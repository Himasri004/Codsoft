import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // base: 1 USD
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("INR", 83.0);
        exchangeRates.put("EUR", 0.93);
        exchangeRates.put("GBP", 0.80);
        exchangeRates.put("JPY", 155.2);
    }

    public static void main(String[] args) {
        Scanner scanner = null; // Declare scanner here for proper handling
        try {
            scanner = new Scanner(System.in);  // Initialize inside try block

            System.out.println("Available currencies: " + exchangeRates.keySet());
            System.out.print("Enter base currency (e.g. USD): ");
            String baseCurrency = scanner.next().toUpperCase();

            System.out.print("Enter target currency (e.g. INR): ");
            String targetCurrency = scanner.next().toUpperCase();

            if (!exchangeRates.containsKey(baseCurrency) || !exchangeRates.containsKey(targetCurrency)) {
                System.out.println("Invalid currency entered!");
                return;
            }

            System.out.print("Enter amount in " + baseCurrency + ": ");
            double amount = scanner.nextDouble();

            double baseToUSD = amount / exchangeRates.get(baseCurrency); // convert to USD first
            double convertedAmount = baseToUSD * exchangeRates.get(targetCurrency); // then to target currency

            System.out.printf("Converted amount: %.2f %s%n", convertedAmount, targetCurrency);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close(); // Ensure the scanner is closed in the finally block
            }
        }
    }
}
