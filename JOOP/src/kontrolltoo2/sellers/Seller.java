package kontrolltoo2.sellers;

import kontrolltoo2.stock.Stock;
import kontrolltoo2.stockpapers.Bond;
import kontrolltoo2.stockpapers.Share;
import kontrolltoo2.stockpapers.StockPaper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Seller implements Runnable {

    private String id;
    private Stock stock;
    private String sellType;
    private int soldStockPapers;
    private List<BigInteger> soldAmounts = new ArrayList<>();
    private List<BigDecimal> soldPrices = new ArrayList<>();
    private Random random;
    private final List<String> paperShortNames = Arrays.asList("TKM1T", "TAL1T", "LHVB065025A");
    private final List<String> paperFullNames = Arrays.asList("Tallinna Kaubamaja Grupp",
            "Tallink Grupp", "LHV Grupp");

    private final int TIME_TO_SLEEP = 40;
    private final int MAX_STOCK_PAPERS_SOLD_PER_DAY = 10;

    public Seller(String id, Stock stock, String sellType) throws IllegalArgumentException {
        if (!sellType.equals("kaubamaja") && !sellType.equals("tallink") && !sellType.equals("lhv")) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.stock = stock;
        this.sellType = sellType;
        random = new Random();
        soldStockPapers = 0;
    }

    @Override
    public void run() {
        while (soldStockPapers != MAX_STOCK_PAPERS_SOLD_PER_DAY) {
            generateStockPaper();
            try {
                Thread.sleep(TIME_TO_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Seller " + id + " has finished!");
    }

    private void generateStockPaper() {
        StockPaper stockPaper;
        BigInteger amount;
        BigDecimal price;
        do {
            amount = BigInteger.valueOf(random.nextInt(30));
        }
        while (soldAmounts.contains(amount));
        do {
            price = BigDecimal.valueOf(random.nextInt(1000) / 100.0f);
        } while (soldAmounts.contains(amount));

        soldAmounts.add(amount);
        soldPrices.add(price);
        if (sellType.equals("kaubamaja")) {
            stockPaper = new Share(paperShortNames.get(0), paperFullNames.get(0), price, amount, BigDecimal.TEN);
        } else if (sellType.equals("tallink")) {
            stockPaper = new Share(paperShortNames.get(1), paperFullNames.get(1), price, amount, BigDecimal.ONE);
        } else {
            stockPaper = new Bond(paperShortNames.get(2), paperFullNames.get(2), price, amount,
                    LocalDate.of(2025, 10, 29));
        }

        System.out.println("Seller " + id + stockPaper.getInfo());
        stock.addStockPaper(stockPaper);
        soldStockPapers++;
    }
}
