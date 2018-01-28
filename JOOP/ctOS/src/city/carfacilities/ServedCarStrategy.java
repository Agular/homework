package city.carfacilities;

import java.util.ArrayList;
import java.util.List;

public interface  ServedCarStrategy {

    List<ServedCarInformation> getListOfSearchedCars(ArrayList<ServedCarInformation> servedCarInformations);
}
