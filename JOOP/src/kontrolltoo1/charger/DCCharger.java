package kontrolltoo1.charger;

import kontrolltoo1.battery.Battery;

import java.math.BigDecimal;

public class DCCharger extends ACCharger {

    private BigDecimal fastChargerModifier = new BigDecimal("0.8");

    DCCharger(BigDecimal regularChargingPower, BigDecimal fastChargingPower) {
        super(regularChargingPower, fastChargingPower);
    }

    @Override
    public String getType() {
        return "DC";
    }

    @Override
    public void startFastCharging(Battery battery) {
        if(super.chargerIsInStandbyMode()){
            super.currentMode = super.FAST_CHARGING;
            if(super.batterCapacityIsGreaterThanFastChargingPower(battery)){
                super.usedPowerWhileFastCharging = super.fastChargingPower;
            } else {
                super.usedPowerWhileFastCharging = fastChargerModifier.multiply(super.fastChargingPower);
            }
        } else throw new IllegalStateException();

    }
}
