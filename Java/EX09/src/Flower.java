/**
 * Created by Ragnar on 12.03.2016.
 * Flower class.
 */
public class Flower {
    /**
     * The price of one flower.
     */
    private double price;

    /***/
    public Flower() {

    }

    /**
     * Special constructor for Flower.
     *
     * @param price The price of one flower.
     */
    public Flower(double price) {
        this.price = price;
    }

    /**
     * Returns the price of one flower.
     *
     * @return The price of one flower.
     */
    public double getPrice() {
        if (price < 0) {
            return Double.NaN;
        } else {
            return price;
        }
    }
}
