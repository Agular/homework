/**
 * A string formatting excercise.
 */
public class EX06 {
    /**
     * Creates BankAccount instances and uses their methods.
     */
    /**
     * Money added on first account.
     */
    public static final double FIRST_ACCOUNT_MONEY = 101.0;
    /**
     * Money added from second account.
     */
    public static final double SECOND_ACCOUNT_MONEY = 200.0;
    /**
     * Money transferred from first account to second.
     */
    public static final double FIRST_TRANSFER_MONEY = 100.0;
    /***/
    public static final double SECOND_WITHDRAW_MONEY = 67.3;

    /**
     * Uses the BankAccount class.
     *
     * @param args WHATEVER THIS IS.
     */
    public static void main(String[] args) {
        BankAccount first = new BankAccount();
        BankAccount second = new BankAccount();

        first.addMoney(FIRST_ACCOUNT_MONEY);
        second.addMoney(SECOND_ACCOUNT_MONEY);
        first.transferMoneyTo(second, FIRST_TRANSFER_MONEY);
        System.out.println(second.getBalance()); // 300.0
        System.out.println(first.getBalance()); // 0.0
        second.withdrawMoney(SECOND_WITHDRAW_MONEY);
        System.out.println(second.getBalance()); // 232.7
    }
}
