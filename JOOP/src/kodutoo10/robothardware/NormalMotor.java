package kodutoo10.robothardware;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;

public class NormalMotor implements Motor {

    private final int RPM_CHANGE_TO_CUSTOM_VALUE = 0;
    private final int RPM_CHANGE_TO_MAX = 1;

    private BigInteger maximumRPM;
    private BigInteger currentRPM;
    private PrintStream logFile = null;

    public NormalMotor(BigInteger maximumRPM) {
        this.maximumRPM = maximumRPM;
        currentRPM = BigInteger.ZERO;
        try {
            logFile = new PrintStream(new FileOutputStream("RPM_Change_Log.txt", true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setRPM(BigInteger rpm) {
        if (newRPMHigherThanMaximumRPM(rpm)) {
            throw new IllegalArgumentException("New RPM is bigger than maximum!");
        }
        currentRPM = rpm;
        logRPMChangeToLogFile(RPM_CHANGE_TO_CUSTOM_VALUE);
    }

    private boolean newRPMHigherThanMaximumRPM(BigInteger inputRPM) {
        return inputRPM.compareTo(maximumRPM) == 1;
    }

    @Override
    public void setRPMToMax() {
        currentRPM = maximumRPM;
        logRPMChangeToLogFile(RPM_CHANGE_TO_MAX);
    }

    @Override
    public BigInteger getRPM() {
        return currentRPM;
    }

    private void logRPMChangeToLogFile(int rpmChangeType) {
        switch (rpmChangeType) {
            case RPM_CHANGE_TO_MAX:
                logFile.println("RPM TO MAX VALUE: " + currentRPM + " RPM");
                break;
            case RPM_CHANGE_TO_CUSTOM_VALUE:
                logFile.println("RPM TO VALUE: " + currentRPM + " RPM");
        }
    }

    @Override
    public String toString() {
        return "Current RPM: " + currentRPM + " Maximum RPM: " + maximumRPM;
    }
}
