package business.interfaces;

public interface Player {
    void spawn();

    void visitFarm();

    void visitCity();

    void visitField();

    void endDay();

    void addMoney(int money);

}