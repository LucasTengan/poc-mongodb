package br.com.lucas.wishlist.adapter.inbound.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoRequest {

    private String nome;
    private String marca;
    private String cor;
    private String categoria;
    private String vendidoPor;
    private String detalhes;
    private BigDecimal preco;
    private Integer qtd;
}
