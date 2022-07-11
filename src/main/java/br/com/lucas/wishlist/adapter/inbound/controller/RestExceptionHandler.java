package br.com.lucas.wishlist.adapter.inbound.controller;

import br.com.lucas.wishlist.domain.model.exception.FaltandoParametroParaVerificarException;
import br.com.lucas.wishlist.domain.model.exception.MensagemErro;
import br.com.lucas.wishlist.domain.model.exception.ProdutoNaoEncontradoNaWishlistException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoNaWishlistException.class)
    public MensagemErro produtoNaoEncontradoNaWishlistException() {
        return new MensagemErro("Produto com estas descrições não está na Wishlist, verifique os dados novamente.", OffsetDateTime.now());
    }

    @ExceptionHandler(FaltandoParametroParaVerificarException.class)
    public MensagemErro faltaParametrosException() {
        return new MensagemErro("É preciso preencher os campos 'nome', 'marca' e 'detalhes' para verificar se um determinado produto está na Wishlist", OffsetDateTime.now());
    }
}
