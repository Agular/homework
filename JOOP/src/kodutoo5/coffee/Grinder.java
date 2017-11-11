package kodutoo5.coffee;

import kodutoo5.exceptions.GrinderNotCleanException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;

public class Grinder {

    int calloutCounter;
    private int energySavingModeMinutes;
    private BigDecimal primaryBeanRatio;
    private BigDecimal secondaryBeanRatio;
    private BigDecimal primaryBeansInGrams;
    private BigDecimal secondaryBeansInGrams;

    private final BigDecimal COFFEE_BEANS_PER_CUP_IN_GRAMS = new BigDecimal("10.00").setScale(2, RoundingMode.HALF_UP);


    public Grinder() {
        calloutCounter = 0;
        energySavingModeMinutes = 0;
        primaryBeanRatio = new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP);
        secondaryBeanRatio = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
        primaryBeansInGrams = new BigDecimal("150.0").setScale(2, RoundingMode.HALF_UP);
        secondaryBeansInGrams = new BigDecimal("85.00").setScale(2, RoundingMode.HALF_UP);
    }



    public double grind() throws GrinderNotCleanException {
        if (calloutCounter == 3) {
            throw new GrinderNotCleanException();
        }
        if(this.isInEnergySavingMode()){
            this.exitEnergySavingMode();
        }

        if(hasEnoughBeansInContainers()){
            takeBeansFromContainers();
            calloutCounter++;
            return 1.0;
        } else{
            throw new IllegalStateException("Not enough beans!");
        }

    }

    private boolean hasEnoughBeansInContainers() {
        return primaryBeanRatio.multiply(COFFEE_BEANS_PER_CUP_IN_GRAMS).compareTo(primaryBeansInGrams) <=0 &&
                secondaryBeanRatio.multiply(COFFEE_BEANS_PER_CUP_IN_GRAMS).compareTo(secondaryBeansInGrams) <=0;
    }


    private void takeBeansFromContainers() {
       primaryBeansInGrams = primaryBeansInGrams.subtract(primaryBeanRatio.multiply(COFFEE_BEANS_PER_CUP_IN_GRAMS));
       secondaryBeansInGrams = secondaryBeansInGrams.subtract(secondaryBeanRatio.multiply(COFFEE_BEANS_PER_CUP_IN_GRAMS));
    }

    public int getCalloutCounter() {
        return calloutCounter;
    }

    public void clean() {
        calloutCounter = 0;
    }


    public void setEnergySavingModeWithMinutes(int i) {
        if (i < 1 || i > 60) {
            throw new IllegalArgumentException("Minutes must be between 1 and 60!");
        }
        energySavingModeMinutes = i;
    }

    public boolean isInEnergySavingMode() {
        return energySavingModeMinutes != 0;
    }

    public void exitEnergySavingMode() {
        energySavingModeMinutes = 0;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Grinder) {
            return grinderHasEqualConditionsWithOtherGrinder((Grinder) other);
        }

        return false;
    }

    private boolean grinderHasEqualConditionsWithOtherGrinder(Grinder otherGrinder){
        if(this.calloutCounter != otherGrinder.calloutCounter){
            return false;
        }
        if (this.energySavingModeMinutes != otherGrinder.energySavingModeMinutes){
            return false;
        }
        if (!this.primaryBeanRatio.equals(otherGrinder.primaryBeanRatio)){
            return false;
        }
        if (!this.secondaryBeanRatio.equals(otherGrinder.secondaryBeanRatio)){
            return false;
        }
        if (!this.primaryBeansInGrams.equals(otherGrinder.primaryBeansInGrams)){
            return false;
        }
        if (!this.secondaryBeansInGrams.equals(otherGrinder.secondaryBeansInGrams)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        //repeat this for every field!!
        hashCode = 31 * hashCode + calloutCounter;

        hashCode = 31 * hashCode + energySavingModeMinutes;

        hashCode = 31* hashCode + primaryBeanRatio.hashCode();

        hashCode = 31* hashCode + secondaryBeanRatio.hashCode();

        hashCode = 31* hashCode + primaryBeansInGrams.hashCode();

        hashCode = 31* hashCode + secondaryBeansInGrams.hashCode();

        return hashCode;
    }

    public static Grinder newInstance(LocalTime localTime) {
        if(dateIsInTheMorning(localTime)){
            return new EspressoGrinder();
        } else {
            return new Grinder();
        }
    }

    private static boolean dateIsInTheMorning(LocalTime localTime) {
        return localTime.isAfter(LocalTime.of(7,0,0)) &&
                localTime.isBefore(LocalTime.of(10, 30, 0));
    }

    public void setCustomBeanRatio(BigDecimal primaryBeanRatio, BigDecimal secondaryBeanRatio) throws IllegalArgumentException{
        if(beanRatioIsNegative(primaryBeanRatio) || beanRatioIsNegative(secondaryBeanRatio)){
            throw new IllegalArgumentException();
        } else if(beanRatioSumIsNotOne(primaryBeanRatio, secondaryBeanRatio)){
            throw new IllegalArgumentException();
        }
        this.primaryBeanRatio = primaryBeanRatio;
        this.secondaryBeanRatio = secondaryBeanRatio;
    }

    private boolean beanRatioIsNegative(BigDecimal beanRatio) {
        return beanRatio.compareTo(BigDecimal.ZERO) == -1;
    }

    private boolean beanRatioSumIsNotOne(BigDecimal primaryBeanRatio, BigDecimal secondaryBeanRatio) {
        return primaryBeanRatio.add(secondaryBeanRatio).compareTo(BigDecimal.ONE) != 0;
    }

    public BigDecimal getPrimaryBeansInGrams() {
        return primaryBeansInGrams;
    }

    public BigDecimal getSecondaryBeansInGrams() {
        return secondaryBeansInGrams;
    }
}
