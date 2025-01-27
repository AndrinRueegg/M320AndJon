import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private StockMarket stockMarket;
    private List<String> stocks;

    public Portfolio(StockMarket stockMarket) {
        this.stockMarket = stockMarket;
        this.stocks = new ArrayList<>();
    }

    public void addStock(String stock) {
        this.stocks.add(stock);
    }

    public double calculateValue() {
        double totalValue = 0.0;
        for (String stock : stocks) {
            totalValue += stockMarket.getStockPrice(stock);
        }
        return totalValue;
    }

    public void showPortfolioValue() {
        System.out.println("Portfolio value: " + calculateValue() + " units");
    }


    public void showStocksWithValue() {
        if (stocks.isEmpty()) {
            System.out.println("No stocks in the portfolio.");
        } else {
            System.out.println("Stocks in the portfolio with their values:");
            for (String stock : stocks) {
                double stockValue = stockMarket.getStockPrice(stock);
                System.out.println(stock + ": " + stockValue + " units");
            }
        }
    }
}
