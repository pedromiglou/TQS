package tqsua.p1stocks;

import java.util.ArrayList;

public class StocksPortfolio {
    private String name;
    private ArrayList<Stock> stocks = new ArrayList<>();
    private IStockMarket marketService;

    public IStockMarket getMarketService() {
        return marketService;
    }

    public void setMarketService(IStockMarket marketService) {
        this.marketService = marketService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalValue() {
        Double sum = 0.0;
        for (Stock s: stocks) {
            sum += s.getQuantity() * marketService.getPrice(s.getName());
        }
        return sum;
    }

    public void addStock(Stock s) {
        stocks.add(s);
    }
}
