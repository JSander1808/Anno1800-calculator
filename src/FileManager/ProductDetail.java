package FileManager;

public class ProductDetail {

    private double trade;
    private double level;

    public ProductDetail(double trade, double level){
        this.trade=trade;
        this.level=level;
    }

    public double getLevel() {
        return level;
    }

    public double getTrade() {
        return trade;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public void setTrade(double trade) {
        this.trade = trade;
    }
}
