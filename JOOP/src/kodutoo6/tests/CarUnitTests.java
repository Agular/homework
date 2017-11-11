package kodutoo6.tests;

import kodutoo6.car.ControlButton;
import kodutoo6.windows.Window;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.SendingContext.RunTime;

public class CarUnitTests {

    private Window frontWindow;
    private Window rearWindow;
    private ControlButton buttonK;

    @Before
    public void setupBeforeEachTest() {
        buttonK = new ControlButton();
        frontWindow = Window.getNewWindow("A");
        rearWindow = Window.getNewRearWindow("C", buttonK);
    }

    @Test
    public void FrontWindowInitialState() {
        Assert.assertEquals(0, frontWindow.getState());
    }

    @Test
    public void FrontWindowType() {
        Assert.assertEquals("A", frontWindow.getType());
    }

    @Test
    public void BackWindowType() {
        Assert.assertEquals("C", rearWindow.getType());
    }

    @Test
    public void openFrontWindowPartially() {
        frontWindow.openPartially();
        Assert.assertEquals(2, frontWindow.getState());
    }

    @Test
    public void openFrontWindowPartiallyFiveTimes() {
        for (int i = 0; i < 5; i++) {
            frontWindow.openPartially();
        }
        Assert.assertEquals(10, frontWindow.getState());
    }

    @Test(expected = IllegalStateException.class)
    public void openFrontWindowPartiallyOverLimit() {
        for (int i = 0; i < 6; i++) {
            frontWindow.openPartially();
        }
    }

    @Test
    public void openFrontWindowFully() {
        frontWindow.open();
        Assert.assertEquals(10, frontWindow.getState());
    }

    @Test(expected = IllegalStateException.class)
    public void openFrontWindowFullyTwoTimes() {
        frontWindow.open();
        frontWindow.open();
    }

    @Test
    public void closeFrontWindowPartiallyFromPartiallyOpenState() {
        frontWindow.openPartially();
        frontWindow.openPartially();
        frontWindow.closePartially();
        Assert.assertEquals(2, frontWindow.getState());
    }

    @Test(expected = IllegalStateException.class)
    public void closeFrontWindowPartiallyFromPartiallyOpenStateOverLimit() {
        frontWindow.openPartially();
        frontWindow.openPartially();
        frontWindow.closePartially();
        frontWindow.closePartially();
        frontWindow.closePartially();
    }

    @Test
    public void closeFrontWindowFullyFromOpenState() {
        frontWindow.open();
        frontWindow.close();
        Assert.assertEquals(0, frontWindow.getState());
    }

    @Test(expected = IllegalStateException.class)
    public void closeFrontWindowFullyFromOpenStateTwoTimes() {
        frontWindow.open();
        frontWindow.close();
        frontWindow.close();
    }

    @Test(expected = RuntimeException.class)
    public void openPartiallyRearWindowBlockedButton(){
        buttonK.changeState();
        rearWindow.openPartially();
        Assert.assertEquals(0, rearWindow.getState());
    }

    @Test(expected = RuntimeException.class)
    public void openRearWindowBlockedButton(){
        buttonK.changeState();
        rearWindow.open();
        Assert.assertEquals(0, rearWindow.getState());
    }

    @Test
    public void closeRearWindowPartiallyBlockedButtonFromPartiallyOpenState(){
        rearWindow.openPartially();
        rearWindow.openPartially();
        buttonK.changeState();
        rearWindow.closePartially();
        Assert.assertEquals(2, rearWindow.getState());
    }


}
