package br.com.lucas.wishlist.domain.model.exception;

import java.time.OffsetDateTime;

public class MensagemErro {
    private String mensagem;
    private OffsetDateTime dataHora;

    public MensagemErro(String mensagem, OffsetDateTime dataHora) {
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
