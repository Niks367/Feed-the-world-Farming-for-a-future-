package business.interfaces;

public interface Field {
    int checkSeed();

    void sowSeed(int seed, int phosphor);

    void harvestSeed(int seed);

    void fertilize(int amount, int phosphor);
}