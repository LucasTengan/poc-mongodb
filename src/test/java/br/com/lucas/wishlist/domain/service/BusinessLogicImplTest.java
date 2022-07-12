package br.com.lucas.wishlist.domain.service;

import br.com.lucas.wishlist.domain.model.entity.Produto;
import br.com.lucas.wishlist.domain.ports.ProdutoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BusinessLogicImplTest {

    @InjectMocks
    private BusinessLogicImpl businessLogic;
    @Mock
    private ProdutoRepository produtoRepository;
    private Produto produtoTeste;
    private Produto produtoTeste2;

    @BeforeEach
    void setUp() {
        produtoTeste = new Produto();
        produtoTeste.setNome("teste-nome");
        produtoTeste.setMarca("teste-marca");
        produtoTeste.setDetalhes("teste-detalhes");
        produtoTeste.setPreco(new BigDecimal("10.9"));

        produtoTeste2 = new Produto();
        produtoTeste2.setNome("teste2-nome");
        produtoTeste2.setMarca("teste2-marca");
        produtoTeste2.setDetalhes("teste2-detalhes");
        produtoTeste2.setPreco(new BigDecimal("15.9"));
    }

    @Test
    void deveRetornarProdutoPersistidoQuandoSucesso() {
        when(produtoRepository.criaProduto(any())).thenReturn(produtoTeste);
        Produto produtoAdicionadoNaWishlist = businessLogic.adicionaProdutoNaWishlist(produtoTeste);

        Assertions.assertThat(produtoAdicionadoNaWishlist).isNotNull();
        Assertions.assertThat(produtoAdicionadoNaWishlist.getId()).isEqualTo(produtoTeste.getId());
        Assertions.assertThat(produtoAdicionadoNaWishlist.getNome()).isEqualTo(produtoTeste.getNome());
        Assertions.assertThat(produtoAdicionadoNaWishlist.getMarca()).isEqualTo(produtoTeste.getMarca());
        Assertions.assertThat(produtoAdicionadoNaWishlist.getDetalhes()).isEqualTo(produtoTeste.getDetalhes());
        Assertions.assertThat(produtoAdicionadoNaWishlist.getPreco()).isEqualTo(produtoTeste.getPreco());
    }

    @Test
    void deveRetornarListaDeProdutosQuandoSucesso() {
        when(produtoRepository.listaProdutos()).thenReturn(Arrays.asList(produtoTeste, produtoTeste2));
        List<Produto> produtosRetornadosDaConsulta = businessLogic.consultaProdutosDaWishlist();
        Produto primeiroProduto = produtosRetornadosDaConsulta.get(0);
        Produto segundoProduto = produtosRetornadosDaConsulta.get(1);

        Assertions.assertThat(produtosRetornadosDaConsulta).isNotNull();
        Assertions.assertThat(produtosRetornadosDaConsulta).isEqualTo(Arrays.asList(produtoTeste, produtoTeste2));

        Assertions.assertThat(primeiroProduto.getId()).isEqualTo(produtoTeste.getId());
        Assertions.assertThat(primeiroProduto.getNome()).isEqualTo(produtoTeste.getNome());
        Assertions.assertThat(primeiroProduto.getMarca()).isEqualTo(produtoTeste.getMarca());
        Assertions.assertThat(primeiroProduto.getDetalhes()).isEqualTo(produtoTeste.getDetalhes());
        Assertions.assertThat(primeiroProduto.getPreco()).isEqualTo(produtoTeste.getPreco());

        Assertions.assertThat(segundoProduto.getId()).isEqualTo(produtoTeste2.getId());
        Assertions.assertThat(segundoProduto.getNome()).isEqualTo(produtoTeste2.getNome());
        Assertions.assertThat(segundoProduto.getMarca()).isEqualTo(produtoTeste2.getMarca());
        Assertions.assertThat(segundoProduto.getDetalhes()).isEqualTo(produtoTeste2.getDetalhes());
        Assertions.assertThat(segundoProduto.getPreco()).isEqualTo(produtoTeste2.getPreco());
    }

    @Test
    void deveRetornarProdutoConsultadoQuandoSucesso() {
        when(produtoRepository.buscaProdutoPorNomeMarcaDetalhes(anyString(), anyString(), anyString())).thenReturn(Optional.ofNullable(produtoTeste));
        Produto produtoVerificado = businessLogic.verificaSeProdutoEstaNaWishlist("teste-nome", "teste-marca", "teste-detalhes");

        Assertions.assertThat(produtoVerificado).isNotNull();
        Assertions.assertThat(produtoVerificado.getId()).isEqualTo(produtoTeste.getId());
        Assertions.assertThat(produtoVerificado.getNome()).isEqualTo(produtoTeste.getNome());
        Assertions.assertThat(produtoVerificado.getMarca()).isEqualTo(produtoTeste.getMarca());
        Assertions.assertThat(produtoVerificado.getDetalhes()).isEqualTo(produtoTeste.getDetalhes());
        Assertions.assertThat(produtoVerificado.getPreco()).isEqualTo(produtoTeste.getPreco());
    }

    @Test
    void deveDeletarProdutoDaBaseQuandoSucesso() {
        when(produtoRepository.buscaProdutoPorNomeMarcaDetalhes(anyString(), anyString(), anyString())).thenReturn(Optional.ofNullable(produtoTeste));
        produtoTeste.setQtd(1);

        businessLogic.deletaProdutoDaWishList("teste-nome", "teste-marca", "teste-detalhes");
        verify(produtoRepository, times(1)).deletaProduto(produtoTeste);
    }
}