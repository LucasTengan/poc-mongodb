package br.com.lucas.wishlist.adapter.outbound.datasource;

import br.com.lucas.wishlist.domain.model.entity.Produto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MongoDBProdutoRepositoryTest {

    @Autowired
    private MongoDBProdutoRepository mongoDBProdutoRepository;
    private Produto produtoTeste;

    @BeforeEach
    void setUp() {
        produtoTeste = new Produto();
        produtoTeste.setNome("teste-nome");
        produtoTeste.setMarca("teste-marca");
        produtoTeste.setDetalhes("teste-detalhes");
        produtoTeste.setPreco(new BigDecimal("10.9"));
    }

    @Test
    void devePersistirProdutoQuandoSucesso() {
        Produto produtoTestePersistido = this.mongoDBProdutoRepository.save(produtoTeste);

        Assertions.assertThat(produtoTestePersistido).isNotNull();
        Assertions.assertThat(produtoTestePersistido.getId()).isEqualTo(produtoTeste.getId());
        Assertions.assertThat(produtoTestePersistido.getNome()).isEqualTo(produtoTeste.getNome());
        Assertions.assertThat(produtoTestePersistido.getMarca()).isEqualTo(produtoTeste.getMarca());
        Assertions.assertThat(produtoTestePersistido.getDetalhes()).isEqualTo(produtoTeste.getDetalhes());
        Assertions.assertThat(produtoTestePersistido.getPreco()).isEqualTo(produtoTeste.getPreco());
    }

    @Test
    void deveAtualizarProdutoQuandoSucesso() {
        Produto produtoTestePersistido = this.mongoDBProdutoRepository.save(produtoTeste);
        produtoTestePersistido.setNome("novo-teste-nome");
        Produto produtoTesteAtualizado = this.mongoDBProdutoRepository.save(produtoTestePersistido);

        Assertions.assertThat(produtoTestePersistido).isNotNull();
        Assertions.assertThat(produtoTestePersistido.getId()).isEqualTo(produtoTesteAtualizado.getId());
        Assertions.assertThat(produtoTestePersistido.getMarca()).isEqualTo(produtoTesteAtualizado.getMarca());
        Assertions.assertThat(produtoTestePersistido.getDetalhes()).isEqualTo(produtoTesteAtualizado.getDetalhes());
        Assertions.assertThat(produtoTestePersistido.getPreco()).isEqualTo(produtoTesteAtualizado.getPreco());
    }
}