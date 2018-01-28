package proovikontrolltoo2.vehicles;

import java.math.BigDecimal;

public class SeaVehicle extends Vehicle {

    private boolean hasFlags;

    public SeaVehicle(int producedYear, int firstRegistrationYear, BigDecimal price, boolean hasFlags) {
        super(producedYear, firstRegistrationYear, price);
        this.hasFlags = hasFlags;
    }

    public boolean isHasFlags() {
        return hasFlags;
    }

    @Override
    public String getInfo(){
        return "Sea vehicle " + super.getInfo();
    }
}
