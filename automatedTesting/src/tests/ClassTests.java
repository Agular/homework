package tests;

import org.junit.Assert;
import org.junit.Test;
import openweathermap.weather.Coordinates;
import openweathermap.weather.ForecastMaxMinTemperatures;
import openweathermap.weather.MaxMinTemperatures;
import openweathermap.weather.Temperature;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassTests {

    @Test
    public void isCoordinatesEqualsCorrect(){
        Assert.assertEquals(new Coordinates(2.0,2.0), new Coordinates(2.0, 2.0));
    }

    @Test
    public void isCoordinatesHashCorrect(){
        Assert.assertEquals(-33553471, new Coordinates(1.0, 1.0).hashCode());
    }

    @Test
    public void isTemperatureEqualsCorrect(){
        Assert.assertEquals(new Temperature(1.0), new Temperature(1.0));
    }

    @Test
    public void isTemperatureHashCorrect(){
        Assert.assertEquals(1072693279,new Temperature(1.0).hashCode());
    }

    @Test
    public void isOneDayTemperaturesEqualsCorrect(){
        MaxMinTemperatures first = new MaxMinTemperatures(LocalDate.now(), new Temperature(1.0), new Temperature(1.0));
        MaxMinTemperatures second = new MaxMinTemperatures(LocalDate.now(), new Temperature(1.0), new Temperature(1.0));
        Assert.assertEquals(first, second);
    }

    @Test
    public void isOneDayTemperaturesHashCorrect(){
        MaxMinTemperatures temperatures = new MaxMinTemperatures(LocalDate.of(1996, 1, 12), new Temperature(1.0), new Temperature(1.0));

        Assert.assertEquals(-400034421, temperatures.hashCode());
    }

    @Test
    public void isFiveDayTemperaturesEqualsCorrect()  {
        List<MaxMinTemperatures> temperaturesList1 = new ArrayList<>();
        MaxMinTemperatures temp1 = new MaxMinTemperatures(LocalDate.of(2017,10, 23), new Temperature(4.23), new Temperature(0.41));
        MaxMinTemperatures temp2 = new MaxMinTemperatures(LocalDate.of(2017,10, 24), new Temperature(4.65), new Temperature(-1.43));
        MaxMinTemperatures temp3 = new MaxMinTemperatures(LocalDate.of(2017,10, 25), new Temperature(1.06), new Temperature(-1.55));
        MaxMinTemperatures temp4 = new MaxMinTemperatures(LocalDate.of(2017,10, 26), new Temperature(4.19), new Temperature(0.54));
        MaxMinTemperatures temp5 = new MaxMinTemperatures(LocalDate.of(2017,10, 27), new Temperature(3.65), new Temperature(1.93));
        temperaturesList1.add(temp1);
        temperaturesList1.add(temp2);
        temperaturesList1.add(temp3);
        temperaturesList1.add(temp4);
        temperaturesList1.add(temp5);

        List<MaxMinTemperatures> temperaturesList2 = new ArrayList<>();
        MaxMinTemperatures temp21 = new MaxMinTemperatures(LocalDate.of(2017,10, 23), new Temperature(4.23), new Temperature(0.41));
        MaxMinTemperatures temp22 = new MaxMinTemperatures(LocalDate.of(2017,10, 24), new Temperature(4.65), new Temperature(-1.43));
        MaxMinTemperatures temp23 = new MaxMinTemperatures(LocalDate.of(2017,10, 25), new Temperature(1.06), new Temperature(-1.55));
        MaxMinTemperatures temp24 = new MaxMinTemperatures(LocalDate.of(2017,10, 26), new Temperature(4.19), new Temperature(0.54));
        MaxMinTemperatures temp25 = new MaxMinTemperatures(LocalDate.of(2017,10, 27), new Temperature(3.65), new Temperature(1.93));
        temperaturesList2.add(temp21);
        temperaturesList2.add(temp22);
        temperaturesList2.add(temp23);
        temperaturesList2.add(temp24);
        temperaturesList2.add(temp25);

        Assert.assertEquals(new ForecastMaxMinTemperatures(temperaturesList1), new ForecastMaxMinTemperatures(temperaturesList2));
    }

    @Test
    public void isFiveDayTemperaturesHashCorrect() {
        List<MaxMinTemperatures> temperaturesList1 = new ArrayList<>();
        MaxMinTemperatures temp1 = new MaxMinTemperatures(LocalDate.of(2017,10, 23), new Temperature(4.23), new Temperature(0.41));
        MaxMinTemperatures temp2 = new MaxMinTemperatures(LocalDate.of(2017,10, 24), new Temperature(4.65), new Temperature(-1.43));
        MaxMinTemperatures temp3 = new MaxMinTemperatures(LocalDate.of(2017,10, 25), new Temperature(1.06), new Temperature(-1.55));
        MaxMinTemperatures temp4 = new MaxMinTemperatures(LocalDate.of(2017,10, 26), new Temperature(4.19), new Temperature(0.54));
        MaxMinTemperatures temp5 = new MaxMinTemperatures(LocalDate.of(2017,10, 27), new Temperature(3.65), new Temperature(1.93));
        temperaturesList1.add(temp1);
        temperaturesList1.add(temp2);
        temperaturesList1.add(temp3);
        temperaturesList1.add(temp4);
        temperaturesList1.add(temp5);

        Assert.assertEquals(-148432624, new ForecastMaxMinTemperatures(temperaturesList1).hashCode());
    }

}
