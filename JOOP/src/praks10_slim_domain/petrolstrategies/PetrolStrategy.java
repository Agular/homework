package praks10_slim_domain.petrolstrategies;

import praks10_slim_domain.petrolpump.GasTank;

import java.math.BigDecimal;

public interface PetrolStrategy {

    BigDecimal getPricePerLiterOfPetrol(GasTank gasTank, BigDecimal amountOfPetrolBought);

    static PetrolStrategy WeirdPetrolStrategy(){
        return (gasTank, amountOfPetrolBought) -> new BigDecimal("1.249");
    };



}



