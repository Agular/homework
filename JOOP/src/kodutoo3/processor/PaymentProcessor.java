package kodutoo3.processor;

import kodutoo3.card.Card;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PaymentProcessor {

    private final BigDecimal subractableAmount = new BigDecimal("3.0454547");

    public void makeMassTransactionWithStaticAmount(ArrayList<Card> cards){
        for(Card card: cards){
            card.makeTransaction(subractableAmount);
        }
    }
}
