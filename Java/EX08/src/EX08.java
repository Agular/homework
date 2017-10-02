/**
 * Homework: EX08.
 * A practice of creating classes and implementing them.
 * Soldiers, attention!
 * Leeeft, side!
 * March!
 * Stop! Hammer time!!
 */
public class EX08 {
    /**
     * Tests to insure that the classes work as they should.
     *
     * @param args I do not know this.
     */
    public static void main(String[] args) {
        SoldierOfFortune soldier1 = new SoldierOfFortune();
        soldier1.setFirstName("Gerald Albert");
        soldier1.setLastName("Garacus");
        soldier1.setCodeName("G.A.");
        SoldierOfFortune soldier2 = new SoldierOfFortune();
        soldier2.setFirstName("Templeton");
        soldier2.setLastName("Peck");
        soldier2.setCodeName("Bottom");

        SoldierOfFortune soldier3 = new SoldierOfFortune();
        soldier3.setFirstName("John");
        soldier3.setLastName("McClean");
        soldier3.setCodeName("Bro Hard");

        SoldierOfFortune soldier4 = new SoldierOfFortune();
        soldier4.setFirstName("John");
        soldier4.setLastName("Rambro");
        soldier4.setCodeName("Your Worst Nightmare");
        SoldierOfFortune soldier5 = new SoldierOfFortune();
        soldier5.setFirstName("Arnie");
        soldier5.setLastName("Blackman");
        soldier5.setCodeName("The Initiator");

        Team bTeam = new Team();
        bTeam.setCodeName("B-Team");
        Team cTeam = new Team();
        cTeam.setCodeName("C-Team");

        bTeam.addSoldierToTeam(soldier1);
        bTeam.addSoldierToTeam(soldier2);
        bTeam.addSoldierToTeam(soldier3);
        cTeam.addSoldierToTeam(soldier3); // Soldier 3 on kahes tiimis
        cTeam.addSoldierToTeam(soldier4);
        cTeam.addSoldierToTeam(soldier5);

        System.out.println(soldier1); // -> Gerald Albert "G.A." Garacus
        System.out.println(bTeam); // -> B-Team: G.A, Bottom, Bro Hard
        System.out.println(bTeam.getNumberOfSoldiers()); // -> 3
        System.out.println(cTeam.getNumberOfSoldiers()); // -> 3

        SoldierOfFortune x = new SoldierOfFortune();
        x.setNumberOfMissionsCompleted(2);
        SoldierOfFortune y = new SoldierOfFortune();
        y.setNumberOfMissionsCompleted(2 + 1);
        SoldierOfFortune z = null;
        Team wapa = new Team();
        wapa.addSoldierToTeam(x);
        wapa.addSoldierToTeam(y);
        wapa.addSoldierToTeam(z);
        System.out.println(wapa.averageMissionsCompleted());
        bTeam.dance();
    }
}
