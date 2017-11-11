package kodutoo9;

import kodutoo9.travel.Destination;
import kodutoo9.travel.DestinationInfo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Destination> destinations = Files.lines(Paths.get("src/kodutoo9/states.csv"), StandardCharsets.UTF_8)
                .map(line -> line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"))
                .map(state -> {
                    Destination destination;
                    if (state[2].equals("")) {
                        destination = new Destination(state[0], 299);
                    } else {
                        destination = new Destination(state[0], Double.parseDouble(state[2]) / 100 + 273);
                    }
                    int gdp;
                    if (state[16].equals(" ")) {
                        gdp = 0;
                    } else {
                        gdp = Integer.parseInt(state[16]);
                    }
                    destination.setDestinationInfo(new DestinationInfo(Integer.parseInt(state[12]), state[8], state[9], gdp));
                    return destination;
                }).collect(Collectors.toList());


        IntSummaryStatistics statsGDPAllCountries = destinations.stream().filter(d -> d.getGDPperCapita() != 0)
                .collect(Collectors.summarizingInt(Destination::getGDPperCapita));
        System.out.println("Average GDP all countries: " + statsGDPAllCountries.getAverage());

        IntSummaryStatistics statsGDPEuroCountries = destinations.stream().filter(d -> d.getCurrency().equals("Euro")).filter(d -> d.getGDPperCapita() != 0)
                .collect(Collectors.summarizingInt(Destination::getGDPperCapita));
        System.out.println("Average GDP euro countries: " + statsGDPEuroCountries.getAverage());
        System.out.println("Minimum GDP euro countries: " + statsGDPEuroCountries.getMin());
        System.out.println("Maximum GDP euro countries: " + statsGDPEuroCountries.getMax());

        long numberOfCurrenciesWithAreaLessThan100000Km2 = destinations.stream().filter(d-> d.getArea() < 100000)
                .map(Destination::getCurrency)
                .distinct()
                .count();
        System.out.println("Number of Distinct currencies used in Countries with area less than 100 000km2: " + numberOfCurrenciesWithAreaLessThan100000Km2);

        System.out.println("Average weather of all countries in Réaumur scale");
        destinations.stream().map(d->d.getAvgWeather(t -> t - 273.15) * 0.8).forEach(System.out::println);
    }
}
