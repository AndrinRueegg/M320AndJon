public class ZurichStockMarket implements StockMarket {
    @Override
    public double getStockPrice(String stock) {

        if ("Microsoft".equals(stock)) {
            return 90;
        } else if ("Apple".equals(stock)) {
            return 150;
        }
        return 0.0;
    }
}
