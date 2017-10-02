/**
 * Created by Ragnar on 12.03.2016.
 * Tulip class.
 */
public class Tulip extends Flower {
    /**
     * The colour of the Tulip.
     */
    private String colour;

    /**
     * The discount of a Tulip when there are more or equal to 5 Tulips.
     */
    private final double discountModifier = 0.1;
    /**
     * The amount of Tulips when discount is given.
     */
    private final int discountAmount = 5;

    /**
     * Create the Tulip.
     *
     * @param price  The price of 1 Tulip.
     * @param colour The colour of the Tulip.
     */
    public Tulip(double price, String colour) {
        super(price);
        this.colour = colour;
    }

    /**
     * Get the colour of the Tulip.
     *
     * @return The colour as a String.
     */
    public String getColour() {
        return colour;
    }

    /**
     * Returns the price of 1 Tulip.
     * If there are 5 or more Tulips, the discount is 10 %.
     *
     * @param amount The amount of Tulips.
     * @return The price of 1 Tulip.
     */
    public double getPrice(int amount) {
        if (amount < 1 || super.getPrice() < 0.0) {
            return 0.0;
        }
        if (amount < discountAmount) {
            return super.getPrice();
        } else {
            return super.getPrice() * (1.0 - discountModifier);
        }
    }
}
