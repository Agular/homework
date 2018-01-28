package proovikontrolltoo2.vehicles;

import java.math.BigDecimal;

public abstract class Vehicle {

    private int producedYear;
    private int firstRegistrationYear;
    private BigDecimal price;

    public Vehicle(int producedYear, int firstRegistrationYear, BigDecimal price) {
        this.producedYear = producedYear;
        this.firstRegistrationYear = firstRegistrationYear;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getProducedYear() {
        return producedYear;
    }

    public int getFirstRegistrationYear() {
        return firstRegistrationYear;
    }

    public String getInfo(){
        return "Produced: " + producedYear + " First registration: " + firstRegistrationYear + " Price: " + price;
    }
}
