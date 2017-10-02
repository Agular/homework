package kodutoo4.client;

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
    public void payMonthlyBankCardFee() {
    }
}
