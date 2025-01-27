public class NewYorkStockMarket implements StockMarket {
    @Override
    public double getStockPrice(String stock) {

        if ("Microsoft".equals(stock)) {
            return 80;
        } else if ("Apple".equals(stock)) {
            return 140;
        }
        return 0.0;
    }
}
