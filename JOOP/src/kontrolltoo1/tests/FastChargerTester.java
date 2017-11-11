package kontrolltoo1.tests;

import kontrolltoo1.battery.Battery;
import kontrolltoo1.charger.Charger;
import kontrolltoo1.charger.ChargerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class FastChargerTester {

    private Charger acCharger;
    private Charger dcCharger;

    @Before
    public void setupBeforeEachTest() {
        acCharger = ChargerFactory.of("AC");
        dcCharger = ChargerFactory.of("DC");
    }

    @Test
    public void acChargerFastChargingUsesNominalPowerWithBigBattery(){
        Battery bigBattery = new Battery(new BigDecimal("23000"));
        acCharger.startFastCharging(bigBattery);
        Assert.assertEquals(new BigDecimal("22000"), acCharger.getUsedPowerWhileFastCharging());
    }

    @Test
    public void acChargerFastChargingUsesBatteryPowerWithSmallBattery(){
        Battery bigBattery = new Battery(new BigDecimal("20000"));
        acCharger.startFastCharging(bigBattery);
        Assert.assertEquals(new BigDecimal("20000"), acCharger.getUsedPowerWhileFastCharging());
    }
}
