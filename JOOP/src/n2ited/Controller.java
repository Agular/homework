package n2ited;

import java.awt.*;

public class Controller {
    public static void main(String[] args) {
        // Runnable, mille argumendiks on lambda
        Buyer buyer = new Buyer(car -> car.hasColor(Color.YELLOW));
    }
}
