package kodutoo3;

import kodutoo3.card.BankCard;
import kodutoo3.card.Card;
import kodutoo3.card.CreditCard;
import kodutoo3.processor.PaymentProcessor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class MainKodutoo3 {

    public static void main(String[] args){

        BankCard swedbankCard = new BankCard();
        CreditCard masterCard = new CreditCard();
        BankCard lhvCard = new CreditCard();
        BankCard lugaCard = new CreditCard(new BigDecimal("100.0"), false);
        CreditCard visaCard = new CreditCard(new BigDecimal("250"), true);
        CreditCard plainOldCreditCard = new CreditCard(new BigDecimal("500.0"), false);
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        swedbankCard.makeTransaction(new BigDecimal("20"));
        masterCard.makeTransaction(new BigDecimal("55.99"));
        lhvCard.makeTransaction(new BigDecimal("3456.12"));

        System.out.println(swedbankCard.getBalance().toPlainString());
        System.out.println(masterCard.getBalance().toPlainString());
        System.out.println(lhvCard.getBalance().toPlainString());
        System.out.println(lugaCard.getBalance().toPlainString());

        System.out.println("\nMaking massive mass-transactions\n");
        paymentProcessor.makeMassTransactionWithStaticAmount(
                new ArrayList<Card>(Arrays.asList(swedbankCard, masterCard, lhvCard))
        );

        System.out.println("Trying to exceed the credit limit");
        lugaCard.makeTransaction(new BigDecimal("1001.0"));

        System.out.println("\nTesting offline card\n");
        visaCard.makeTransaction(new BigDecimal("50.0"));

        // out of bounds
        visaCard.makeOfflineTransaction(new BigDecimal("0.5"));
        visaCard.makeOfflineTransaction(new BigDecimal("25"));

        // out of bounds
        visaCard.makeOfflineTransaction(new BigDecimal("125"));

        visaCard.makeOfflineTransaction(new BigDecimal("50.0"));
        visaCard.makeOfflineTransaction(new BigDecimal("50.0"));
        visaCard.makeOfflineTransaction(new BigDecimal("50.0")); //current sum now is 175
        visaCard.makeOfflineTransaction(new BigDecimal("50.0")); //exceeds limit, service fee is added, sum is 228.

        System.out.println("Sum of offline transactions: " + visaCard.getSumDoneOfflineTransactions());

        System.out.println("\nTest offline payments of card that has it disabled\n");
        plainOldCreditCard.makeOfflineTransaction(new BigDecimal("29.0"));

    }
}
