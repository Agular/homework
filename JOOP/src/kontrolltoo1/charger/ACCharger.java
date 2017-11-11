package kontrolltoo1.charger;

import kontrolltoo1.battery.Battery;

import java.math.BigDecimal;

public class ACCharger implements Charger {

    final int STANDBY_MODE = 0;
    final int REGULAR_CHARGING = 1;
    final int FAST_CHARGING = 2;

    int currentMode;
    BigDecimal regularChargingPower;
    BigDecimal fastChargingPower;

    BigDecimal usedPowerWhileRegularCharging;
    BigDecimal usedPowerWhileFastCharging;

    ACCharger(BigDecimal regularChargingPower, BigDecimal fastChargingPower) {
        this.regularChargingPower = regularChargingPower;
        this.fastChargingPower = fastChargingPower;
        currentMode = STANDBY_MODE;
        usedPowerWhileRegularCharging = BigDecimal.ZERO;
        usedPowerWhileFastCharging = BigDecimal.ZERO;
    }

    public void startRegularCharging(Battery battery) {
        if (chargerIsInStandbyMode()) {
            currentMode = REGULAR_CHARGING;
            usedPowerWhileRegularCharging = regularChargingPower;
        } else {
            throw new IllegalStateException();
        }
    }

    public void startFastCharging(Battery battery) {
        if (chargerIsInStandbyMode()) {
            currentMode = FAST_CHARGING;
            if (batterCapacityIsGreaterThanFastChargingPower(battery)) {
                usedPowerWhileFastCharging = fastChargingPower;
            } else {
                usedPowerWhileFastCharging = battery.getCapacity();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public void stopRegularCharging() {
        currentMode = STANDBY_MODE;
        usedPowerWhileRegularCharging = BigDecimal.ZERO;
    }

    public void stopFastCharging(Battery battery) {
        currentMode = STANDBY_MODE;
        usedPowerWhileRegularCharging = BigDecimal.ZERO;
    }

    public BigDecimal getUsedPowerWhileRegularCharging() {
        return usedPowerWhileRegularCharging;
    }

    public BigDecimal getUsedPowerWhileFastCharging() {
        return usedPowerWhileFastCharging;
    }

    @Override
    public String getType() {
        return "AC";
    }

    @Override
    public BigDecimal getRegularChargingPower() {
        return regularChargingPower;
    }

    @Override
    public BigDecimal getFastChargingPower() {
        return fastChargingPower;
    }

    boolean chargerIsInStandbyMode() {
        return currentMode == STANDBY_MODE;
    }

    boolean batterCapacityIsGreaterThanRegularChargingPower(Battery battery) {
        return battery.getCapacity().compareTo(regularChargingPower) == 1;
    }

    boolean batterCapacityIsGreaterThanFastChargingPower(Battery battery) {
        return battery.getCapacity().compareTo(fastChargingPower) == 1;
    }

    public String getCurrentMode() {
        if(currentMode == STANDBY_MODE){
         return "STANDBY_MODE";
        } else if (currentMode == REGULAR_CHARGING){
            return "REGULAR_CHARGING";
        } else {
            return "FAST_CHARGING";
        }
    }
}
