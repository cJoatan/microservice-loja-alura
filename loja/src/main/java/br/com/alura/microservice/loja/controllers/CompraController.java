package br.com.alura.microservice.loja.controllers;

import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.models.Compra;
import br.com.alura.microservice.loja.services.CompraService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("compra")
public class CompraController {


    private CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping("{id}")
    public Compra show(@PathVariable Long id) {
        return compraService.findById(id);
    }

    @PostMapping
    public Compra realizaCompra(@RequestBody CompraDTO compra) {
        return compraService.realizaCompra(compra);
    }
}
