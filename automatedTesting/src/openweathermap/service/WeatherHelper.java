package openweathermap.service;

import com.fasterxml.jackson.databind.JsonNode;
import openweathermap.weather.Coordinates;
import openweathermap.weather.ForecastMaxMinTemperatures;
import openweathermap.weather.MaxMinTemperatures;
import openweathermap.weather.Temperature;

import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class WeatherHelper {

    public Temperature convertKelvinStringToCelsiusTemperature(JsonNode apiResponse) {
        String kelvinString = apiResponse.get("main").get("temp").toString();
        double temperatureKelvin = Double.parseDouble(kelvinString);
        return new Temperature(temperatureKelvin - 273.15);
    }

    public void convertOneDayMaxMinTemperaturesToCelsius(MaxMinTemperatures kelvinTemperature) {
        kelvinTemperature.setMinTemperature(new Temperature(roundValue(kelvinTemperature.getMinTemperature().getValue() - 273.15)));
        kelvinTemperature.setMaxTemperature(new Temperature(roundValue(kelvinTemperature.getMaxTemperature().getValue() - 273.15)));
    }

    public Coordinates getCoordinatesFromNode(JsonNode apiResponse) {
        JsonNode coordinatesNode = apiResponse.get("coord");
        double lon = coordinatesNode.get("lon").asDouble();
        double lat = coordinatesNode.get("lat").asDouble();
        return new Coordinates(lon, lat);
    }

    public ForecastMaxMinTemperatures getMaxMinTemperaturesFromNode(JsonNode apiResponse) {
        List<MaxMinTemperatures> temperaturesList = new ArrayList<>();
        Iterator<JsonNode> listIterator = apiResponse.get("list").elements();
        MaxMinTemperatures maxMinTemperatures = null;
        while (listIterator.hasNext()) {
            JsonNode threeHourNode = listIterator.next();
            String nodeDateString = getDateStringFromNode(threeHourNode);
            if (maxMinTemperatures == null) {
                maxMinTemperatures = new MaxMinTemperatures(LocalDate.parse(nodeDateString),
                        new Temperature(Double.MIN_VALUE), new Temperature(Double.MAX_VALUE));
            } else if (!maxMinTemperatures.getDate().equals(LocalDate.parse(nodeDateString))) {
                temperaturesList.add(maxMinTemperatures);
                maxMinTemperatures = new MaxMinTemperatures(LocalDate.parse(nodeDateString),
                        new Temperature(Double.MIN_VALUE), new Temperature(Double.MAX_VALUE));
            }
            double nodeTemperature = threeHourNode.get("main").get("temp").asDouble();
            if (maxMinTemperatures.getMinTemperature().getValue() > nodeTemperature) {
                maxMinTemperatures.setMinTemperature(new Temperature(nodeTemperature));
            }
            if (maxMinTemperatures.getMaxTemperature().getValue() < nodeTemperature) {
                maxMinTemperatures.setMaxTemperature(new Temperature(nodeTemperature));
            }
        }
        temperaturesList.add(maxMinTemperatures);
        Collections.sort(temperaturesList, new Comparator<MaxMinTemperatures>() {
            @Override
            public int compare(MaxMinTemperatures o1, MaxMinTemperatures o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        convertKelvinTemperaturesListToCelsiusTemperaturesList(temperaturesList);
        return new ForecastMaxMinTemperatures(temperaturesList);
    }

    public String getDateStringFromNode(JsonNode node) {
        return node.get("dt_txt").toString().split(" ")[0].replace("\"", "");
    }

    public void convertKelvinTemperaturesListToCelsiusTemperaturesList(List<MaxMinTemperatures> temperaturesList) {
        for (int i = 0; i < temperaturesList.size(); i++) {
            convertOneDayMaxMinTemperaturesToCelsius(temperaturesList.get(i));
        }
    }

    public double roundValue(double value) {
        double temp = value * 100;
        temp = Math.round(temp);
        return temp / 100;
    }

    public void writeResultToOutputFile(String result, String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.print(result);
        writer.close();
    }

    public List<Integer> getCityIdFromFile(String fileName, boolean multiple) throws FileNotFoundException {
        File file = new File(fileName);
        BufferedReader reader;
        List<String> cityStringIds = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File was not found. Check your path and file name!");
        }
        if (multiple) {
            String cityId;
            try {
                while ((cityId = reader.readLine()) != null) {
                    cityStringIds.add(cityId);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                String cityId = reader.readLine();
                if (cityId == null) {
                    throw new IllegalArgumentException("File is empty!");
                }
                cityStringIds.add(cityId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (cityStringIds.isEmpty()) {
            throw new IllegalArgumentException("The file is empty!");
        }
        for (String cityId : cityStringIds) {
            if (!cityId.matches("^\\d+$")) {
                throw new IllegalArgumentException("City id is not numeric!");
            }
        }
        List<Integer> cityIntegerIds = new ArrayList<>();
        for (String cityStringId : cityStringIds) {
            cityIntegerIds.add(Integer.parseInt(cityStringId));
        }
        return cityIntegerIds;
    }

    public String getCityNameFromWeatherResponse(JsonNode weatherApiResponse) {
        return weatherApiResponse.get("name").toString().replace("\"", "");
    }
}
