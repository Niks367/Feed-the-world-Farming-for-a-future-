package business.interfaces;

public interface City {
    void setIsInCity(boolean z);
    boolean getIsInCity();
    int getHunger();
    int getPopulation();
    public void visitShop();
    public void visitUni();
    public String visitMadman();
    void calculateHunger();
}