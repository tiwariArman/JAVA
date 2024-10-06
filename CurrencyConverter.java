import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class CurrencyConverter{
    private static final String API_KEY = "YOUR_API_KEY";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Take base currency input from user
            System.out.print("Enter the base currency (e.g., USD, EUR, GBP): ");
            String baseCurrency = scanner.next().toUpperCase();

            // Take target currency input from user
            System.out.print("Enter the target currency (e.g., USD, EUR, GBP): ");
            String targetCurrency = scanner.next().toUpperCase();

            // Construct the API URL
            String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency + "?apikey=" + API_KEY;

            // Make HTTP request
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Parse the JSON response to get the exchange rate
            String jsonResponse = response.toString();
            double exchangeRate = parseExchangeRate(jsonResponse, targetCurrency);

            // Take amount input from user
            System.out.print("Enter the amount to convert: ");
            double amountToConvert = scanner.nextDouble();

            // Perform currency conversion
            double convertedAmount = convertCurrency(amountToConvert, exchangeRate);

            // Display the result
            System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static double parseExchangeRate(String jsonResponse, String targetCurrency) {
        // Parse the JSON response to get the exchange rate for the target currency
        // You may want to use a JSON parsing library for a more robust solution
        String targetCurrencyKey = "\"" + targetCurrency + "\":";
        int startIdx = jsonResponse.indexOf(targetCurrencyKey) + targetCurrencyKey.length();
        int endIdx = jsonResponse.indexOf(",", startIdx);
        String rateSubstring = jsonResponse.substring(startIdx, endIdx);

        return Double.parseDouble(rateSubstring);
    }

    private static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}
