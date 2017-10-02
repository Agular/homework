package kodutoo4.client;

import kodutoo4.card.BankCard;
import kodutoo4.card.CreditCard;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class Client {

    private String socialId;
    private BankCard bankCard;
    private CreditCard creditCard;
    private LocalDate birthDate;

    public Client(String socialId) {
        this.socialId = socialId;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Optional<CreditCard> getCreditCardOrElseNull() {
        return Optional.ofNullable(creditCard);
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void payMonthlyBankCardFee(){
        try{
            System.out.println("Taking monthly bank fee from bankcard.");
            bankCard.makeTransaction(new BigDecimal("1.00"));
        } catch (RuntimeException e){
            System.out.println("There was unsufficient funds on the bankcard. Trying creditcard.");
            if(creditCard != null){
                try{
                    creditCard.makeTransaction(new BigDecimal("1.00"));
                } catch(RuntimeException e1){
                    System.out.println("No sufficient funds on creditcard.");
                    System.out.println("This client has no sufficient funds for monthly bank fee!");
                    System.out.println("This client should be cancelled!");
                }
            } else{
                System.out.println("This client has no sufficient funds for monthly bank fee!");
                System.out.println("This client should be cancelled!");
            }
        }
    }
}
