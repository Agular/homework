package kodutoo12.orderreader;

import kodutoo12.orders.Order;
import kodutoo12.storage.OrderStorage;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.BiFunction;

public class OrderReader implements Runnable {

    private String id;
    private OrderStorage orderStorage;

    public OrderReader(String id, OrderStorage orderStorage) {
        this.id = id;
        this.orderStorage = orderStorage;
    }

    private synchronized void fetchOrders() throws InterruptedException {
        registerOrder(orderStorage.getOrder());
    }

    private BiFunction<BigInteger, BigInteger, BigDecimal> normalPriceCalculation(){
        return (lengthOfOrder, numberOfExemplars) -> {
            BigDecimal price = new BigDecimal("0.55").setScale(2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(numberOfExemplars));
            if (lengthOfOrder.compareTo(new BigInteger("6")) >= 0) {
                return price.multiply(new BigDecimal("0.9"));
            } else {
                return price;
            }
        };
    }

    private void registerOrder(Order order) {
        System.out.println("ReaderId: " + id + " " + order.getInfo() + " Price: " +
                order.getPrice(normalPriceCalculation()));
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                fetchOrders();
            } catch (InterruptedException e) {
                break;
            }
        }
    }


}
