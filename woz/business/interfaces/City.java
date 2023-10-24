package business.interfaces;

public interface City {
    boolean setIsInCity(boolean z);
    boolean getIsInCity();
    int getHunger();
    int getPopulation();
    public void visitShop();
    public void visitUni();
    public void visitMadman();
    void calculateHunger();
}