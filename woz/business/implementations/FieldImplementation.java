package business.implementations;

import business.interfaces.Field;
import business.interfaces.Player;

public class FieldImplementation implements Field {
    FarmImplementation farmImplementation;
    SeedImplementation seedsInField; //oprettes for at kunne bruge Seed klassen.
    private double phosphorEffekt = 20; //jeg har gjort fertilizer effekt til en ratio af 1:20, for at gøre den meget ekstrem.
    public int land = 1; // antal marker

    // fertilizer tager antallet frø i marken, og * med 20, som er gødningseffekten lige nu.
    @Override
    public void fertilize(int amount, int phosphor, int land) {
        if (phosphor >=land) {
            seedsInField.seedAmount += (int) (2*(land * phosphorEffekt));
            farmImplementation.phosphor -= land; // hver mark bruger en phosphor
        } else {
            seedsInField.seedAmount *= 2*land;
        }
    }
    public void farmInterface(FarmImplementation farmImplementation) {
        this.farmImplementation = farmImplementation;
    }
    public void seedInterface(SeedImplementation seedImplementation) {
        this.seedsInField = seedImplementation;
    }

    // metode som returnere mængden af Seeds spilleren har, hvordan den skal printes ud, det ved jeg ikke.
    @Override
    public int checkSeed() {
        return seedsInField.seedAmount;
    }

    @Override
    public void sowSeed(int seedsToSow, int phosphor, int land) {
        fertilize(seedsToSow, phosphor, land);
    }

    //metoden til at så frø. kalder på seedAmount i Seed-klassen, for at få den aktuelle mængde seeds.

    /*Metode til at høste sine frø, den tjekker om der er nogle frø i marken, ved hjælp af constructoren,
       hvorefter den øger mængden af seedsToHarvest, indtil seedsInField er tom. hvorefter den printer linjen.
        */
    @Override
    public void harvestSeed(int seedsToHarvest){
        if (seedsToHarvest <= seedsInField.seedAmount) {
            seedsInField.seedAmount += seedsToHarvest;
        } else {
            System.out.println("Not enough seeds to harvest. Should've used more fertilizer...");
        }
    }
}
