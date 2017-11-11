package praks10.gasstation;

import java.math.BigDecimal;

public class GasTank {

    private BigDecimal remainingPetrol;
    private BigDecimal tankSize;

    public GasTank(BigDecimal tankSize){
        this.tankSize = tankSize;
        remainingPetrol = tankSize;
    }

    public BigDecimal getGas(BigDecimal amount){
        remainingPetrol = remainingPetrol.subtract(amount);
        return amount;
    }

    void refill() {
        remainingPetrol = tankSize;
    }

    public BigDecimal getRemainingPetrol() {
        return remainingPetrol;
    }

    public BigDecimal getTankSize() {
        return tankSize;
    }
}
