
import openweathermap.service.WeatherAPI;
import openweathermap.service.WeatherClient;
import openweathermap.service.WeatherDAO;
import openweathermap.service.WeatherHelper;
import openweathermap.weather.Coordinates;
import openweathermap.weather.ForecastMaxMinTemperatures;
import openweathermap.weather.Temperature;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        final int CITY_ID = 0;
        final String API_KEY = "fde9c3d325025e6e3e3fc555c0f71b9c";

        WeatherDAO weatherDAO = new WeatherDAO(new WeatherAPI(), new WeatherHelper());
        WeatherClient weatherClient = new WeatherClient(API_KEY, weatherDAO);
        Coordinates coords = weatherClient.getCoordinatesByCityId(CITY_ID);
        System.out.println(coords);

/*        Temperature currentTemp  = weatherClient.getCurrentTemperatureByCityId(CITY_ID);
        System.out.println(currentTemp);

        ForecastMaxMinTemperatures temperatures = weatherClient.getForecastMaxMinTemperaturesByCityId(CITY_ID);
        System.out.println(temperatures);

        weatherClient.getBulkWeatherInfoFromTextFileToTextFiles("inputBulk.txt");*/
    }
}
