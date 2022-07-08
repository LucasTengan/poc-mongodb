package br.com.lucas.wishlist.adapter.inbound.dto;

import lombok.*;

import java.math.BigDecimal;

public class ProdutoRequest {

    private String nome;
    private String marca;
    private String cor;
    private String categoria;
    private String vendidoPor;
    private String detalhes;
    private BigDecimal preco;
    private Integer qtd;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getVendidoPor() {
        return vendidoPor;
    }

    public void setVendidoPor(String vendidoPor) {
        this.vendidoPor = vendidoPor;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }
}
