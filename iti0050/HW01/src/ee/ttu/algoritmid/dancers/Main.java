package ee.ttu.algoritmid.dancers;

import ee.ttu.algoritmid.dancers.dancertree.BallroomDancer;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        HW01 hwTree = new HW01();
        Random rg = new Random();
        for (int i = 0; i < 10000; i++) {
            int height = rg.nextInt(2);
            Dancer.Gender gender = Dancer.Gender.values()[rg.nextInt(2)];
            System.out.println(i + " GENDER: " + gender + " HEIGHT: " + height);
            System.out.println(i + " GENDER: " + gender + " HEIGHT: " + height +"      " + hwTree.findPartnerFor(new BallroomDancer(100, gender, height)));
        }

/*        System.out.println(hwTree.findPartnerFor(new BallroomDancer(4, Dancer.Gender.FEMALE, 4)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(4, Dancer.Gender.MALE, 4)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(9, Dancer.Gender.MALE, 9)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(1, Dancer.Gender.FEMALE, 1)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(4, Dancer.Gender.MALE, 4)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(7, Dancer.Gender.MALE, 7)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(5, Dancer.Gender.FEMALE, 5)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(2, Dancer.Gender.MALE, 2)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(0, Dancer.Gender.FEMALE, 0)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(8, Dancer.Gender.MALE, 8)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(1, Dancer.Gender.MALE, 1)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(1, Dancer.Gender.MALE, 1)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(1, Dancer.Gender.MALE, 1)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(8, Dancer.Gender.FEMALE, 8)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(5, Dancer.Gender.FEMALE, 5)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(10, Dancer.Gender.FEMALE, 10)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(7, Dancer.Gender.FEMALE, 7)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(7, Dancer.Gender.FEMALE, 7)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(3, Dancer.Gender.FEMALE, 3)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(1, Dancer.Gender.FEMALE, 1)));
        System.out.println(hwTree.findPartnerFor(new BallroomDancer(8, Dancer.Gender.MALE, 8)));*/
    }
}
