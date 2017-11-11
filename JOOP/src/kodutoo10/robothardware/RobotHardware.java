package kodutoo10.robothardware;

public class RobotHardware {

    private Motor motor;
    private Gearbox gearbox;

    public RobotHardware(Motor motor, Gearbox gearbox) {
        this.motor = motor;
        this.gearbox = gearbox;
    }

    public Motor getMotor() {
        return motor;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    @Override
    public String toString() {
        return motor.toString() + "\n" +
                gearbox.toString();
    }

}
