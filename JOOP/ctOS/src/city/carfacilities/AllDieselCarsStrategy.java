package city.carfacilities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllDieselCarsStrategy implements ServedCarStrategy {

    @Override
    public List<ServedCarInformation> getListOfSearchedCars(ArrayList<ServedCarInformation> servedCarInformations) {
        return servedCarInformations.stream().filter(info->info.getEngine().getType().equals("Diesel"))
                .collect(Collectors.toList());
    }
}
