package br.com.lucas.wishlist.adapter.inbound.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoResponse {

    private String nome;
    private String marca;
    private String cor;
    private String categoria;
    private String vendidoPor;
    private String detalhes;
    private BigDecimal preco;
    private Integer qtd;
}
