package kodutoo4.card;

import java.math.BigDecimal;

public interface Card {

    BigDecimal getBalance();

    void makeTransaction(BigDecimal amount);
}
