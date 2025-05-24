package io.github.davikosta.conversormoedas.main;

import io.github.davikosta.conversormoedas.service.ExchangeRateApiService;
import io.github.davikosta.conversormoedas.util.InputHandler;

import java.io.IOException;

public class ConversorApp {

    // Sua API Key da ExchangeRate-API
    private static final String API_KEY = "1738e9e943368c6dff7af3fc"; // <<< MUDAR AQUI PELA SUA CHAVE REAL!

    public static void main(String[] args) throws IOException {
        if (API_KEY.equals("SUA_API_KEY") || API_KEY.isEmpty()) {
            System.err.println("ERRO: Por favor, substitua 'SUA_API_KEY' pela sua chave real da ExchangeRate-API no arquivo ConversorApp.java.");
            System.exit(1); // Encerra a aplicação
        }

        ExchangeRateApiService apiService = new ExchangeRateApiService(API_KEY);

        System.out.println("*****Bem-vindo ao CurrencyC, um conversor de moedas via terminal*****");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Converter Moeda");
            System.out.println("2. Sair");
            System.out.print("Opção: ");

            String choice = InputHandler.getStringInput(""); // Lê a opção do usuário

            switch (choice) {
                case "1":
                    performConversion(apiService);
                    break;
                case "2":
                    System.out.println("Encerrando programa... Obrigado por usar o meu programa. Até mais! :)");
                    InputHandler.closeScanner(); // Fecha o scanner antes de sair
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private static void performConversion(ExchangeRateApiService apiService) throws IOException {
        String baseCurrency = InputHandler.getStringInput("Digite o código da moeda de origem (ex: USD para dólar, BRL para real, ...): ").toUpperCase();
        String targetCurrency = InputHandler.getStringInput("Digite o código da moeda destino (ex: EUR para euro, CAD para dólar canadense): ").toUpperCase();
        double amount = InputHandler.getDoubleInput("Digite o valor a ser convertido: ");

        if (amount < 0) {
            System.out.println("O valor a ser convertido não pode ser negativo.");
            return;
        }

        System.out.println("Convertendo " + amount + " " + baseCurrency + " para " + targetCurrency + "...");

        double convertedValue = apiService.convertCurrency(amount, baseCurrency, targetCurrency);

        if (convertedValue != -1.0) { // -1.0 é o meu indicador de erro
            System.out.printf("Resultado: %.2f %s = %.2f %s%n", amount, baseCurrency, convertedValue, targetCurrency);
        } else {
            System.out.println("Não foi possível realizar a conversão. Verifique os códigos das moedas ou sua conexão.");
        }
    }
}
