package br.com.lucas.wishlist.domain.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdutoNaoEncontradoNaWishlistException extends RuntimeException {

    public ProdutoNaoEncontradoNaWishlistException() {
        super();
    }
}
