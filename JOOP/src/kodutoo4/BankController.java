package kodutoo4;

import kodutoo4.bankclientgateway.BankClientGateway;
import kodutoo4.client.Client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class BankController {
    public static void main(String[] args) {
        BankClientGateway bankClientGateway = new BankClientGateway();

        HashMap<String, LocalDate> clientOrder = new HashMap<>();
        clientOrder.put("1111", LocalDate.now());
        clientOrder.put("2222", LocalDate.now());
        clientOrder.put("3333", LocalDate.now());


        for (Map.Entry<String, LocalDate> clientEntry : clientOrder.entrySet()) {
            System.out.println("\n" + clientEntry.getKey() + "\n");
            Client client = bankClientGateway.returnClientInstanceBySocialId(clientEntry.getKey(),clientEntry.getValue());
            client.getBankCard().makeTransaction(new BigDecimal("34"));
            if (client.getCreditCardOrElseNull().isPresent()) {
                client.getCreditCardOrElseNull().get().makeTransaction(new BigDecimal("70.99"));
            }
            if (client.getCreditCard() != null) {
                client.getCreditCard().makeTransaction(new BigDecimal("20"));
            }
        }

    }
}
