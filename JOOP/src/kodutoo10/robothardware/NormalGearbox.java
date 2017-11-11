package kodutoo10.robothardware;

import java.math.BigInteger;

public class NormalGearbox implements Gearbox {

    private final BigInteger highestGear;
    private BigInteger currentGear;

    public NormalGearbox(BigInteger highestGear) {
        this.highestGear = highestGear;
        currentGear = BigInteger.ZERO;
    }

    @Override
    public void setGear(BigInteger newGear) {
        if (!newGearIsWithinBounds(newGear)) {
            throw new IllegalArgumentException("New gear is not within accepted bounds!");
        }
        currentGear = newGear;
    }

    private boolean newGearIsWithinBounds(BigInteger newGear) {
        return newGear.compareTo(BigInteger.ZERO) >= 0 && newGear.compareTo(highestGear) <= 1;
    }

    @Override
    public void shiftUp() {
        if (currentGear.compareTo(highestGear) != 0) {
            currentGear = currentGear.add(BigInteger.ONE);
        }
    }

    @Override
    public void shiftDown() {
        if (currentGear.compareTo(BigInteger.ZERO) != 0) {
            currentGear = currentGear.subtract(BigInteger.ONE);
        }
    }

    @Override
    public void shiftToNeutral() {
        currentGear = BigInteger.ZERO;
    }

    @Override
    public void shiftToLowest() {
        currentGear = BigInteger.ONE;
    }

    @Override
    public void shiftToHighest() {
        currentGear = highestGear;
    }

    public BigInteger getCurrentGear() {
        return currentGear;
    }

    @Override
    public String toString(){
        return "Current gear: " + currentGear + " Highest gear: " + highestGear;
    }
}
