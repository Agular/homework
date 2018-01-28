package car;

import engines.DieselEngine;
import engines.Engine;
import engines.PetrolEngine;
import tires.RegularTires;

import java.util.Random;

public class Cars {

    private static Random randomGenerator = new Random();
    private static int nextCarLabel = 1;

    public static Car newInstaceOfRegularCar(){
        return new CityCar(nextCarLabel++,newInstanceOfRegularEngine(), new RegularTires());
    }

    public static Car newInstanceOfBioFriendlyCar(){
        return new CityCar(nextCarLabel++, newInstanceOfBioFriendlyEngine(), new RegularTires());
    }

    private static Engine newInstanceOfRegularEngine() {
        if(randomGenerator.nextBoolean()){
            return new DieselEngine();
        } else {
            return new PetrolEngine();
        }
    }

    private static Engine newInstanceOfBioFriendlyEngine() {
        if(randomGenerator.nextBoolean()){
            return new DieselEngine();
        } else {
            return new PetrolEngine();
        }
    }
}
