package br.com.alura.microservice.loja.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private Integer tempoDePreparo;

    private LocalDate dataParaEntrega;

    private String enderecoDestino;

    private Long voucher;

    private StatusCompra status;

    public Compra() { }

    public Compra(Long pedidoId, Integer tempoDePreparo, LocalDate dataParaEntrega, String enderecoDestino, Long voucher) {
        this.pedidoId = pedidoId;
        this.tempoDePreparo = tempoDePreparo;
        this.dataParaEntrega = dataParaEntrega;
        this.enderecoDestino = enderecoDestino;
        this.voucher = voucher;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public Compra setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
        return this;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public Compra setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
        return this;
    }

    public Integer getTempoDePreparo() {
        return tempoDePreparo;
    }

    public Compra setTempoDePreparo(Integer tempoDePreparo) {
        this.tempoDePreparo = tempoDePreparo;
        return this;
    }

    public LocalDate getDataParaEntrega() {
        return dataParaEntrega;
    }

    public Compra setDataParaEntrega(LocalDate dataParaEntrega) {
        this.dataParaEntrega = dataParaEntrega;
        return this;
    }

    public Long getVoucher() {
        return voucher;
    }

    public Compra setVoucher(Long voucher) {
        this.voucher = voucher;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Compra setId(Long id) {
        this.id = id;
        return this;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public Compra setStatus(StatusCompra status) {
        this.status = status;
        return this;
    }
}
