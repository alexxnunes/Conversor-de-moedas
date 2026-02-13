package com.conversor.de.moedas.principal;

import com.conversor.de.moedas.controller.Controller;
import com.conversor.de.moedas.excecao.ErroConexaoException;
import com.conversor.de.moedas.excecao.MoedaNaoEncontradaException;
import com.conversor.de.moedas.excecao.RespostaInvalidaException;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller = new Controller();
    private int opcao = 0;

    public void exibirMenu() {
        while (opcao != 15) {
            System.out.println("\n============================================");
            System.out.println("   *** Conversor de Moedas ***");
            System.out.println("============================================");
            System.out.println("1  - R$ → US$ | Real para Dólar");
            System.out.println("2  - US$ → R$ | Dólar para Real");
            System.out.println("3  - R$ → ¥   | Real para Iene");
            System.out.println("4  - ¥ → R$   | Iene para Real");
            System.out.println("5  - R$ → €   | Real para Euro");
            System.out.println("6  - € → R$   | Euro para Real");
            System.out.println("7  - R$ → CN¥ | Real para Yuan");
            System.out.println("8  - CN¥ → R$ | Yuan para Real");
            System.out.println("9  - US$ → ¥  | Dólar para Iene");
            System.out.println("10 - ¥ → US$  | Iene para Dólar");
            System.out.println("11 - US$ → €  | Dólar para Euro");
            System.out.println("12 - € → US$  | Euro para Dólar");
            System.out.println("13 - US$ → CN¥| Dólar para Yuan");
            System.out.println("14 - CN¥ → US$| Yuan para Dólar");
            System.out.println("15 - Sair");
            System.out.println("============================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            if (opcao == 15) {
                System.out.println("Obrigado por usar o Conversor de Moedas! Até logo!");
                break;
            }

            processarEscolha();
        }
        scanner.close();
    }

    private void processarEscolha() {
        String moedaOrigem = "";
        String moedaDestino = "";
        String nomeOrigem = "";
        String nomeDestino = "";

        switch (opcao) {
            case 1:
                moedaOrigem = "BRL";
                moedaDestino = "USD";
                nomeOrigem = "Real (R$)";
                nomeDestino = "Dólar (US$)";
                break;
            case 2:
                moedaOrigem = "USD";
                moedaDestino = "BRL";
                nomeOrigem = "Dólar (US$)";
                nomeDestino = "Real (R$)";
                break;
            case 3:
                moedaOrigem = "BRL";
                moedaDestino = "JPY";
                nomeOrigem = "Real (R$)";
                nomeDestino = "Iene (¥)";
                break;
            case 4:
                moedaOrigem = "JPY";
                moedaDestino = "BRL";
                nomeOrigem = "Iene (¥)";
                nomeDestino = "Real (R$)";
                break;
            case 5:
                moedaOrigem = "BRL";
                moedaDestino = "EUR";
                nomeOrigem = "Real (R$)";
                nomeDestino = "Euro (€)";
                break;
            case 6:
                moedaOrigem = "EUR";
                moedaDestino = "BRL";
                nomeOrigem = "Euro (€)";
                nomeDestino = "Real (R$)";
                break;
            case 7:
                moedaOrigem = "BRL";
                moedaDestino = "CNY";
                nomeOrigem = "Real (R$)";
                nomeDestino = "Yuan (CN¥)";
                break;
            case 8:
                moedaOrigem = "CNY";
                moedaDestino = "BRL";
                nomeOrigem = "Yuan (CN¥)";
                nomeDestino = "Real (R$)";
                break;
            case 9:
                moedaOrigem = "USD";
                moedaDestino = "JPY";
                nomeOrigem = "Dólar (US$)";
                nomeDestino = "Iene (¥)";
                break;
            case 10:
                moedaOrigem = "JPY";
                moedaDestino = "USD";
                nomeOrigem = "Iene (¥)";
                nomeDestino = "Dólar (US$)";
                break;
            case 11:
                moedaOrigem = "USD";
                moedaDestino = "EUR";
                nomeOrigem = "Dólar (US$)";
                nomeDestino = "Euro (€)";
                break;
            case 12:
                moedaOrigem = "EUR";
                moedaDestino = "USD";
                nomeOrigem = "Euro (€)";
                nomeDestino = "Dólar (US$)";
                break;
            case 13:
                moedaOrigem = "USD";
                moedaDestino = "CNY";
                nomeOrigem = "Dólar (US$)";
                nomeDestino = "Yuan (CN¥)";
                break;
            case 14:
                moedaOrigem = "CNY";
                moedaDestino = "USD";
                nomeOrigem = "Yuan (CN¥)";
                nomeDestino = "Dólar (US$)";
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                return;
        }

        System.out.print("Digite o valor em " + nomeOrigem + ": ");
        double valor = scanner.nextDouble();

        try {
            double resultado = controller.converter(moedaOrigem, moedaDestino, valor);
            System.out.printf("\n%.2f %s = %.2f %s%n", valor, nomeOrigem, resultado, nomeDestino);
        } catch (ErroConexaoException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        } catch (MoedaNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (RespostaInvalidaException e) {
            System.out.println("Erro na resposta da API: " + e.getMessage());
        }
    }
}
