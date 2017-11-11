package praks10.petrolstrategies;

import praks10.gasstation.GasTank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NormalPetrolStrategy implements PetrolStrategy {

    private BigDecimal pricePerLiterOfPetrol = new BigDecimal("1.249").setScale(3, RoundingMode.HALF_UP);
    private final BigDecimal DISCOUNT_AMOUNT = new BigDecimal("30.0");
    private final BigDecimal DISCOUNT_MODIFIER = new BigDecimal("0.9");

    @Override
    public BigDecimal getPricePerLiterOfPetrol(GasTank gasTank, BigDecimal amountOfPetrolBought) {
        if (amountOfPetrolBoughtIsEnoughForDiscount(amountOfPetrolBought) && gasTankHasDiscountAmountOfPetrol(gasTank)) {
            return pricePerLiterOfPetrol.multiply(DISCOUNT_MODIFIER);
        }
        return pricePerLiterOfPetrol;
    }

    private boolean amountOfPetrolBoughtIsEnoughForDiscount(BigDecimal amountOfPetrolBought) {
        return amountOfPetrolBought.compareTo(DISCOUNT_AMOUNT) >= 0;
    }

    private boolean gasTankHasDiscountAmountOfPetrol(GasTank gasTank) {
        return gasTank.getRemainingPetrol().compareTo(DISCOUNT_AMOUNT) >= 0;
    }
}
