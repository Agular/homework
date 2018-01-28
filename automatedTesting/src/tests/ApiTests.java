package tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import openweathermap.service.WeatherAPI;
import openweathermap.service.WeatherClient;
import openweathermap.service.WeatherDAO;
import openweathermap.service.WeatherHelper;
import openweathermap.weather.Coordinates;
import openweathermap.weather.ForecastMaxMinTemperatures;
import openweathermap.weather.MaxMinTemperatures;
import openweathermap.weather.Temperature;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApiTests {
    private final int CITY_ID = 588409;
    private final String API_KEY = "fde9c3d325025e6e3e3fc555c0f71b9c";
    private WeatherAPI weatherApi;
    private WeatherClient weatherClient;

    @Before
    public void setupBeforeEachTest() {

        weatherApi = new WeatherAPI();
        WeatherDAO weatherDAO = new WeatherDAO(weatherApi, new WeatherHelper());
        weatherClient = new WeatherClient(API_KEY, weatherDAO);
    }

    @Test
    public void isInputReceiveSuccessful() throws IOException {
        Assert.assertNotEquals(null, weatherApi.forecastQuery(CITY_ID, API_KEY));
    }

    @Test
    public void isCurrentTempDouble() throws IOException {
        JsonNode node = weatherApi.weatherQuery(CITY_ID, API_KEY);
        Assert.assertTrue(node.get("main").get("temp").isDouble());
    }

    @Test
    public void isGeoLocationLongitudeDouble() throws IOException {
        JsonNode node = weatherApi.weatherQuery(CITY_ID, API_KEY);
        Assert.assertTrue(node.get("coord").get("lon").isDouble());
    }

    @Test
    public void isGeoLocationLatitudeDouble() throws IOException {
        JsonNode node = weatherApi.weatherQuery(CITY_ID, API_KEY);
        Assert.assertTrue(node.get("coord").get("lat").isDouble());
    }

    @Test(expected = FileNotFoundException.class)
    public void getCityIdFromFileThrowsFileNotFoundException() throws IOException {
        weatherClient.getCoordinatesByCityIdFromTextFile("unfindable_textfile.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCityIDFromFileWhenFileIsEmptyThrowsIllegalArgumentException() throws IOException {
        weatherClient.getCoordinatesByCityIdFromTextFile("empty_file.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCityIDFromFileStringIsNotNumericThrowsIllegalArgumentException() throws IOException {
        weatherClient.getCoordinatesByCityIdFromTextFile("hasNotNumericString.txt");
    }

    @Test()
    public void getGeoLocationWithCityIdFromTextFileSuccessfulQuery() throws IOException {
        weatherClient.getCoordinatesByCityIdFromTextFile("input.txt");
    }

    @Test()
    public void getCoordinatesWithCityIdFromFileCorrectOutput() throws IOException {
        weatherClient.getCoordinatesByCityIdFromTextFile("input.txt");
        BufferedReader br = new BufferedReader(new FileReader("output.txt"));
        String output = br.readLine();
        br.close();
        Assert.assertEquals("lon:24.75 lat:59.44", output);
    }

    @Test
    public void getTemperatureWithCityIdFromFileCorrectOutput() throws IOException {
        weatherClient.getCurrentTemperatureByCityIdFromTextFile("input.txt");
        BufferedReader br = new BufferedReader(new FileReader("output.txt"));
        String output = br.readLine();
        br.close();
        Assert.assertTrue(output.matches("^temp:[0-9]+(\\.[0-9]+)$"));

    }

    @Test
    public void mockFileSuccessfulReadOnGetTemperatureFromFile() throws IOException {
        WeatherHelper helper = mock(WeatherHelper.class);
        ArgumentCaptor<JsonNode> apiResponseCaptor = ArgumentCaptor.forClass(JsonNode.class);
        when(helper.convertKelvinStringToCelsiusTemperature(apiResponseCaptor.capture())).thenReturn(new Temperature(15.0));
        when(helper.getCityIdFromFile(eq("input.txt"), eq(false))).thenReturn(Collections.singletonList(588409));

        WeatherDAO dao = new WeatherDAO(new WeatherAPI(), helper);
        WeatherClient client = new WeatherClient(API_KEY, dao);

        client.getCurrentTemperatureByCityIdFromTextFile("input.txt");

        verify(helper).getCityIdFromFile(eq("input.txt"), eq(false));
    }

    @Test
    public void mockFileSuccessfulReadOnGetCoordinatesFromFile() throws IOException {
        WeatherHelper helper = mock(WeatherHelper.class);
        ArgumentCaptor<JsonNode> apiResponseCaptor = ArgumentCaptor.forClass(JsonNode.class);
        when(helper.getCoordinatesFromNode(apiResponseCaptor.capture())).thenReturn(new Coordinates(10.0, 10.0));
        when(helper.getCityIdFromFile(eq("input.txt"), eq(false))).thenReturn(Collections.singletonList(588409));

        WeatherDAO dao = new WeatherDAO(new WeatherAPI(), helper);
        WeatherClient client = new WeatherClient(API_KEY, dao);

        client.getCoordinatesByCityIdFromTextFile("input.txt");

        verify(helper).getCityIdFromFile(eq("input.txt"), eq(false));
    }

    @Test
    public void mockFileSuccessfulReadOnGetForecastFromFile() throws IOException {
        WeatherHelper helper = mock(WeatherHelper.class);
        ArgumentCaptor<JsonNode> apiResponseCaptor = ArgumentCaptor.forClass(JsonNode.class);
        ForecastMaxMinTemperatures temperatures = new ForecastMaxMinTemperatures(
                Collections.singletonList(new MaxMinTemperatures(LocalDate.now(), new Temperature(10.0), new Temperature(10.0)))
        );

        when(helper.getMaxMinTemperaturesFromNode(apiResponseCaptor.capture())).thenReturn(temperatures);
        when(helper.getCityIdFromFile(eq("input.txt"), eq(false))).thenReturn(Collections.singletonList(588409));

        WeatherDAO dao = new WeatherDAO(new WeatherAPI(), helper);
        WeatherClient client = new WeatherClient(API_KEY, dao);

        client.getForecastMaxMinTemperaturesByCityIdFromTextFile("input.txt");

        verify(helper).getCityIdFromFile(eq("input.txt"), eq(false));
    }


    @Test
    public void mockFileSuccessfulReadOnGetBulkInfoFromFile() throws IOException {
        WeatherHelper helper = mock(WeatherHelper.class);
        ArgumentCaptor<JsonNode> apiResponseCaptor = ArgumentCaptor.forClass(JsonNode.class);

        when(helper.getCityIdFromFile(eq("input.txt"), eq(true))).thenReturn(Arrays.asList(588409, 588409));
        ForecastMaxMinTemperatures temperatures = new ForecastMaxMinTemperatures(
                Collections.singletonList(new MaxMinTemperatures(LocalDate.now(), new Temperature(10.0), new Temperature(10.0)))
        );
        when(helper.getMaxMinTemperaturesFromNode(apiResponseCaptor.capture())).thenReturn(temperatures);
        when(helper.convertKelvinStringToCelsiusTemperature(apiResponseCaptor.capture())).thenReturn(new Temperature(15.0));
        when(helper.getCoordinatesFromNode(apiResponseCaptor.capture())).thenReturn(new Coordinates(10.0, 10.0));

        WeatherDAO dao = new WeatherDAO(new WeatherAPI(), helper);
        WeatherClient client = new WeatherClient(API_KEY, dao);

        client.getBulkWeatherInfoFromTextFileToTextFiles("inputBulk.txt");
        verify(helper).getCityIdFromFile(eq("inputBulk.txt"), eq(true));
    }

    @Test
    public void mockFileSuccessfulWriteOnGetTemperatureFromFile() throws IOException {
        WeatherHelper helper = mock(WeatherHelper.class);
        ArgumentCaptor<JsonNode> apiResponseCaptor = ArgumentCaptor.forClass(JsonNode.class);
        when(helper.convertKelvinStringToCelsiusTemperature(apiResponseCaptor.capture())).thenReturn(new Temperature(15.0));
        when(helper.getCityIdFromFile(eq("input.txt"), eq(false))).thenReturn(Collections.singletonList(588409));
        WeatherDAO dao = new WeatherDAO(new WeatherAPI(), helper);
        WeatherClient client = new WeatherClient(API_KEY, dao);
        client.getCurrentTemperatureByCityIdFromTextFile("input.txt");
        verify(helper).writeResultToOutputFile(eq("temp:15.0"), eq("output.txt"));
    }

    @Test
    public void mockFileSuccessfulWriteOnGetCoordinatesFromFile() throws IOException {
        WeatherHelper helper = mock(WeatherHelper.class);
        ArgumentCaptor<JsonNode> apiResponseCaptor = ArgumentCaptor.forClass(JsonNode.class);
        when(helper.getCoordinatesFromNode(apiResponseCaptor.capture())).thenReturn(new Coordinates(10.0, 10.0));
        when(helper.getCityIdFromFile(eq("input.txt"), eq(false))).thenReturn(Collections.singletonList(588409));
        WeatherDAO dao = new WeatherDAO(new WeatherAPI(), helper);
        WeatherClient client = new WeatherClient(API_KEY, dao);
        client.getCoordinatesByCityIdFromTextFile("input.txt");
        verify(helper).writeResultToOutputFile(eq("lon:10.0 lat:10.0"), eq("output.txt"));
    }

    @Test
    public void mockFileSuccessfulWriteOnGetForecastFromFile() throws IOException {
        WeatherHelper helper = mock(WeatherHelper.class);
        ArgumentCaptor<JsonNode> apiResponseCaptor = ArgumentCaptor.forClass(JsonNode.class);
        ForecastMaxMinTemperatures temperatures = new ForecastMaxMinTemperatures(
                Collections.singletonList(new MaxMinTemperatures(LocalDate.of(2000, 12, 12), new Temperature(10.0), new Temperature(10.0)))
        );
        when(helper.getMaxMinTemperaturesFromNode(apiResponseCaptor.capture())).thenReturn(temperatures);
        when(helper.getCityIdFromFile(eq("input.txt"), eq(false))).thenReturn(Collections.singletonList(588409));
        WeatherDAO dao = new WeatherDAO(new WeatherAPI(), helper);
        WeatherClient client = new WeatherClient(API_KEY, dao);
        client.getForecastMaxMinTemperaturesByCityIdFromTextFile("input.txt");
        verify(helper).writeResultToOutputFile(eq("date:2000-12-12 max:10.0 min:10.0"), eq("output.txt"));
    }

    @Test
    public void mockFileSuccessfulWriteOnGetBulkInfoFromFile() throws IOException {
        WeatherHelper helper = mock(WeatherHelper.class);
        ArgumentCaptor<JsonNode> apiResponseCaptor = ArgumentCaptor.forClass(JsonNode.class);

        when(helper.getCityIdFromFile(eq("inputBulk.txt"), eq(true))).thenReturn(Arrays.asList(588409));
        ForecastMaxMinTemperatures temperatures = new ForecastMaxMinTemperatures(
                Collections.singletonList(new MaxMinTemperatures(LocalDate.of(2017, 12, 20), new Temperature(10.0), new Temperature(10.0)))
        );
        when(helper.getMaxMinTemperaturesFromNode(apiResponseCaptor.capture())).thenReturn(temperatures);
        when(helper.convertKelvinStringToCelsiusTemperature(apiResponseCaptor.capture())).thenReturn(new Temperature(15.0));
        when(helper.getCoordinatesFromNode(apiResponseCaptor.capture())).thenReturn(new Coordinates(10.0, 10.0));
        when(helper.getCityNameFromWeatherResponse(apiResponseCaptor.capture())).thenReturn("Tallinn");

        WeatherDAO dao = new WeatherDAO(new WeatherAPI(), helper);
        WeatherClient client = new WeatherClient(API_KEY, dao);

        client.getBulkWeatherInfoFromTextFileToTextFiles("inputBulk.txt");
        verify(helper).writeResultToOutputFile(
                eq("Tallinn\n" +
                        "lon:10.0 lat:10.0\n" +
                        "temp:15.0\n" +
                        "date:2017-12-20 max:10.0 min:10.0"), eq("Tallinn.txt"));
    }

    @Test
    public void ForecastMaxMinTemperaturesCorrectToString() throws IOException {
        List<MaxMinTemperatures> temperaturesList = new ArrayList<>();
        temperaturesList.add(new MaxMinTemperatures(LocalDate.of(2017,11,20), new Temperature(10.0), new Temperature(10.0)));
        temperaturesList.add(new MaxMinTemperatures(LocalDate.of(2017,11,21), new Temperature(11.0), new Temperature(9.0)));
        temperaturesList.add(new MaxMinTemperatures(LocalDate.of(2017,11,22), new Temperature(12.0), new Temperature(8.0)));
        temperaturesList.add(new MaxMinTemperatures(LocalDate.of(2017,11,23), new Temperature(13.0), new Temperature(7.0)));
        temperaturesList.add(new MaxMinTemperatures(LocalDate.of(2017,11,24), new Temperature(14.0), new Temperature(6.0)));
        ForecastMaxMinTemperatures temperatures = new ForecastMaxMinTemperatures(temperaturesList);
        Assert.assertEquals("date:2017-11-20 max:10.0 min:10.0\n" +
                        "date:2017-11-21 max:11.0 min:9.0\n" +
                        "date:2017-11-22 max:12.0 min:8.0\n" +
                        "date:2017-11-23 max:13.0 min:7.0\n" +
                        "date:2017-11-24 max:14.0 min:6.0", temperatures.toString());
    }

    @Test
    public void getCoordinatesUsesWeatherQueryMockedAPI() throws IOException {
        WeatherAPI api = mock(WeatherAPI.class);
        WeatherDAO dao = new WeatherDAO(api, new WeatherHelper());
        WeatherClient client = new WeatherClient(API_KEY, dao);
        when(api.weatherQuery(eq(CITY_ID), eq(API_KEY))).thenReturn(
                new ObjectMapper().readTree(
                        "{\"coord\":{\"lon\":21.5,\"lat\":22.0}}"
                )
        );
        client.getCoordinatesByCityId(CITY_ID);
        verify(api).weatherQuery(eq(CITY_ID), eq(API_KEY));
    }

    @Test
    public void getForecastUsesForecastQueryMockedAPI() throws IOException {
        WeatherAPI api = mock(WeatherAPI.class);
        WeatherHelper helper = mock(WeatherHelper.class);
        WeatherDAO dao = new WeatherDAO(api, new WeatherHelper());
        WeatherClient client = new WeatherClient(API_KEY, dao);
        when(api.forecastQuery(eq(CITY_ID), eq(API_KEY))).thenReturn(
                new ObjectMapper().readTree(
                 "{\"list\":[{\"main\":{\"temp\":261.45},\"dt_txt\":\"2017-01-30 18:00:00\"}," +
                         "{\"main\":{\"temp\":261.45},\"dt_txt\":\"2017-01-30 21:00:00\"}]}")
        );
        ArgumentCaptor<JsonNode> apiResponseCaptor = ArgumentCaptor.forClass(JsonNode.class);
        when(helper.getDateStringFromNode(apiResponseCaptor.capture())).thenReturn("2017-01-30");

        client.getForecastMaxMinTemperaturesByCityId(CITY_ID);
        verify(api).forecastQuery(eq(CITY_ID), eq(API_KEY));
    }
}
