package io.github.davikosta.currencyc.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Lê uma string do console.
     * @param prompt Mensagem para exibir ao usuário.
     * @return A string lida.
     */
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Lê um double do console, tratando erros de entrada.
     * @param prompt Mensagem para exibir ao usuário.
     * @return O valor double lido.
     */
    public static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consome a quebra de linha restante
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        }
    }

    /**
     * Fecha o scanner. Deve ser chamado no final da aplicação.
     */
    public static void closeScanner() {
        scanner.close();
    }
}
