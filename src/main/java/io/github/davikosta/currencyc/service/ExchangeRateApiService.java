package io.github.davikosta.currencyc.service;

import com.google.gson.Gson;
import io.github.davikosta.currencyc.model.ExchangeRateResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class ExchangeRateApiService {

    private final String API_KEY;
    private final OkHttpClient client;
    private final Gson gson;
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public ExchangeRateApiService(String apiKey) {
        this.API_KEY = apiKey;
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    /**
     * Obtém as taxas de conversão mais recentes para uma moeda base específica.
     * @param baseCode O código da moeda base (ex: "USD", "EUR").
     * @return Um objeto ExchangeRateResponse contendo as taxas de conversão, ou null em caso de erro.
     */
    public ExchangeRateResponse getLatestRates(String baseCode) throws IOException {
        String url = BASE_URL + API_KEY + "/latest/" + baseCode.toUpperCase();

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String json = response.body().string();
                return gson.fromJson(json, ExchangeRateResponse.class);
            } else {
                System.err.println("Erro ao obter taxas de conversão: " + response.code() + " " + response.message());
                if (response.body() != null) {
                    System.err.println("Corpo da resposta: " + response.body().string());
                }
                return null;
            }
        } catch (IOException e) {
            System.err.println("Erro de rede ao obter taxas de conversão: " + e.getMessage());
            return null;
        }
    }

    /**
     * Calcula o valor convertido.
     * @param amount O valor a ser convertido.
     * @param baseCode A moeda de origem.
     * @param targetCode A moeda de destino.
     * @return O valor convertido, ou -1.0 em caso de erro.
     */
    public double convertCurrency(double amount, String baseCode, String targetCode) throws IOException {
        ExchangeRateResponse response = getLatestRates(baseCode);

        if (response != null && "success".equals(response.getResult())) {
            Map<String, Double> rates = response.getConversionRates();
            if (rates != null && rates.containsKey(targetCode.toUpperCase())) {
                double targetRate = rates.get(targetCode.toUpperCase());
                return amount * targetRate;
            } else {
                System.err.println("Código da moeda destino '" + targetCode + "' não encontrado nas taxas de conversão.");
                return -1.0;
            }
        } else {
            System.err.println("Não foi possível obter as taxas de conversão para " + baseCode + ".");
            return -1.0;
        }
    }
}
