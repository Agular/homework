/**
 * Created by raluga on 04.05.2016.
 * EX17.
 */
public class EX17 {
    /***/
    public static void main(String[] args) {
        Child marta = new Child("Marta");
        Child mati = new Child("Mati");
        Child kati = new Child(null);
        marta.playsWith(mati);
        mati.playsWith(kati);

        System.out.println(Child.getSandbox(marta)); // --> laste nimed: ["Marta", "Mati", "Kati"]
    }
}
