package kodutoo12.orders;

import kodutoo12.client.ClientInfo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.BiFunction;


public class PrivateOrder implements Order {

    ClientInfo clientInfo;
    int clientNumber;
    BigInteger lengthOfOrder;

    public PrivateOrder(int clientNumber, ClientInfo clientInfo, BigInteger lengthOfOrder){
        this.clientInfo = clientInfo;
        this.clientNumber = clientNumber;
        this.lengthOfOrder = lengthOfOrder;
    }

    @Override
    public String getClientName() {
        return clientInfo.getName();
    }

    @Override
    public String getAddress() {
        return clientInfo.getAddress();
    }

    @Override
    public int getClientNumber() {
        return clientNumber;
    }

    @Override
    public BigInteger getLengthOfOrder() {
        return lengthOfOrder;
    }

    @Override
    public BigInteger getNumberOfExemplars() {
        return new BigInteger("6");
    }

    @Override
    public BigDecimal getPrice(BiFunction<BigInteger, BigInteger, BigDecimal> priceHandler) {
        return priceHandler.apply(lengthOfOrder, this.getNumberOfExemplars());
    }

    @Override
    public String getInfo() {
        return  " Number: " + clientNumber +
                " Name: " + clientInfo.getName() + " Address: " + clientInfo.getAddress() +
                " OrderLength: " + lengthOfOrder + " N_Exemplars: " + getNumberOfExemplars();
    }


}
