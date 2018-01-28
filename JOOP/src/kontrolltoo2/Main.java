package kontrolltoo2;

import kontrolltoo2.controllers.StockController;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        StockController controller = new StockController();
        controller.runSystem();
    }
}
