package kodutoo10.robothardware;

import java.math.BigInteger;

public interface Motor {

    void setRPM(BigInteger rpm);

    void setRPMToMax();

    BigInteger getRPM();
}
