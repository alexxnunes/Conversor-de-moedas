package com.conversor.de.moedas.excecao;

public class ErroConexaoException extends RuntimeException {

    public ErroConexaoException(String mensagem) {
        super(mensagem);
    }

    public ErroConexaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
