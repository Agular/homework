package kontrolltoo2.stock;

import kontrolltoo2.stockpapers.StockPaper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Stock {

    private List<StockPaper> stockPapers;

    public Stock() {
        stockPapers = new ArrayList<>();
    }

    public synchronized void addStockPaper(StockPaper stockPaper) {
        stockPapers.add(stockPaper);
        notifyAll();
    }

    public synchronized StockPaper getStockPaper(Predicate<StockPaper> buyCondition) throws InterruptedException {
        while(true){
            while (stockPapers.isEmpty()) {
                wait();
            }
            List<StockPaper> foundStockPapers = stockPapers.stream()
                    .filter(buyCondition)
                    .collect(Collectors.toList());
            if (!foundStockPapers.isEmpty()) {
                StockPaper foundPaper = foundStockPapers.remove(0);
                stockPapers.remove(foundPaper);
                return foundPaper;
            } else {
                wait();
            }
        }
    }
}