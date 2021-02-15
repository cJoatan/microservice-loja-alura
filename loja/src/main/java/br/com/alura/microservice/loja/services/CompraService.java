package br.com.alura.microservice.loja.services;

import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.dto.InfoPedidoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    Logger logger = LoggerFactory.getLogger(CompraService.class);

    private final FornecedorClient fornecedorClient;

    @Autowired
    public CompraService(FornecedorClient fornecedorClient) {
        this.fornecedorClient = fornecedorClient;
    }

    public Compra realizaCompra(CompraDTO compra) {
        final InfoFornecedorDTO infoPorEstado = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
        final InfoPedidoDTO infoPedidoDTO = fornecedorClient.realizaPedido(compra.getItens());

        logger.info("ENDERECO " + infoPorEstado.getEndereco());

        return new Compra(infoPedidoDTO.getId(), infoPedidoDTO.getTempoDePreparo(), compra.getEndereco().toString());
    }

}
