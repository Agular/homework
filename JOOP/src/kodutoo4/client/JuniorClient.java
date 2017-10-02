package kodutoo4.client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class JuniorClient extends Client {

    private GuardianInformation guardianInformation;

    public JuniorClient(String socialId) {
        super(socialId);
    }

    public void setGuardianInformation(GuardianInformation guardianInformation) {
        this.guardianInformation = guardianInformation;
    }

    public Optional<GuardianInformation> getGuardianInformationOrNull(){
        return Optional.ofNullable(guardianInformation);
    }

    @Override
    public void payMonthlyBankCardFee(){
        BigDecimal monthlyFee = BigDecimal.valueOf(this.getAgeInYears()).divide(new BigDecimal("100.0"));
        try{
            System.out.println("Taking monthly bank fee from bankcard.");
            this.getBankCard().makeTransaction(monthlyFee);
        } catch (RuntimeException e){
            System.out.println("There was unsufficient funds on the bankcard. Trying creditcard.");
            if(this.getCreditCardOrElseNull().isPresent()){
                try{
                    this.getCreditCard().makeTransaction(monthlyFee);
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

    private int getAgeInYears(){
        return Period.between(this.getBirthDate(), LocalDate.now()).getYears();
    }
}
