/**
 * Creates a  BankAccount class.
 */
public class BankAccount {
    /**
     * The amount of money on the account.
     */
    private double balance;
    /**
     * The amount of money payed on withdrawal. 0.01 is the tax fee.
     */
    private final double transferModifier = 1.01;

    /**
     * Returns the balance of the account.
     *
     * @return balance The amount of money on account.
     */
    public final double getBalance() {
        return balance;
    }

    /**
     * The amount of money withdrawn.
     *
     * @param amount The amount of money withdrawn.
     * @return The amount of money left on bank account on successful withdrawal.
     * Else: an error is returned as there is not enough money to be withdrawn.
     */
    public final double withdrawMoney(double amount) {
        if (balance - amount < 0) {
            return Double.NaN;
        } else {
            balance -= amount;
            return balance;
        }
    }

    /**
     * Adds money to the account.
     *
     * @param amount The amount of money added.
     */
    public final void addMoney(double amount) {
        balance += amount;
    }

    /**
     * Function to transfer money from this account to another.
     *
     * @param targetAccount The target account where money would be transferred.
     * @param amount        The amount of the money transferred.
     * @return True if transfer was successful
     * False if target account does not exist or there is not enough money for sending.
     */
    public final boolean transferMoneyTo(BankAccount targetAccount, double amount) {
        if (targetAccount == null || this.balance - amount * transferModifier < 0 || amount < 0) {
            return false;
        } else {
            this.balance -= amount * transferModifier;
            targetAccount.addMoney(amount);
            return true;
        }
    }
}
