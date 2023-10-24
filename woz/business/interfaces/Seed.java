package business.interfaces;

public interface Seed {
    int seedAmount = 0;
    int valueSeed = 0;
    void calculateProfit();
    void sendMoney();
    boolean isSeedRipe();

}
