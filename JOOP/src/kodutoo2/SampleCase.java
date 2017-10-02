package kodutoo2;

import kodutoo2.electriclock.SchoolElectricLock;

public class SampleCase {

    public static void main(String[] args){
        SchoolElectricLock reaalElectricLock = new SchoolElectricLock("REAAL");
        SchoolElectricLock nooElectricLock = new SchoolElectricLock("NOO");
        SchoolElectricLock kardlaElectricLock = new SchoolElectricLock("KARDLA");
        SchoolElectricLock kapakElectricLock = new SchoolElectricLock("KAPAK");

        System.out.println("Testing reaalElectricLock\n");
        reaalElectricLock.processCard("511111111111111111111");
        reaalElectricLock.processCard("4111111111111111111");
        reaalElectricLock.processCard("622222222223333333");
        reaalElectricLock.processCard("522222222222222222");
        reaalElectricLock.processCard("523233333333333333");
        reaalElectricLock.processCard("11111111111111111115");

        System.out.println("\nTesting nooElectricLock\n");
        nooElectricLock.processCard("511111111111111111111");
        nooElectricLock.processCard("4111111111111111111");
        nooElectricLock.processCard("622222222223333333");
        nooElectricLock.processCard("522222222222222222");
        nooElectricLock.processCard("523233333333333333");
        nooElectricLock.processCard("11111111111111111115");

        System.out.println("\nTesting kardlaElectricLock\n");
        kardlaElectricLock.processCard("511111111111111111111");
        kardlaElectricLock.processCard("4111111111111111111");
        kardlaElectricLock.processCard("622222222223333333");
        kardlaElectricLock.processCard("522222222222222222");
        kardlaElectricLock.processCard("523233333333333333");
        kardlaElectricLock.processCard("11111111111111111115");

        System.out.println("\nTesting kapakElectricLock\n");
        kapakElectricLock.processCard("511111111111111111111");
        kapakElectricLock.processCard("4111111111111111111");
        kapakElectricLock.processCard("622222222223333333");
        kapakElectricLock.processCard("522222222222222222");
        kapakElectricLock.processCard("523233333333333333");
        kapakElectricLock.processCard("11111111111111111115");
    }
}
