package ee.ttu.algoritmid.dancers.dancertree;

import ee.ttu.algoritmid.dancers.Dancer;

public class BallroomDancer implements Dancer {

    private int ID;
    private Gender gender;
    private int height;

    public BallroomDancer(int ID, Gender gender, int height) {
        this.ID = ID;
        this.gender = gender;
        this.height = height;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "ID: " + ID + " Gender: " + gender + " Height: " + height;
    }
}
