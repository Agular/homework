package kodutoo4.card;

import java.math.BigDecimal;

public class BankCard implements Card {

    private BigDecimal balance;

    private BankCard(BigDecimal initialBalance) {
        this.balance = initialBalance;
    }

    BankCard() {}

    public static BankCard getNewInstance(BigDecimal initialBalance){
        return new BankCard(initialBalance);
    }

    @Override
    public void makeTransaction(BigDecimal amount) throws RuntimeException {
        System.out.println(this.toString() + " Transaction amount: " + amount);
        if (balance.compareTo(amount) == -1) {
            System.out.println("Bankcard does not have enough credit, transaction cancelled");
            throw new RuntimeException();
        } else {
            subtractBalance(amount);
            System.out.println(this.toString() + " Balance: " + balance);
        }
    }

    void subtractBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
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
