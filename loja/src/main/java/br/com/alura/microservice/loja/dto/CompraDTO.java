package br.com.alura.microservice.loja.dto;

import java.util.List;

public class CompraDTO {

    private List<ItemDataCompraDTO> itens;
    private EnderecoDTO endereco;

    public List<ItemDataCompraDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemDataCompraDTO> itens) {
        this.itens = itens;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
