package business.interfaces;

public interface City {
    void setIsInCity(boolean z);

    boolean getIsInCity();

    void setIsInShop(boolean z);

    boolean getIsInShop();

    void setIsInUni(boolean z);

    boolean getIsInUni();

    int getHunger();

    int getPopulation();

    public void visitShop();

    public void visitUni();

    public String visitMadman();

    void calculateHunger();
}