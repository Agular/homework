package kodutoo12.orders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.BiFunction;

public interface Order {

    String getClientName();

    String getAddress();

    int getClientNumber();

    BigInteger getLengthOfOrder();

    BigInteger getNumberOfExemplars();

    BigDecimal getPrice(BiFunction<BigInteger, BigInteger, BigDecimal> priceHandler);

    String getInfo();
}
