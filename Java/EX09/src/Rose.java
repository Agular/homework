/**
 * Created by Ragnar on 12.03.2016.
 * Rose class.
 */
public class Rose extends Flower {
    /**
     * The boolean that indicates if the Rose has thorns or not.
     */
    private boolean hasThorns;

    /**
     * The discount of a Rose when there are more or equal to 3 Roses.
     */
    private final double discountModifier = 0.05;
    /**
     * The amount of Roses when discount is given.
     */
    private final int discountAmount = 3;

    /**
     * The constructor for the rose.
     *
     * @param price     The price of one Rose.
     * @param hasThorns The boolean for having thorns.
     */
    public Rose(double price, boolean hasThorns) {
        super(price);
        this.hasThorns = hasThorns;
    }

    /**
     * Return a boolean if the Rose has thorns.
     *
     * @return The boolean hasThorns.
     */
    public boolean hasThorns() {
        return hasThorns;
    }

    /**
     * Returns the price of 1 Rose.
     * If there are 3 or more roses, the discount is 5 %.
     *
     * @param amount The amount of Roses.
     * @return The price of 1 Rose.
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
