package br.com.alura.microservice.loja.services;

import br.com.alura.microservice.loja.dto.*;
import br.com.alura.microservice.loja.models.Compra;
import br.com.alura.microservice.loja.models.StatusCompra;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Service
public class CompraService {

    Logger logger = LoggerFactory.getLogger(CompraService.class);

    private final FornecedorClient fornecedorClient;
    private final TransportadorClient transportadorClient;
    private final CompraRepository compraRepository;

    @Autowired
    public CompraService(FornecedorClient fornecedorClient, TransportadorClient transportadorClient,
                         CompraRepository compraRepository) {
        this.fornecedorClient = fornecedorClient;
        this.transportadorClient = transportadorClient;
        this.compraRepository = compraRepository;
    }

    @Bulkhead(name = "mostraCompra")
    public Compra findById(Long id) {
        return compraRepository.findById(id).get();
    }

    @Bulkhead(name = "realizaCompra")
    @CircuitBreaker(name = "realizaCompra", fallbackMethod = "realizaCompraFallback")
    public Compra realizaCompra(CompraDTO compra) {

        final Compra newCompra = new Compra();
        newCompra.setStatus(StatusCompra.RECEBIDO);
        newCompra.setEnderecoDestino(compra.getEndereco().toString());
        compraRepository.save(newCompra);

        final InfoFornecedorDTO infoPorEstado = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
        final InfoPedidoDTO infoPedidoDTO = fornecedorClient.realizaPedido(compra.getItens());

        newCompra.setStatus(StatusCompra.PEDIDO_REALIZADO)
            .setPedidoId(infoPedidoDTO.getId())
            .setTempoDePreparo(infoPedidoDTO.getTempoDePreparo());
        compraRepository.save(newCompra);

        final InfoEntregaDTO entregaDTO = new InfoEntregaDTO(infoPedidoDTO.getId(), LocalDate.now().plusDays(infoPedidoDTO.getTempoDePreparo()),
            infoPorEstado.getEndereco(), compra.getEndereco().toString());
//
        final VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDTO);
        newCompra.setStatus(StatusCompra.RESERVA_ENTREGA_REALIZADA)
            .setDataParaEntrega(voucher.getPrevisaoParaEntrega())
            .setVoucher(voucher.getNumero());
        compraRepository.save(newCompra);

        logger.info("ENDERECO " + infoPorEstado.getEndereco());

        return newCompra;
    }

    public Compra realizaCompraFallback(CompraDTO compra, Throwable throwable) {
        System.out.println("ERROR: " + throwable);
        return new Compra(null, null, null, compra.getEndereco().toString(), null);
    }

}
