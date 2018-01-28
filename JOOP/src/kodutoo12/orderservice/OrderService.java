package kodutoo12.orderservice;

import kodutoo12.client.ClientInfo;
import kodutoo12.orders.BusinessOrder;
import kodutoo12.orders.PrivateOrder;
import kodutoo12.storage.OrderStorage;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OrderService implements Runnable {

    private String id;
    private static int nextClientNumber = 0;
    private OrderStorage orderStorage;
    private final int MAX_AMOUNT_OF_TIME_DELAY = 2;
    private List<String> namePool = Arrays.asList("Manuel", "Sierra", "Ragnar", "Mike", "Juliet");
    private List<String> addressPool = Arrays.asList("Estonia", "Latvia", "Lithuania", "Russia", "Finland");
    private List<String> orderLengthPool = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    private Random random = new Random();

    public OrderService(String id, OrderStorage orderStorage){
        this.id = id;
        this.orderStorage = orderStorage;
    }

    private int getNextClientNumber() {
        return ++nextClientNumber;
    }

    @Override
    public void run() {
        for(int i = 0; i< 900; i++){
            ClientInfo clientInfo = new ClientInfo(namePool.get(random.nextInt(5)), addressPool.get(random.nextInt(5)));
            BigInteger orderLength = new BigInteger(orderLengthPool.get(random.nextInt(12)));
            orderStorage.addOrder(new PrivateOrder(getNextClientNumber(), clientInfo, orderLength));
            try {
                Thread.sleep(MAX_AMOUNT_OF_TIME_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i< 450; i++){
            ClientInfo clientInfo = new ClientInfo(namePool.get(random.nextInt(5)), addressPool.get(random.nextInt(5)));
            BigInteger orderLength = new BigInteger(orderLengthPool.get(random.nextInt(5)));
            BigInteger registerCode = BigInteger.valueOf(random.nextLong());
            orderStorage.addOrder(new BusinessOrder(getNextClientNumber(), registerCode, clientInfo, orderLength));
            try {
                Thread.sleep(MAX_AMOUNT_OF_TIME_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i< 250; i++){
            ClientInfo clientInfo = new ClientInfo(namePool.get(random.nextInt(5)), addressPool.get(random.nextInt(5)));
            BigInteger orderLength = new BigInteger(orderLengthPool.get(random.nextInt(6) + 6));
            BigInteger registerCode = BigInteger.valueOf(random.nextLong());
            orderStorage.addOrder(new BusinessOrder(getNextClientNumber(), registerCode, clientInfo, orderLength));
            try {
                Thread.sleep(MAX_AMOUNT_OF_TIME_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
