package kodutoo9.travel;

import java.util.function.DoubleFunction;

public class Destination implements DestinationModel {

    private String name;
    private double temperature;
    private DestinationInfo destinationInfo;
    private final static double KELVIN_MIN_VALUE = -273.15;

    public Destination() {
    }

    public Destination(String name, double temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getKelvin() {
        return temperature;
    }

    @Override
    public double getAvgWeather(DoubleFunction<Double> tempHandler) {
        return tempHandler.apply(temperature);
    }

    public double getAvgWeatherInCelsius() {
        return temperature + KELVIN_MIN_VALUE;
    }

    public double getAvgWeatherInFahrenheit() {
        return (9.0 / 5.0) * temperature - 459.67;
    }

    public static double convertFromKevinToCelsius(double temperature) {
        return temperature + KELVIN_MIN_VALUE;
    }

    public static double convertFromKevinToFahrenheit(double temperature) {
        return (9.0 / 5.0) * temperature - 459.67;
    }

    public DestinationInfo getDestinationInfo() {
        return destinationInfo;
    }

    public void setDestinationInfo(DestinationInfo destinationInfo) {
        this.destinationInfo = destinationInfo;
    }

    public int getArea(){
        return destinationInfo.getArea();
    }

    public String getCurrency(){
        return destinationInfo.getCurrency();
    }

    public String getCurrencyCode(){
        return destinationInfo.getCurrencyCode();
    }

    public int getGDPperCapita(){
        return destinationInfo.getGDPperCapita();
    }

    @Override
    public String toString() {
        return "########################################\n" +
                "Name: " + name + "\n" +
                "Temperature: " + temperature + " K\n" +
                "Area: " + destinationInfo.getArea() + "\n" +
                "Currency: " + destinationInfo.getCurrency() + "\n" +
                "Currency code: " + destinationInfo.getCurrencyCode() + "\n" +
                "GDP per capita :" + destinationInfo.getGDPperCapita();
    }
}
