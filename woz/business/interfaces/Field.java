package business.interfaces;

public interface Field {
    int checkSeed();

    void sowSeed(int seed);

    void harvestSeed(int seed);

    void fertilize();
}