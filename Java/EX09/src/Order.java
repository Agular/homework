import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ragnar on 12.03.2016.
 * The order class.
 */
public class Order {
    /**
     * The name of the client.
     */
    private String client;
    /**
     * The address of the client.
     */
    private String address;
    /**
     * The number of current order.
     */
    private int orderNumber = 0;
    /**
     * The list containing all the flowers.
     */
    private List<Flower> flowers;
    /**
     * The number of next order.
     */
    static int nextOrderNumber = 0;

    /**
     * Create the Order class.
     *
     * @param client  The name of the client.
     * @param address The address of the client.
     */
    public Order(String client, String address) {
        this.client = client;
        this.address = address;
    }

    /**
     * Return the name of the client.
     *
     * @return clientName.
     */
    public String getClient() {
        return client;
    }

    /**
     * Return the address of the client.
     *
     * @return address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Add a Flower to the Order.
     *
     * @param flower The Flower.
     * @return If adding the flower was successful.
     */
    public boolean add(Flower flower) {
        if (flowers == null) {
            flowers = new ArrayList<>();
        }
        if (flower == null || flower.getPrice() < 0) {
            return false;
        } else {
            flowers.add(flower);
            return true;
        }
    }

    /**
     * Get the total price of the order.
     *
     * @return The total price.
     */
    public double getTotalPrice() {
        if (flowers == null || flowers.isEmpty()) {
            return 0.0;
        }
        double tulipPrice = 0;
        double rosePrice = 0;
        int noTulips = 0;
        int noRoses = 0;
        for (Flower flower : flowers) {
            if (flower instanceof Rose && flower.getPrice() >= 0) {
                rosePrice += ((Rose) flower).getPrice();
                noRoses++;
            } else if (flower instanceof Tulip && flower.getPrice() >= 0) {
                tulipPrice += ((Tulip) flower).getPrice();
                noTulips++;
            }
        }
        if (noRoses >= 3) {
            rosePrice = rosePrice * 0.95;
        }
        if (noTulips >= 5) {
            tulipPrice = tulipPrice * 0.9;
        }
        return tulipPrice + rosePrice;
    }

    /**
     * Get the number of current order.
     *
     * @return The number of current order.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Get the number of next order.
     *
     * @return The number of next order.
     */
    public static int getNextOrderNumber() {
        nextOrderNumber++;
        return nextOrderNumber;
    }

    /**
     * Get the check for the order.
     *
     * @return The string containing the check.
     */
    public String pay() {
        orderNumber = getNextOrderNumber();
        int noRoses = getNumberOfRoses();
        int noTulips = getNumberOfTulips();
        String check = "";
        check += "Order: " + getOrderNumber() + "\n";
        check += "Client: " + getClient() + "\n";
        check += "Address: " + getAddress() + "\n";
        if (noRoses > 0) {
            check += "Roses: " + noRoses + "\n";
        }
        if (noTulips > 0) {
            check += "Tulips: " + noTulips + "\n";
        }
        check += String.format(Locale.ENGLISH, "%.2f", getTotalPrice()) + "€";
        return check;
    }

    /**
     * Get the number of Tulips in flowers.
     *
     * @return The integer of Tulips in flowers.
     */
    public int getNumberOfTulips() {
        if (flowers == null || flowers.isEmpty()) {
            return 0;
        }
        int number = 0;
        for (Flower flower : flowers) {
            if (flower != null && flower instanceof Tulip && flower.getPrice() >= 0) {
                number++;
            }
        }
        return number;
    }

    /**
     * Get the number of Roses in flowers.
     *
     * @return The integer of Roses in flowers.
     */
    public int getNumberOfRoses() {
        if (flowers == null || flowers.isEmpty()) {
            return 0;
        }
        int number = 0;
        for (Flower flower : flowers) {
            if (flower != null && flower instanceof Rose && flower.getPrice() >= 0) {
                number++;
            }
        }
        return number;
    }
}
