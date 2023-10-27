package business.interfaces;

public interface Player{
    public void spawn();
    public void visitFarm();
    public void visitCity();
    public void visitField();
    public void endDay();
    void addMoney(int money);

}