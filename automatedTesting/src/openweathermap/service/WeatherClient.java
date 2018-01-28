package openweathermap.service;

import openweathermap.weather.Coordinates;
import openweathermap.weather.ForecastMaxMinTemperatures;
import openweathermap.weather.Temperature;

import java.io.IOException;

public class WeatherClient {

    private String apiKey;
    private WeatherDAO weatherDAO;

    public WeatherClient(String apiKey, WeatherDAO weatherDAO) {
        this.apiKey = apiKey;
        this.weatherDAO = weatherDAO;
    }

    public Temperature getCurrentTemperatureByCityId(int cityId) throws IOException {
        return weatherDAO.getCurrentTemperature(cityId, apiKey);
    }

    public Coordinates getCoordinatesByCityId(int cityId) throws IOException {
        return weatherDAO.getCoordinates(cityId, apiKey);
    }

    public ForecastMaxMinTemperatures getForecastMaxMinTemperaturesByCityId(int cityId) throws IOException {
        return weatherDAO.getForecastMaxMinTemperatures(cityId, apiKey);
    }

    public void getCurrentTemperatureByCityIdFromTextFile(String inputFileName) throws IOException {
        weatherDAO.getCurrentTemperatureToFile(inputFileName, apiKey);
    }

    public void getCoordinatesByCityIdFromTextFile(String inputFileName) throws IOException {
        weatherDAO.getCoordinatesToFile(inputFileName, apiKey);
    }

    public void getForecastMaxMinTemperaturesByCityIdFromTextFile(String inputFileName) throws IOException {
        weatherDAO.getForecastMaxMinTemperaturesToFile(inputFileName, apiKey);
    }

    public void getBulkWeatherInfoFromTextFileToTextFiles(String inputFileName) throws IOException {
        weatherDAO.getBulkWeatherInfoToFiles(inputFileName, apiKey);
    }
}
