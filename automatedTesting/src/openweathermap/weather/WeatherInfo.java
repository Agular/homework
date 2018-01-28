package openweathermap.weather;

public class WeatherInfo {

    private String cityName;
    private Coordinates coordinates;
    private Temperature currentTemperature;
    private ForecastMaxMinTemperatures forecastMaxMinTemperatures;

    public WeatherInfo(String cityName, Coordinates coordinates, Temperature currentTemperature, ForecastMaxMinTemperatures forecastMaxMinTemperatures) {
        this.cityName = cityName;
        this.coordinates = coordinates;
        this.currentTemperature = currentTemperature;
        this.forecastMaxMinTemperatures = forecastMaxMinTemperatures;
    }

    public String getCityName() {
        return cityName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Temperature getCurrentTemperature() {
        return currentTemperature;
    }

    public ForecastMaxMinTemperatures getForecastMaxMinTemperatures() {
        return forecastMaxMinTemperatures;
    }

    @Override
    public String toString() {
        return cityName + "\n" +
                coordinates + "\n" +
                currentTemperature + "\n" +
                forecastMaxMinTemperatures;
    }
}
