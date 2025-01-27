import java.util.ArrayList;
import java.util.List;

// Interface representing a stock exchange
interface StockExchange {
    double getPrice(String stock); // Method to fetch the price of a given stock
}

// Concrete implementation for Zurich Stock Exchange
class ZurichStockExchange implements StockExchange {
    @Override
    public double getPrice(String stock) {
        // Return prices specific to the Zurich stock market
        switch (stock) {
            case "Microsoft": return 90.0;
            case "Apple": return 150.0;
            default: return 0.0;
        }
    }
}

// Concrete implementation for London Stock Exchange
class LondonStockExchange implements StockExchange {
    @Override
    public double getPrice(String stock) {
        // Return prices specific to the London stock market
        switch (stock) {
            case "Microsoft": return 85.0;
            case "Apple": return 145.0;
            default: return 0.0;
        }
    }
}

// Concrete implementation for New York Stock Exchange
class NewYorkStockExchange implements StockExchange {
    @Override
    public double getPrice(String stock) {
        // Return prices specific to the New York stock market
        switch (stock) {
            case "Microsoft": return 92.0;
            case "Apple": return 155.0;
            default: return 0.0;
        }
    }
}

// Class representing a stock
class Stock {
    private String name; // Name of the stock (e.g., Microsoft)
    private int quantity; // Quantity of the stock owned

    public Stock(String name, int quantity) {
        this.name = name; // Initialize stock name
        this.quantity = quantity; // Initialize stock quantity
    }

    public String getName() {
        return name; // Get the name of the stock
    }

    public int getQuantity() {
        return quantity; // Get the quantity of the stock
    }
}

// Portfolio class to manage stocks and calculate total value
class Portfolio {
    private List<Stock> stocks = new ArrayList<>(); // List to store stocks in the portfolio
    private StockExchange stockExchange; // Reference to the stock exchange for fetching prices

    public Portfolio(StockExchange stockExchange) {
        this.stockExchange = stockExchange; // Set the stock exchange for this portfolio
    }

    public void addStock(Stock stock) {
        stocks.add(stock); // Add a stock to the portfolio
    }

    public double calculateTotalValue() {
        double total = 0.0;
        // Iterate through stocks to calculate total value based on prices from the stock exchange
        for (Stock stock : stocks) {
            total += stock.getQuantity() * stockExchange.getPrice(stock.getName());
        }
        return total; // Return the total portfolio value
    }

    public void displayPortfolio() {
        System.out.println("Portfolio Details:");
        // Display each stock's name, quantity, and price
        for (Stock stock : stocks) {
            System.out.println("Stock: " + stock.getName() +
                    ", Quantity: " + stock.getQuantity() +
                    ", Price: " + stockExchange.getPrice(stock.getName()));
        }
        // Display the total portfolio value
        System.out.println("Total Portfolio Value: " + calculateTotalValue());
    }
}

// Main class to test the functionality
public class Main {
    public static void main(String[] args) {
        // Create instances of different stock exchanges
        StockExchange zurich = new ZurichStockExchange();
        StockExchange london = new LondonStockExchange();
        StockExchange newYork = new NewYorkStockExchange();

        // Portfolio with Zurich Stock Exchange
        Portfolio portfolio = new Portfolio(zurich);
        portfolio.addStock(new Stock("Microsoft", 10)); // Add 10 Microsoft stocks
        portfolio.addStock(new Stock("Apple", 5)); // Add 5 Apple stocks

        // Display portfolio using Zurich stock prices
        System.out.println("Using Zurich Stock Exchange:");
        portfolio.displayPortfolio();

        // Switch to London Stock Exchange
        portfolio = new Portfolio(london);
        portfolio.addStock(new Stock("Microsoft", 10)); // Add 10 Microsoft stocks
        portfolio.addStock(new Stock("Apple", 5)); // Add 5 Apple stocks

        // Display portfolio using London stock prices
        System.out.println("\nUsing London Stock Exchange:");
        portfolio.displayPortfolio();

        // Switch to New York Stock Exchange
        portfolio = new Portfolio(newYork);
        portfolio.addStock(new Stock("Microsoft", 10)); // Add 10 Microsoft stocks
        portfolio.addStock(new Stock("Apple", 5)); // Add 5 Apple stocks

        // Display portfolio using New York stock prices
        System.out.println("\nUsing New York Stock Exchange:");
        portfolio.displayPortfolio();
    }
}
