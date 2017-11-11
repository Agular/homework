package kontrolltoo1.controller;

import kontrolltoo1.charger.Charger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChargerController {

    private String name;
    private List<Charger> chargers;

    public ChargerController(String name){
        this.name = name;
        chargers = new ArrayList<>();
    }

    public void addCharger(Charger charger){
        chargers.add(charger);
    }

    public Optional<String> getName(){
        return Optional.ofNullable(name);
    }

    @Override
    public String toString(){
        String output = "";
        for (Charger charger: chargers){
            output = output + "Charger: " + charger.getType() + " Mode: " + charger.getCurrentMode() + "\n";
        }
        return output;
    }

    public BigDecimal getSumOfAllRegularChargingPower(){
        BigDecimal sumOfAllRegularChargingPower = BigDecimal.ZERO;
        for(Charger charger: chargers){
            sumOfAllRegularChargingPower.add(charger.getRegularChargingPower());
        }
        return sumOfAllRegularChargingPower;
    }
}
