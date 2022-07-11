package br.com.lucas.wishlist.domain.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FaltandoParametroParaVerificarException extends RuntimeException {

    public FaltandoParametroParaVerificarException() {
        super();
    }
}
