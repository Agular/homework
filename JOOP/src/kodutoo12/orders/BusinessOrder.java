package kodutoo12.orders;

import kodutoo12.client.ClientInfo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.BiFunction;

public class BusinessOrder extends PrivateOrder {

    private BigInteger registerCode;

    public BusinessOrder(int clientNumber, BigInteger registerCode, ClientInfo clientInfo, BigInteger lengthOfOrder) {
        super(clientNumber, clientInfo, lengthOfOrder);
        this.registerCode = registerCode;
    }


    @Override
    public BigInteger getNumberOfExemplars() {
        return new BigInteger("5");
    }

    @Override
    public BigDecimal getPrice(BiFunction<BigInteger, BigInteger, BigDecimal> priceHandler) {
        return priceHandler.apply(super.getLengthOfOrder(), this.getNumberOfExemplars());
    }

    @Override
    public String getInfo(){
        return  " Number: " + super.clientNumber + " RegisterCode: " + registerCode +
                " Name: " + super.clientInfo.getName() + " Address: " + clientInfo.getAddress() +
                " OrderLength: " + lengthOfOrder + " N_Exemplars: " + getNumberOfExemplars();
    }
}
