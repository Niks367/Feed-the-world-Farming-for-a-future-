package business.implementations;

import business.interfaces.Player;

public class PlayerImplementation implements Player {
    public int money;
    @Override
    public void spawn() {
        this.money = 150;
    }

    @Override
    public void addMoney(int money) {
        this.money += money;
    }
    @Override
    public void useMoney(int money) { this.money -= money; }
}
