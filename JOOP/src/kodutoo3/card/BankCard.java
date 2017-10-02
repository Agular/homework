package kodutoo3.card;

import java.math.BigDecimal;

public class BankCard implements Card {

    private BigDecimal balance;

    public BankCard() {
        balance = new BigDecimal("10000.0005");
    }

    @Override
    public void makeTransaction(BigDecimal amount) throws RuntimeException {
        System.out.println(this.toString() + " Transaction amount: " + amount);
        if (balance.compareTo(amount) == -1) {
            System.out.println("Bankcard does not have enough credit, transaction cancelled");
        } else {
            subtractBalance(amount);
            System.out.println(this.toString() + " Balance: " + balance);
        }
    }

    void subtractBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "Deebetkaart";
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
