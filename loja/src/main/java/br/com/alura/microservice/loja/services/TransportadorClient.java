package br.com.alura.microservice.loja.services;

import br.com.alura.microservice.loja.dto.InfoEntregaDTO;
import br.com.alura.microservice.loja.dto.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("transportador")
public interface TransportadorClient {

    @PostMapping("entrega")
    VoucherDTO reservaEntrega(InfoEntregaDTO entregaDTO);
}
