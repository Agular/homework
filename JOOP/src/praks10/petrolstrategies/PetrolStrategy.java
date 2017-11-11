package praks10.petrolstrategies;

import praks10.gasstation.GasTank;

import java.math.BigDecimal;

public interface PetrolStrategy {

    BigDecimal getPricePerLiterOfPetrol(GasTank gasTank, BigDecimal amountOfPetrolBought);

    static PetrolStrategy WeirdPetrolStrategy(){
        return (gasTank, amountOfPetrolBought) -> new BigDecimal("1.249");
    };



}



