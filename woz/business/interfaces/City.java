package business.interfaces;

public interface City {
    void setIsInCity(boolean z);

    void setIsInShop(boolean z);

    boolean getIsInShop();

    void setIsInUni(boolean z);

    boolean getIsInUni();

    int getPopulation();

    void visitShop();

    void visitUni();

    String visitMadman();

    boolean calculateHunger();
}