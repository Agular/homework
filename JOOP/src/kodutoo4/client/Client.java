package kodutoo4.client;

import kodutoo4.card.BankCard;
import kodutoo4.card.CreditCard;

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

    }
}
