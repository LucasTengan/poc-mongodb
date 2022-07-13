package br.com.lucas.wishlist.adapter.inbound.controller;

import br.com.lucas.wishlist.domain.model.exception.FaltandoParametroParaVerificarException;
import br.com.lucas.wishlist.domain.model.exception.MensagemErro;
import br.com.lucas.wishlist.domain.model.exception.NaoEPossivelAdicionarMaisProdutosNaWishlist;
import br.com.lucas.wishlist.domain.model.exception.ProdutoNaoEncontradoNaWishlistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.OffsetDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoNaWishlistException.class)
    public MensagemErro produtoNaoEncontradoNaWishlistException() {
        return new MensagemErro("Produto com estas descrições não está na Wishlist, verifique os dados novamente.", OffsetDateTime.now());
    }

    @ExceptionHandler(FaltandoParametroParaVerificarException.class)
    public MensagemErro faltaParametrosException() {
        return new MensagemErro("É preciso preencher os campos 'nome', 'marca' e 'detalhes' para verificar se um determinado produto está na Wishlist.", OffsetDateTime.now());
    }

    @ExceptionHandler(NaoEPossivelAdicionarMaisProdutosNaWishlist.class)
    public MensagemErro naoEPossivelAdicionarMaisProdutosException() {
        return new MensagemErro("A quantidade máxima de produtos na Wishlist é 20.", OffsetDateTime.now());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemErro faltaAtributo(ConstraintViolationException ex) {
        return new MensagemErro("O campo '" + ex.getMessage().replace(":", "'") + ".", OffsetDateTime.now());
    }
}
