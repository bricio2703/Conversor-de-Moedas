import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConversorMoedas {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/0261e654805a91c74d621f25/latest/USD";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JSONObject taxas = obterTaxas();
        if (taxas == null) {
            System.out.println("Erro ao carregar taxas de câmbio. Encerrando.");
            return;
        }

        while (true) {
            exibirMenu();
            int opcao = lerOpcao(scanner);
            if (opcao == 6) {
                System.out.println("\nObrigado por usar o Conversor de Moedas!");
                break;
            }

            double valorBRL = lerValor(scanner);
            if (valorBRL < 0) continue;

            processarConversao(opcao, valorBRL, taxas);
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== CONVERSOR DE MOEDAS ===");
        System.out.println("Escolha uma moeda para conversão:");
        System.out.println("1 - Dólar Americano (USD)");
        System.out.println("2 - Euro (EUR)");
        System.out.println("3 - Libra Esterlina (GBP)");
        System.out.println("4 - Peso Argentino (ARS)");
        System.out.println("5 - Iene Japonês (JPY)");
        System.out.println("6 - Sair");
        System.out.print("Digite sua opção: ");
    }

    private static int lerOpcao(Scanner scanner) {
        int opcao = -1;
        try {
            opcao = scanner.nextInt();
            if (opcao < 1 || opcao > 6) {
                System.out.println("Opção inválida. Tente novamente.");
                return lerOpcao(scanner);
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Digite apenas números.");
            scanner.next(); // limpar buffer
            return lerOpcao(scanner);
        }
        return opcao;
    }

    private static double lerValor(Scanner scanner) {
        System.out.print("Digite o valor em Reais (BRL): R$ ");
        try {
            double valor = scanner.nextDouble();
            if (valor < 0) {
                System.out.println("Valor não pode ser negativo.");
                return -1;
            }
            return valor;
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Digite um número válido.");
            scanner.next(); // limpar buffer
            return -1;
        }
    }

    private static JSONObject obterTaxas() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();

            JSONObject json = new JSONObject(resposta.toString());
            return json.getJSONObject("conversion_rates");

        } catch (Exception e) {
            System.out.println("Erro ao conectar com a API: " + e.getMessage());
            return null;
        }
    }

    private static void processarConversao(int opcao, double valorBRL, JSONObject taxas) {
        try {
            double usdToBrl = taxas.getDouble("BRL");
            double taxaConversao = 1 / usdToBrl; // BRL -> USD base
            String simbolo = "";

            switch (opcao) {
                case 1:
                    simbolo = "USD";
                    break;
                case 2:
                    taxaConversao *= taxas.getDouble("EUR");
                    simbolo = "EUR";
                    break;
                case 3:
                    taxaConversao *= taxas.getDouble("GBP");
                    simbolo = "GBP";
                    break;
                case 4:
                    taxaConversao *= taxas.getDouble("ARS");
                    simbolo = "ARS";
                    break;
                case 5:
                    taxaConversao *= taxas.getDouble("JPY");
                    simbolo = "JPY";
                    break;
            }

            double resultado = valorBRL * taxaConversao;
            System.out.printf("✔ R$ %.2f convertido para %s: %.2f %s\n", valorBRL, simbolo, resultado, simbolo);

        } catch (Exception e) {
            System.out.println("Erro durante conversão: " + e.getMessage());
        }
    }
}
