/**
 * Created by Ragnar on 12.03.2016.
 * For testing.
 * For Git. For Glory.
 */
public class EX09 {
    /**
     * Testing.
     *
     * @param args Dunno.
     */
    public static void main(String[] args) {
        Rose r1 = new Rose(10, true);
        Rose r2 = new Rose(5, false);

        Tulip t1 = new Tulip(10, "Red");
        Tulip t2 = new Tulip(10, "Blue");
        Tulip t3 = new Tulip(10, null);

        Order o1 = new Order("Mati", "Maalt");
        o1.add(r1);
        o1.add(r2);
        o1.add(t1);
        o1.add(t2);
        o1.add(t3);
        o1.add(r1);
        System.out.println(o1.pay());
    }
}
