package kontrolltoo1.charger;

import kontrolltoo1.battery.Battery;

import java.math.BigDecimal;

public interface Charger {

    String getType();

    BigDecimal getRegularChargingPower();

    BigDecimal getFastChargingPower();

    void startRegularCharging(Battery battery);

    void startFastCharging(Battery battery);

    void stopRegularCharging();

    void stopFastCharging(Battery battery);

    BigDecimal getUsedPowerWhileRegularCharging();

    BigDecimal getUsedPowerWhileFastCharging();

    String getCurrentMode();

}
