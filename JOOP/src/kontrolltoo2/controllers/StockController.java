package kontrolltoo2.controllers;

import kontrolltoo2.buyers.Buyer;
import kontrolltoo2.sellers.Seller;
import kontrolltoo2.stock.Stock;
import kontrolltoo2.stockpapers.StockPaper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StockController {

    public void runSystem() throws InterruptedException, ExecutionException {

        Stock stock = new Stock();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Seller> sellers = Arrays.asList(
                new Seller("KaubamajaSeller", stock, "kaubamaja"),
                new Seller("TallinkSeller", stock, "tallink"),
                new Seller("LHVSeller", stock, "lhv"));


        Buyer mari = new Buyer("Mari", stock,
                stockPaper -> stockPaper.getFullPrice().compareTo(new BigDecimal("200.0")) == -1);
        Buyer ants = new Buyer("Ants", stock,
                stockPaper -> stockPaper.getShortName().equals("TAL1T"));
        Buyer mati = new Buyer("Mati", stock,
                stockPaper -> stockPaper.getShortName().equals("TAL1T") ||
                        stockPaper.getShortName().equals("LHVB065025A"));
        Buyer ragnar = new Buyer("Ragnar", stock,
                stockPaper -> stockPaper.getFullPrice().compareTo(new BigDecimal("100.0")) >= 0 &&
                        stockPaper.getFullPrice().compareTo(new BigDecimal("200.0")) <= 0);

        List<Thread> sellerThreads = new ArrayList<>();
        List<Future<Stream<StockPaper>>> buyerBoughtPapers = new ArrayList<>();
        for (Seller seller : sellers) {
            sellerThreads.add(new Thread(seller));
        }
        sellerThreads.forEach(Thread::start);

        Future<Stream<StockPaper>> mariBoughtPapers = executorService.submit(mari);
        Future<Stream<StockPaper>> antsBoughtPapers = executorService.submit(ants);
        Future<Stream<StockPaper>> matiBoughtPapers = executorService.submit(mati);
        Future<Stream<StockPaper>> ragnarBoughtPapers = executorService.submit(ragnar);

        for (Thread sellerThread : sellerThreads) {
            sellerThread.join();
        }
        System.out.println("Join of threads successful!");
        executorService.shutdownNow();
        //executorService.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Executor shutdown!" + mariBoughtPapers.isDone());


        BigDecimal mariTotalPrice = mariBoughtPapers.get().map(StockPaper::getFullPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal antsTotalPrice = antsBoughtPapers.get().map(StockPaper::getFullPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal matiTotalPrice = matiBoughtPapers.get().map(StockPaper::getFullPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal ragnarTotalPrice = ragnarBoughtPapers.get().map(StockPaper::getFullPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Mari bought papers for a total price of: " + mariTotalPrice);
        System.out.println("Ants bought papers for a total price of: " + antsTotalPrice);
        System.out.println("Mati bought papers for a total price of: " + matiTotalPrice);
        System.out.println("Ragnar bought papers for a total price of: " + ragnarTotalPrice);

    }
}
