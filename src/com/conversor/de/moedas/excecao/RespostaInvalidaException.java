package com.conversor.de.moedas.excecao;

public class RespostaInvalidaException extends RuntimeException {

    public RespostaInvalidaException(String mensagem) {
        super(mensagem);
    }

    public RespostaInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
