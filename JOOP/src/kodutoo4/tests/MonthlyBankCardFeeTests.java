package kodutoo4.tests;

import kodutoo4.bankclientgateway.BankClientGateway;
import kodutoo4.client.Client;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;


public class MonthlyBankCardFeeTests {

    @Test
    public void ClientMonthlyFeeWithSufficientFundsOnBankCard(){
        BankClientGateway gateway = new BankClientGateway();
        Client client = gateway.returnClientInstanceBySocialId("1111", LocalDate.MIN);
        client.payMonthlyBankCardFee();
        Assert.assertEquals(new BigDecimal("799.00"), client.getBankCard().getBalance());
    }

    @Test
    public void JuniorClientMonthlyFeeWithSufficientFundsOnBankCard(){
        BankClientGateway gateway = new BankClientGateway();
        Client client = gateway.returnClientInstanceBySocialId("1111", LocalDate.now().minusYears(10));
        client.payMonthlyBankCardFee();
        Assert.assertEquals(new BigDecimal("799.9"), client.getBankCard().getBalance());
    }

    @Test
    public void ClientMonthlyFeeWithNoSufficientFundsOnBankCardButHasCreditcard(){
        BankClientGateway gateway = new BankClientGateway();
        Client client = gateway.returnClientInstanceBySocialId("1111", LocalDate.MIN);
        client.getBankCard().makeTransaction(new BigDecimal("800.0"));
        client.payMonthlyBankCardFee();
        Assert.assertEquals(new BigDecimal("399.00"), client.getCreditCard().getBalance());
    }

    @Test
    public void JuniorClientMonthlyFeeWithNoSufficientFundsOnBankCardButHasCreditcard(){
        BankClientGateway gateway = new BankClientGateway();
        Client client = gateway.returnClientInstanceBySocialId("1111", LocalDate.now().minusYears(10));
        client.getBankCard().makeTransaction(new BigDecimal("800.0"));
        client.payMonthlyBankCardFee();
        Assert.assertEquals(new BigDecimal("399.9"), client.getCreditCard().getBalance());
    }

    @Test
    public void ClientMonthlyFeeWithNoSufficientFundsEitherCard(){
        BankClientGateway gateway = new BankClientGateway();
        Client client = gateway.returnClientInstanceBySocialId("1111", LocalDate.MIN);
        client.getBankCard().makeTransaction(new BigDecimal("800.0"));
        client.getCreditCard().makeTransaction(new BigDecimal("1300.0"));
        client.payMonthlyBankCardFee();
        Assert.assertEquals(new BigDecimal("-900.0"), client.getCreditCard().getBalance());
    }

    @Test
    public void JuniorClientMonthlyFeeWithNoSufficientFundsEitherCard(){
        BankClientGateway gateway = new BankClientGateway();
        Client client = gateway.returnClientInstanceBySocialId("1111", LocalDate.now().minusYears(10));
        client.getBankCard().makeTransaction(new BigDecimal("800.0"));
        client.getCreditCard().makeTransaction(new BigDecimal("1300.0"));
        client.payMonthlyBankCardFee();
        Assert.assertEquals(new BigDecimal("-900.0"), client.getCreditCard().getBalance());
    }

}
