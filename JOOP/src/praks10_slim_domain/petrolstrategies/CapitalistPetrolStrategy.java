package praks10_slim_domain.petrolstrategies;

import praks10_slim_domain.petrolpump.GasTank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CapitalistPetrolStrategy implements PetrolStrategy {

    private BigDecimal pricePerLiterOfPetrol = new BigDecimal("1.299").setScale(3, RoundingMode.HALF_UP);
    private final BigDecimal CAPITALIST_PRICE_MODIFIER = new BigDecimal("1.2");
    private final BigDecimal RESERVE_FACTOR = new BigDecimal("0.4");

    @Override
    public BigDecimal getPricePerLiterOfPetrol(GasTank gasTank, BigDecimal amountOfPetrolBought) {
        if (gasTankFuelFactorIsSmallerThanReserveFactor(gasTank)) {
            return pricePerLiterOfPetrol.multiply(CAPITALIST_PRICE_MODIFIER);
        }
        return pricePerLiterOfPetrol;
    }

    private boolean gasTankFuelFactorIsSmallerThanReserveFactor(GasTank gasTank) {
        return gasTank.getRemainingPetrol().divide(gasTank.getTankSize(), RoundingMode.HALF_UP).compareTo(RESERVE_FACTOR) == -1;
    }
}
