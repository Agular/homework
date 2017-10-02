package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Controll the controllables.
 */
public class Controller {
    /***/
    @FXML
    private TextField magicText;
    /***/
    @FXML
    private Button magicButton;
    /***/
    @FXML
    private Label magicLabel;
    /***/
    @FXML
    private Canvas magicCanvas;
    /***/
    public static final int WIDTH = 300;
    /***/
    public static final int HEIGHT = 300;
    /***/
    public static final int ORDS_LENGHT = 3;
    /***/
    public static final int N150 = 150;
    /***/
    public static final int N125 = 125;
    /***/
    public static final int N90 = 90;
    /***/
    public static final int N120 = 120;
    /***/
    public static final int N137 = 137;
    /***/
    public static final int N30 = 30;
    /***/
    public static final int N60 = 60;
    /***/
    public static final int N80 = 80;

    /**
     * Do some magic and then do some more magic.
     *
     * @param event Magic event.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Button was clicked, do something...
        String magicInput = magicText.getText();
        String[] magicOrds = magicInput.split(" ");
        if (magicOrds.length == ORDS_LENGHT) {
            if (magicOrds[0].matches("^[0-9]+$") && magicOrds[1].matches("^[0-9]+$")
                    && magicOrds[2].matches("^[0-9]+$")) {
                GraphicsContext gc = magicCanvas.getGraphicsContext2D();
                gc.clearRect(0, 0, WIDTH, HEIGHT);
                int magicX = Integer.parseInt(magicOrds[0]);
                int magicY = Integer.parseInt(magicOrds[1]);
                int magicR = Integer.parseInt(magicOrds[2]);
                gc.fillOval(magicX, magicY, magicR, magicR);
            } else {
                magicLabel.setText(magicInput);
            }
        } else if (magicInput.equals("snowman")) {
            GraphicsContext gc = magicCanvas.getGraphicsContext2D();
            gc.clearRect(0, 0, WIDTH, HEIGHT);
            gc.setFill(Color.BLUE);
            gc.fillOval(N125, N150, N120, N120);
            gc.fillOval(N137, N80, N90, N90);
            gc.fillOval(N150, N30, N60, N60);
        } else {
            magicLabel.setText(magicInput);
        }
    }

}
