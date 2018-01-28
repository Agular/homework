package kontrolltoo2.stockpapers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public class Bond extends StockPaper {

    private LocalDate redemptionDate;

    public Bond(String shortName, String fullName, BigDecimal pricePerUnit, BigInteger amount, LocalDate redemptionDate) {
        super(shortName, fullName, pricePerUnit, amount);
        this.redemptionDate = redemptionDate;
    }

    @Override
    public String getInfo() {
        return "Bond\n" + super.getInfo();
    }
}
