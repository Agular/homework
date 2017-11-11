package kodutoo6.windows;

import kodutoo6.car.ControlButton;

public class RearWindow extends Window {

    private ControlButton buttonK;

    RearWindow(String type, ControlButton buttonK) {
        super(type);
        this.buttonK = buttonK;
    }

    @Override
    public void openPartially() {
        if (!buttonK.isBlocked()) {
            super.openPartially();
        } else throw new RuntimeException("Window opening is currently blocked!\n" + "State" + super.getState());
    }

    @Override
    public void open() {
        if (!buttonK.isBlocked()) {
            super.open();
        } else throw new RuntimeException("Window opening is currently blocked!\n" + "State" + super.getState());
    }
}
