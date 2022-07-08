package br.com.lucas.wishlist.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collation = "produto-collection")
public class Produto {

    @Id
    private String id;
    private String nome;
    private String marca;
    private String cor;
    private String categoria;
    private String vendidoPor;
    private String detalhes;
    private BigDecimal preco;
    private Integer qtd;
}
