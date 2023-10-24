package business.interfaces;

public interface Field {
    public Seed checkSeed();
    public void sowSeed(Seed seed);
    public void harvestSeed(Seed seed);
    public void fertilize();
}