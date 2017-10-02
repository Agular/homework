package kodutoo4.card;

import java.math.BigDecimal;

public class CreditCard extends BankCard {

    private final BigDecimal CREDIT_LIMIT = new BigDecimal("900.0");
    private final BigDecimal MINIMUM_OFFLINE_TRANSACTION_AMOUNT = new BigDecimal("1.00");
    private final BigDecimal MAXIMUM_OFFLINE_TRANSACTION_AMOUNT = new BigDecimal("50.00");
    private final BigDecimal OFFLINE_TRANSACTION_LIMIT = new BigDecimal("200.0");
    private final BigDecimal SERVICE_FEE = new BigDecimal("3.00");
    private BigDecimal sumDoneOfflineTransactions = new BigDecimal("0");
    private boolean areOfflinePaymentsEnabled = false;

    private CreditCard() {
        super.setBalance(new BigDecimal("2"));
    }

   private CreditCard(BigDecimal initialBalance, boolean areOfflinePaymentsEnabled) {
        super.setBalance(initialBalance);
        this.areOfflinePaymentsEnabled = areOfflinePaymentsEnabled;
    }

    public static CreditCard getNewInstance(BigDecimal initialBalance, boolean areOfflinePaymentsEnabled){
        return new CreditCard(initialBalance, areOfflinePaymentsEnabled);
    }

    @Override
    public void makeTransaction(BigDecimal amount) throws RuntimeException {
        System.out.println(this.toString() + " Transaction amount: " + amount);
        if (!transactionAmountExceedsCreditCardLimit(amount)) {
            subtractBalance(amount);
            System.out.println(this.toString() + "Balance: " + getBalance());
        } else {
            System.out.println("Amount exceeds creditcard limit. Transaction cancelled");
        }

    }

    private boolean transactionAmountExceedsCreditCardLimit(BigDecimal amount) {
        if (super.getBalance().subtract(amount).compareTo(CREDIT_LIMIT.negate()) == -1) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Krediitkaart";
    }

    public void makeOfflineTransaction(BigDecimal amount) {
        if (areOfflinePaymentsEnabled) {
            if (isOfflineTransactionAmountInBounds(amount)) {
                sumDoneOfflineTransactions = sumDoneOfflineTransactions.add(amount);
                addNecessaryFees();
            } else {
                System.out.println("Offline transaction amount: " + amount + " is out of bounds\n" +
                        "The amount must be between 1 and 50 euros.");
            }
        } else{
            System.out.println("Offline payments are disabled!");
        }
    }

    private boolean isOfflineTransactionAmountInBounds(BigDecimal amount) {
        if (amount.compareTo(MINIMUM_OFFLINE_TRANSACTION_AMOUNT) >= 0 &&
                amount.compareTo(MAXIMUM_OFFLINE_TRANSACTION_AMOUNT) <= 0) {
            return true;
        }
        return false;
    }

    private void addNecessaryFees() {
        if (sumDoneOfflineTransactions.compareTo(OFFLINE_TRANSACTION_LIMIT) == 1) {
            sumDoneOfflineTransactions = sumDoneOfflineTransactions.add(SERVICE_FEE);
        }
    }

    public BigDecimal getSumDoneOfflineTransactions() {
        if (areOfflinePaymentsEnabled) {
            return sumDoneOfflineTransactions;
        } else {
            return null;
        }
    }

    public void setSumDoneOfflineTransactions(BigDecimal sumDoneOfflineTransactions) {
        if (areOfflinePaymentsEnabled) {
            this.sumDoneOfflineTransactions = sumDoneOfflineTransactions;
        } else{
            System.out.println("Offline payments are disabled!");
        }
    }
}
