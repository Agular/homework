package kodutoo6.windows;

import kodutoo6.car.ControlButton;

import java.util.Arrays;
import java.util.List;

public class Window {

    private final int MAX_STATE = 10;
    private final int MIN_STATE = 0;
    private final int STATE_INCREMENT = 2;
    private static final List<String> frontTypes = Arrays.asList("A", "B");
    private static final List<String> rearTypes = Arrays.asList("C", "D");

    private int state;
    private String type;

    Window(String type) {
        state = MIN_STATE;
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void open() {
        if (state != MAX_STATE) {
            state = 10;
            System.out.println("Opened "+ this.toString());
        } else throw new IllegalStateException();
    }

    public void openPartially() {
        if (state != MAX_STATE) {
            state += STATE_INCREMENT;
            System.out.println("Opened partially "+ this.toString());
        } else throw new IllegalStateException();
    }

    public void close() {
        if (state != MIN_STATE) {
            state = 0;
            System.out.println("Closed "+ this.toString());
        } else throw new IllegalStateException();
    }

    public void closePartially() {
        if (state != MIN_STATE) {
            state -= STATE_INCREMENT;
            System.out.println("Closed partially "+ this.toString());
        } else throw new IllegalStateException();
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "Window " + this.getType() + "\nState " + this.getState();
    }

    public static Window getNewWindow(String type) {
        if (frontTypes.contains(type)) {
            return new Window(type);
        } else throw new IllegalArgumentException();
    }

    public static Window getNewRearWindow(String type, ControlButton button) {
        if (rearTypes.contains(type) || button != null) {
            return new RearWindow(type, button);
        } else throw new IllegalArgumentException();
    }
}
