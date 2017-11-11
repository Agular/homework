package kontrolltoo1;

import kontrolltoo1.battery.Battery;
import kontrolltoo1.charger.Charger;
import kontrolltoo1.charger.ChargerFactory;

import java.math.BigDecimal;

public class MainSystem {
    public static void main(String[] args) {

        Charger acCharger1 = ChargerFactory.of("AC");
        Charger acCharger2 = ChargerFactory.of("AC");
        Charger acCharger3 = ChargerFactory.of("AC");
        Charger acCharger4 = ChargerFactory.of("AC");
        Charger acCharger5 = ChargerFactory.of("AC");
        Charger dcCharger1 = ChargerFactory.of("DC");
        Charger dcCharger2 = ChargerFactory.of("DC");
        Charger dcCharger3 = ChargerFactory.of("DC");
        Charger dcCharger4 = ChargerFactory.of("DC");
        Charger dcCharger5 = ChargerFactory.of("DC");

        Battery battery20KW = new Battery(new BigDecimal("20000"));
        Battery battery30KW = new Battery(new BigDecimal("30000"));

        acCharger1.startRegularCharging(battery20KW);
        dcCharger1.startRegularCharging(battery20KW);
        acCharger2.startFastCharging(battery30KW);
        dcCharger2.startFastCharging(battery30KW);
    }


}

