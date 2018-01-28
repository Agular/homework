package kontrolltoo2.buyers;

import kontrolltoo2.stock.Stock;
import kontrolltoo2.stockpapers.StockPaper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Buyer implements Callable<Stream<StockPaper>> {

    private String name;
    private Stock stock;
    private Predicate<StockPaper> buyCondition;
    private List<StockPaper> boughtStockPapers;

    public Buyer(String name, Stock stock, Predicate<StockPaper> buyCondition) {
        this.name = name;
        this.stock = stock;
        this.buyCondition = buyCondition;
        boughtStockPapers = new ArrayList<>();
    }

    private void buyStockPaper() throws InterruptedException {
        StockPaper stockPaper = stock.getStockPaper(buyCondition);
        //System.out.println("Buyer " + name + " bought StockPaper\n" + stockPaper.getInfo());
        boughtStockPapers.add(stockPaper);
    }

    @Override
    public Stream<StockPaper> call() {
        while (!Thread.interrupted()) {
            try {
                buyStockPaper();
            } catch (InterruptedException e) {
                break;
            }
        }
        return boughtStockPapers.stream();
    }
}
