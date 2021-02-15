package br.com.alura.microservice.loja.controllers;

import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.services.Compra;
import br.com.alura.microservice.loja.services.CompraService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("compra")
public class CompraController {


    private CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public Compra realizaCompra(@RequestBody CompraDTO compra) {
        return compraService.realizaCompra(compra);
    }
}
