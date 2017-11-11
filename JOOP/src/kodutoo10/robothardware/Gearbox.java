package kodutoo10.robothardware;

import java.math.BigInteger;

public interface Gearbox {

    void shiftUp();

    void shiftDown();

    void shiftToNeutral();

    void shiftToLowest();

    void shiftToHighest();

    void setGear(BigInteger newGear);

    @Override
    String toString();
}
