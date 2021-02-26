package br.com.alura.microservice.loja.services;

import br.com.alura.microservice.loja.models.Compra;
import org.springframework.data.repository.CrudRepository;

public interface CompraRepository extends CrudRepository<Compra, Long> {
}
