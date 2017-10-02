/**
 * Created by Ragnar on 17.03.2016.
 * PremiumPackage. PP PP PP.
 * I need to PP. Get it? Of course you do.
 */
public class PremiumPackage extends Package {
    /**
     * The priority of the package.
     */
    private int priority;

    /**
     * Default constructor for PremiumPackage.
     */
    public PremiumPackage() {
    }

    /**
     * Return priority of PP.
     *
     * @return priority of PP.
     */
    public int getPriority() {
        return priority;
    }
    /**
     * Set the priority of PP.
     * @param priority The priority of PP.
     * */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Package constructor.
     *
     * @param priority Priority package in decimals.
     * @param packageNumber Package number printed on package
     * @param width         Package width in cm
     * @param height        Package height in cm
     */
    public PremiumPackage(int priority, String packageNumber, int width, int height) {
        this.priority = priority;
        this.packageNumber = packageNumber;
        this.width = width;
        this.height = height;
    }
}
