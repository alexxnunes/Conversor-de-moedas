package com.conversor.de.moedas.controller;

import com.conversor.de.moedas.excecao.ErroConexaoException;
import com.conversor.de.moedas.excecao.MoedaNaoEncontradaException;
import com.conversor.de.moedas.excecao.RespostaInvalidaException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Controller {

    private static final String API_KEY = "e136721a4e6563b6b9d8e14e";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public double converter(String moedaOrigem, String moedaDestino, double valor) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + moedaOrigem))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            double taxa = extrairTaxa(json, moedaDestino);

            return valor * taxa;

        } catch (MoedaNaoEncontradaException | RespostaInvalidaException e) {
            throw e;
        } catch (Exception e) {
            throw new ErroConexaoException("Erro ao conectar com a API de câmbio.", e);
        }
    }

    private double extrairTaxa(String json, String moeda) {
        String busca = "\"" + moeda + "\":";
        int inicio = json.indexOf(busca);

        if (inicio == -1) {
            throw new MoedaNaoEncontradaException("Moeda não encontrada: " + moeda);
        }

        inicio += busca.length();
        int fim = json.indexOf(",", inicio);
        if (fim == -1) {
            fim = json.indexOf("}", inicio);
        }

        try {
            String valorStr = json.substring(inicio, fim).trim();
            return Double.parseDouble(valorStr);
        } catch (NumberFormatException e) {
            throw new RespostaInvalidaException("Erro ao interpretar a taxa de câmbio.", e);
        }
    }
}
