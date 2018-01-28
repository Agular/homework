package proovikontrolltoo2.vehicles;

import java.math.BigDecimal;

public class LandVehicle extends Vehicle {

    private BigDecimal drivenKilometers;

    public LandVehicle(int producedYear, int firstRegistrationYear, BigDecimal price, BigDecimal drivenKilometers) {
        super(producedYear, firstRegistrationYear, price);
        this.drivenKilometers = drivenKilometers;
    }

    public BigDecimal getDrivenKilometers() {
        return drivenKilometers;
    }

    @Override
    public String getInfo(){
        return "Land vehicle " + super.getInfo();
    }
}
