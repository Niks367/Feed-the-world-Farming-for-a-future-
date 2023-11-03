package business.implementations;

import business.interfaces.Player;

public class PlayerImplementation implements Player {
    public int money;
    @Override
    public void spawn() {
        this.money = 100;
    }

    @Override
    public void addMoney(int money) {
        this.money += money;
    }
}
