package kontrolltoo1.battery;

import java.math.BigDecimal;

public class Battery {

    private BigDecimal capacity;

    public Battery(BigDecimal capacity){
        this.capacity = capacity;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }
}
