package kodutoo6.car;

import kodutoo6.windows.Window;

import java.util.Optional;

public class Car {

    private ControlButton buttonK;
    private Window windowA;
    private Window windowB;
    private Window windowC;
    private Window windowD;

    public ControlButton getButtonK() {
        return buttonK;
    }

    public void setButtonK(ControlButton buttonK) {
        this.buttonK = buttonK;
    }

    public Window getWindowA() {
        return windowA;
    }

    public Window getWindowB() {
        return windowB;
    }

    public Optional<Window> getWindowC() {
        return Optional.ofNullable(windowC);
    }

    public Optional<Window> getWindowD() {
        return Optional.ofNullable(windowD);
    }

    public void setWindowA(Window windowA) {
        this.windowA = windowA;
    }

    public void setWindowB(Window windowB) {
        this.windowB = windowB;
    }

    public void setWindowC(Window windowC) {
        this.windowC = windowC;
    }

    public void setWindowD(Window windowD) {
        this.windowD = windowD;
    }
}
