package kodutoo4.bankclientgateway;

import kodutoo4.card.BankCard;
import kodutoo4.card.CreditCard;
import kodutoo4.client.Client;
import kodutoo4.client.JuniorClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public class BankClientGateway {
    private HashMap<String, Client> clientInstances = new HashMap<>();
    private final int REGULAR_CLIENT_MIN_AGE = 18;

    public Client returnClientInstanceBySocialId(String socialId, LocalDate birthDate) {
        Client newClient;

        if(socialId.length() < 4){
            throw new IllegalArgumentException();
        }
        if (clientInstances.containsKey(socialId)) {
            return clientInstances.get(socialId);
        } else {
            if(newClientIsJunior(birthDate)){
                newClient = new JuniorClient(socialId);
            } else {
                newClient = new Client(socialId);
            }
            newClient.setBankCard(BankCard.getNewInstance(new BigDecimal("800")));
            addClientBonusesIfEligible(newClient);
            newClient.setBirthDate(birthDate);
            clientInstances.put(socialId, newClient);
            return newClient;
        }
    }

    private void addClientBonusesIfEligible(Client client) {
        if (newClientIsThird()) {
            client.getBankCard().deposit(new BigDecimal("100.0"));
        }
        if (newClientSocialIdIsOddNumber(client.getSocialId())) {
            client.setCreditCard(CreditCard.getNewInstance(new BigDecimal("400.0"), false));
        }
    }

    private boolean newClientIsThird() {
        return clientInstances.size() % 3 == 2;
    }

    private boolean newClientSocialIdIsOddNumber(String socialId) {
        return Integer.parseInt(socialId) % 2 == 1;
    }

    private boolean newClientIsJunior(LocalDate birthDate){
        return Period.between(birthDate, LocalDate.now()).getYears() < REGULAR_CLIENT_MIN_AGE;
    }
}
