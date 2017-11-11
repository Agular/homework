package kodutoo6.car;

public class ControlButton {

    private boolean state = false;

    public void changeState(){
        state = !state;
    }

    public boolean isBlocked(){
        return state;
    }
}
