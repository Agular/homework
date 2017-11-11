package kodutoo6;

import kodutoo6.car.Car;
import kodutoo6.car.ControlButton;
import kodutoo6.windows.Window;

import javax.naming.ldap.Control;

public class MainTester {
    public static void main(String[] args) {
        ControlButton buttonK = new ControlButton();
        Window windowA = Window.getNewWindow("A");
        Window windowB = Window.getNewWindow("B");
        Window windowC = Window.getNewRearWindow("C", buttonK);
        Window windowD = Window.getNewRearWindow("D", buttonK);

/* button k on OFF seisundis*/

    /*aken avatakse 20%*/
        windowA.openPartially();
        windowB.openPartially();
        windowC.openPartially();
        windowD.openPartially();

    /*aken avatakse täielikult*/
        windowA.open();
        windowB.open();
        windowC.open();
        windowD.open();

        /*aken avatakse 20%*/
        try{
            windowA.openPartially();
        } catch(IllegalStateException e){
            System.out.println("window is already opened");
        }
        try{
            windowB.openPartially();
        } catch(IllegalStateException e){
            System.out.println("window is already opened");
        }
        try{
            windowC.openPartially();
        } catch(IllegalStateException e){
            System.out.println("window is already opened");
        }
        try{
            windowD.openPartially();
        } catch(IllegalStateException e){
            System.out.println("window is already opened");
        }

        /*aken Sulgetakse 20%*/
        windowA.closePartially();
        windowB.closePartially();
        windowC.closePartially();
        windowD.closePartially();

        /*aken sulgetakse täielikult*/
        windowA.close();
        windowB.close();
        windowC.close();
        windowD.close();

        try{
            windowA.closePartially();
        } catch(IllegalStateException e){
            System.out.println("window is already closed");
        }
        try{
            windowB.closePartially();
        } catch(IllegalStateException e){
            System.out.println("window is already closed");
        }
        try{
            windowC.closePartially();
        } catch(IllegalStateException e){
            System.out.println("window is already closed");
        }
        try{
            windowD.closePartially();
        } catch(IllegalStateException e){
            System.out.println("window is already closed");
        }

        /*aken avatakse 20%*/
        windowA.closePartially();
        windowB.closePartially();
        windowC.closePartially();
        windowD.closePartially();


        Car fourDoorCar = new Car();
        Car twoDoorCar = new Car();

        ControlButton fourDoorCarControlButton = new ControlButton();
        ControlButton twoDoorCarControlButton = new ControlButton();

        fourDoorCar.setButtonK(fourDoorCarControlButton);
        twoDoorCar.setButtonK(twoDoorCarControlButton);

        fourDoorCar.setWindowA(Window.getNewWindow("A"));
        fourDoorCar.setWindowB(Window.getNewWindow("B"));
        fourDoorCar.setWindowC(Window.getNewRearWindow("C", fourDoorCarControlButton));
        fourDoorCar.setWindowC(Window.getNewRearWindow("D", fourDoorCarControlButton));

        twoDoorCar.setWindowA(Window.getNewWindow("A"));
        twoDoorCar.setWindowB(Window.getNewWindow("B"));
    }
}
