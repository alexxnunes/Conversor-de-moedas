package com.conversor.de.moedas.excecao;

public class MoedaNaoEncontradaException extends RuntimeException {

    public MoedaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
