package br.com.lucas.wishlist.adapter.inbound.controller;

import br.com.lucas.wishlist.adapter.inbound.dto.ProdutoRequest;
import br.com.lucas.wishlist.adapter.inbound.dto.ProdutoResponse;
import br.com.lucas.wishlist.adapter.inbound.mapper.ProdutoMapper;
import br.com.lucas.wishlist.domain.model.entity.Produto;
import br.com.lucas.wishlist.domain.ports.BusinessLogic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class WishlistControllerTest {

    @InjectMocks
    private WishlistController wishlistController;
    @Mock
    private BusinessLogic businessLogic;
    @Mock
    private ProdutoMapper produtoMapper;
    private ProdutoRequest produtoRequestTeste;
    private Produto produtoTeste;
    private ProdutoResponse produtoResponseTeste;

    @BeforeEach
    void setUp() {
        produtoRequestTeste = new ProdutoRequest();
        produtoRequestTeste.setNome("teste-nome");
        produtoRequestTeste.setMarca("teste-marca");
        produtoRequestTeste.setDetalhes("teste-detalhes");
        produtoRequestTeste.setPreco(new BigDecimal("10.9"));

        produtoResponseTeste = new ProdutoResponse();
        produtoResponseTeste.setNome("teste-nome");
        produtoResponseTeste.setMarca("teste-marca");
        produtoResponseTeste.setDetalhes("teste-detalhes");
        produtoResponseTeste.setPreco(new BigDecimal("10.9"));

        produtoTeste = new Produto();
        produtoTeste.setNome("teste-nome");
        produtoTeste.setMarca("teste-marca");
        produtoTeste.setDetalhes("teste-detalhes");
        produtoTeste.setPreco(new BigDecimal("10.9"));
    }

    @Test
    void devePostarProdutoNaWishlistQuandoSucesso() {
        when(produtoMapper.requestToEntity(any())).thenReturn(produtoTeste);
        when(businessLogic.adicionaProdutoNaWishlist(any())).thenReturn(produtoTeste);
        when(produtoMapper.entityToResponse(produtoTeste)).thenReturn(produtoResponseTeste);

        ResponseEntity<ProdutoResponse> produtoResponse = wishlistController.postProdutoNaWishlist(produtoRequestTeste);
        ProdutoResponse produtoResponseBody = produtoResponse.getBody();

        Assertions.assertThat(produtoResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(produtoResponseBody).isNotNull();
        Assertions.assertThat(produtoResponseBody.getNome()).isEqualTo(produtoTeste.getNome());
        Assertions.assertThat(produtoResponseBody.getMarca()).isEqualTo(produtoTeste.getMarca());
        Assertions.assertThat(produtoResponseBody.getDetalhes()).isEqualTo(produtoTeste.getDetalhes());
        Assertions.assertThat(produtoResponseBody.getPreco()).isEqualTo(produtoTeste.getPreco());
    }

    @Test
    void deveDeletarProdutoDaWishlistQuandoSucesso() {
        doNothing().when(businessLogic).deletaProdutoDaWishList("teste-nome", "teste-marca", "teste-detalhes");
        ResponseEntity<Void> deleteResponseEntity = wishlistController.deleteProdutoDaWishlist("teste-nome", "teste-marca", "teste-detalhes");

        verify(businessLogic, times(1)).deletaProdutoDaWishList(anyString(), anyString(), anyString());
        Assertions.assertThat(deleteResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThatCode(() ->
                wishlistController.deleteProdutoDaWishlist("teste-nome", "teste-marca", "teste-detalhes")).doesNotThrowAnyException();
    }

    @Test
    void deveListarTodosProdutosQuandoNenhumParametroPassado() {
        when(businessLogic.consultaProdutosDaWishlist()).thenReturn(Arrays.asList(produtoTeste));
        when(produtoMapper.entitiesToResponse(Arrays.asList(produtoTeste))).thenReturn(Arrays.asList(produtoResponseTeste));
        ResponseEntity<?> listaTodosProdutoResponseEntity = wishlistController.listProdutosDaWishlist("", "", "");
        List<ProdutoResponse> listaProdutosResponse = (List<ProdutoResponse>) listaTodosProdutoResponseEntity.getBody();
        ProdutoResponse produtoResponseBody = listaProdutosResponse.get(0);

        Assertions.assertThat(listaTodosProdutoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(listaTodosProdutoResponseEntity).isNotNull();
        Assertions.assertThat(produtoResponseBody).isNotNull();
        Assertions.assertThat(produtoResponseBody.getNome()).isEqualTo(produtoTeste.getNome());
        Assertions.assertThat(produtoResponseBody.getMarca()).isEqualTo(produtoTeste.getMarca());
        Assertions.assertThat(produtoResponseBody.getDetalhes()).isEqualTo(produtoTeste.getDetalhes());
        Assertions.assertThat(produtoResponseBody.getPreco()).isEqualTo(produtoTeste.getPreco());
    }

    @Test
    void deveListarUmProdutoQuandoTodosParametroPassado() {
        when(businessLogic.verificaSeProdutoEstaNaWishlist(anyString(), anyString(), anyString())).thenReturn(produtoTeste);
        when(produtoMapper.entityToResponse(produtoTeste)).thenReturn(produtoResponseTeste);
        ResponseEntity<?> responseEntity = wishlistController.listProdutosDaWishlist("teste-nome", "teste-marca", "teste-detalhes");
        ProdutoResponse produtoResponseBody = (ProdutoResponse) responseEntity.getBody();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(produtoResponseBody).isNotNull();
        Assertions.assertThat(produtoResponseBody.getNome()).isEqualTo(produtoTeste.getNome());
        Assertions.assertThat(produtoResponseBody.getMarca()).isEqualTo(produtoTeste.getMarca());
        Assertions.assertThat(produtoResponseBody.getDetalhes()).isEqualTo(produtoTeste.getDetalhes());
        Assertions.assertThat(produtoResponseBody.getPreco()).isEqualTo(produtoTeste.getPreco());
    }
}