import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StockMarket zurichMarket = new ZurichStockMarket();
        StockMarket newYorkMarket = new NewYorkStockMarket();


        Scanner scanner = new Scanner(System.in);


        System.out.println("Choose a stock market (Zurich or New York): ");
        String marketChoice = scanner.nextLine();


        StockMarket chosenMarket = null;
        if ("Zurich".equalsIgnoreCase(marketChoice)) {
            chosenMarket = zurichMarket;
        } else if ("New York".equalsIgnoreCase(marketChoice)) {
            chosenMarket = newYorkMarket;
        } else {
            System.out.println("Invalid stock market chosen. Defaulting to Zurich.");
            chosenMarket = zurichMarket;
        }


        Portfolio portfolio = new Portfolio(chosenMarket);


        while (true) {
            System.out.println("Enter a stock to add to your portfolio (or type 'done' to finish, 'stock' to see portfolio): ");
            String input = scanner.nextLine();

            if ("done".equalsIgnoreCase(input)) {
                break;
            } else if ("stock".equalsIgnoreCase(input)) {
                portfolio.showStocksWithValue();
            } else {
                portfolio.addStock(input);
            }
        }


        portfolio.showPortfolioValue();

        scanner.close();
    }
}
