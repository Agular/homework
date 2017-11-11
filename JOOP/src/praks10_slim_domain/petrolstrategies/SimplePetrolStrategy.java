package praks10_slim_domain.petrolstrategies;

import praks10_slim_domain.petrolpump.GasTank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimplePetrolStrategy implements PetrolStrategy {

    private BigDecimal pricePerLitreOfPetrol = new BigDecimal("1.249").setScale(3, RoundingMode.HALF_UP);

    @Override
    public BigDecimal getPricePerLiterOfPetrol(GasTank gasTank, BigDecimal amountOfPetrolBought) {
        return pricePerLitreOfPetrol;
    }
}
