package praks13;

import praks13.tivoli.TivoliController;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        TivoliController tivoliController = new TivoliController();
        tivoliController.runSystem();
    }
}
