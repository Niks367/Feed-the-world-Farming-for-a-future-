package business.implementations;

import business.interfaces.City;

public class CityImplementation implements City {
    int hunger = 0;
    boolean isInCity;
    int population = 0;

    @Override
    public boolean setIsInCity(boolean z) {
        return isInCity = z;
    }

    @Override
    public boolean getIsInCity() {
        return isInCity;
    }

    @Override
    public int getHunger() {
        return this.hunger;
    }

    @Override
    public int getPopulation() {
        return this.population;
    }

    @Override
    public void visitShop() {
        //Something
    }

    @Override
    public void visitUni() {
        //Something
    }

    @Override
    public void visitMadman() {
        //Something
    }

    @Override
    public void calculateHunger() {

    }
}
