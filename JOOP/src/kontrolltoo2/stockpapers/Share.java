package kontrolltoo2.stockpapers;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Share extends StockPaper{

    private BigDecimal nominalPrice;

    public Share(String shortName, String fullName, BigDecimal pricePerUnit,
                 BigInteger amount, BigDecimal nominalPrice) {
        super(shortName, fullName, pricePerUnit, amount);
        this.nominalPrice = nominalPrice;
    }

    public BigDecimal getNominalPrice() {
        return nominalPrice;
    }

    @Override
    public String getInfo(){
        return "Share\n" + super.getInfo();
    }
}
