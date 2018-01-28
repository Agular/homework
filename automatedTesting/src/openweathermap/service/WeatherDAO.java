package openweathermap.service;

import com.fasterxml.jackson.databind.JsonNode;
import openweathermap.weather.Coordinates;
import openweathermap.weather.ForecastMaxMinTemperatures;
import openweathermap.weather.Temperature;
import openweathermap.weather.WeatherInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherDAO {

    private WeatherAPI weatherAPI;
    private WeatherHelper weatherHelper;

    public WeatherDAO(WeatherAPI weatherAPI, WeatherHelper weatherHelper) {
        this.weatherAPI = weatherAPI;
        this.weatherHelper = weatherHelper;
    }

    Temperature getCurrentTemperature(int cityId, String apiKey) throws IOException {
        JsonNode apiResponse = weatherAPI.weatherQuery(cityId, apiKey);
        return weatherHelper.convertKelvinStringToCelsiusTemperature(apiResponse);
    }

    Coordinates getCoordinates(int cityId, String apiKey) throws IOException {
        JsonNode apiResponse = weatherAPI.weatherQuery(cityId, apiKey);
        return weatherHelper.getCoordinatesFromNode(apiResponse);
    }

    ForecastMaxMinTemperatures getForecastMaxMinTemperatures(int cityId, String apiKey) throws IOException {
        JsonNode apiResponse = weatherAPI.forecastQuery(cityId, apiKey);
        return weatherHelper.getMaxMinTemperaturesFromNode(apiResponse);
    }

    void getCurrentTemperatureToFile(String inputFileName, String apiKey) throws IOException {
        int cityId = weatherHelper.getCityIdFromFile(inputFileName, false).get(0);
        Temperature temperature = getCurrentTemperature(cityId, apiKey);
        weatherHelper.writeResultToOutputFile(temperature.toString(), "output.txt");
    }

    void getCoordinatesToFile(String inputFileName, String apiKey) throws IOException {
        int cityId = weatherHelper.getCityIdFromFile(inputFileName, false).get(0);
        Coordinates coordinates = getCoordinates(cityId, apiKey);
        weatherHelper.writeResultToOutputFile(coordinates.toString(), "output.txt");
    }

    void getForecastMaxMinTemperaturesToFile(String inputFileName, String apiKey) throws IOException {
        int cityId = weatherHelper.getCityIdFromFile(inputFileName, false).get(0);
        ForecastMaxMinTemperatures temperatures = getForecastMaxMinTemperatures(cityId, apiKey);
        weatherHelper.writeResultToOutputFile(temperatures.toString(), "output.txt");
    }

    void getBulkWeatherInfoToFiles(String inputFilename, String apiKey) throws IOException {
        List<Integer> cityIds = weatherHelper.getCityIdFromFile(inputFilename, true);
        List<WeatherInfo> cityWeatherInfos = new ArrayList<>();
        for (int cityId : cityIds) {
            JsonNode weatherApiResponse = weatherAPI.weatherQuery(cityId, apiKey);
            JsonNode forecastApiResponse = weatherAPI.forecastQuery(cityId, apiKey);
            Coordinates coordinates = weatherHelper.getCoordinatesFromNode(weatherApiResponse);
            Temperature currentTemperature = weatherHelper.convertKelvinStringToCelsiusTemperature(weatherApiResponse);
            ForecastMaxMinTemperatures maxMinTemperatures = weatherHelper.getMaxMinTemperaturesFromNode(forecastApiResponse);
            String cityName = weatherHelper.getCityNameFromWeatherResponse(weatherApiResponse);
            WeatherInfo cityWeatherInfo = new WeatherInfo(cityName, coordinates, currentTemperature, maxMinTemperatures);
            cityWeatherInfos.add(cityWeatherInfo);
        }
        for (WeatherInfo cityWeatherInfo : cityWeatherInfos) {
            weatherHelper.writeResultToOutputFile(cityWeatherInfo.toString(), cityWeatherInfo.getCityName() + ".txt");
        }
    }
}
