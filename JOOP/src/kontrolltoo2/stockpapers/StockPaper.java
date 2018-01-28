package kontrolltoo2.stockpapers;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class StockPaper {

    private String shortName;
    private String fullName;
    private BigDecimal pricePerUnit;
    private BigInteger amount;
    private BigDecimal fullPrice;

    public StockPaper(String shortName, String fullName, BigDecimal pricePerUnit, BigInteger amount) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.pricePerUnit = pricePerUnit;
        this.amount = amount;
        this.fullPrice = pricePerUnit.multiply(new BigDecimal(amount));
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public String getInfo() {
        return "Short Name: " + shortName + " " +
                "Full Name: " + fullName + " " +
                "Price Per Unit> " + pricePerUnit + " " +
                "Amount: " + amount + " " +
                "Full Price: " + fullPrice;
    }
}
