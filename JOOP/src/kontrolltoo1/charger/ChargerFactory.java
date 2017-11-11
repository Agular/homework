package kontrolltoo1.charger;


import java.math.BigDecimal;

public class ChargerFactory {

    public static Charger of(String type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        if (type.equals("AC")) {
            return new ACCharger(new BigDecimal("11000"), new BigDecimal("22000"));
        } else if (type.equals("DC")) {
            return new DCCharger( new BigDecimal("11000"), new BigDecimal("40000"));
        } else throw new IllegalArgumentException();
    }
}
