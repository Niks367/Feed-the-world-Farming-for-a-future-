package business.implementations;

import business.interfaces.Player;

import java.sql.SQLOutput;

public class PlayerImplementation implements Player {
    public double money;

    @Override
    public void spawn() {
        this.money = 150.00;
    }

    @Override
    public void addMoney(double money) {
        this.money += money;
    }

    @Override
    public void useMoney(double money) { this.money -= money; }

    public void playerBalance(){
        System.out.println("Your balance is: $" + money);
    }
}
