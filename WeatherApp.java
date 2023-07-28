package Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {
    private static final String API_BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
    private static final String API_KEY = "YOUR_API_KEY"; // Replace with your actual API key
    private static final String LOCATION = "London"; // Replace with the desired location

    public static void main(String[] args) {
        displayMenu();
    }

    private static void displayMenu() {
        int choice;
        do {
            System.out.println("1. Get weather");
            System.out.println("2. Get Wind Speed");
            System.out.println("3. Get Pressure");
            System.out.println("0. Exit");

            choice = getUserChoice();

            switch (choice) {
                case 1:
                    getWeather();
                    break;
                case 2:
                    getWindSpeed();
                    break;
                case 3:
                    getPressure();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
    }

    private static String makeAPIRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    private static void getWeather() {
        String date = getUserInputDate();
        String urlString = API_BASE_URL + "?location=" + LOCATION + "&date=" + date + "&format=json&apiKey=" + API_KEY;
        try {
            String response = makeAPIRequest(urlString);
            System.out.println("Temperature on " + date + ": " + temperatureFromJsonResponse(response));
        } catch (IOException e) {
            System.out.println("Error while making API request: " + e.getMessage());
        }
    }

    private static void getWindSpeed() {
    	String date = getUserInputDate();
    	 String urlString = API_BASE_URL + "?location=" + LOCATION + "&date=" + date + "&format=json&apiKey=" + API_KEY;
         try {
             String response = makeAPIRequest(urlString);
             System.out.println("Wind speed of on " + date + ": " + temperatureFromJsonResponse(response));
         } catch (IOException e) {
             System.out.println("Error while making API request: " + e.getMessage());
         }
    }

    private static void getPressure() {
    	String date = getUserInputDate();
   	    String urlString = API_BASE_URL + "?location=" + LOCATION + "&date=" + date + "&format=json&apiKey=" + API_KEY;
        try {
            String response = makeAPIRequest(urlString);
            System.out.println("pressure of the input " + date + ": " + temperatureFromJsonResponse(response));
        } catch (IOException e) {
            System.out.println("Error while making API request: " + e.getMessage());
        }
    }

    private static String getUserInputDate() {
        System.out.print("Enter the date (YYYY-MM-DD): ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    private static double temperatureFromJsonResponse(String jsonResponse) {
        return 0.0;
    }

}

