package business.implementations;

import business.interfaces.Farm;
import business.interfaces.Field;
import business.utils.MadManUtils;
import business.utils.PrintingUtilities;
import main.Context;

public class FarmImplementation implements Farm {
    public int scalePhosphor = 100;
    public double phosphorConsumationSpeed = 2.0; // This is for the SF project, to speed up use of phosphor!
    private double phosphorEffect = 5;
    Field field;
    public boolean isInFarm;
    public boolean isPhophorized;
    public int phosphorPrice = 10;
    private final int priceOfLand = 200;
    private final int yieldOfLand = 25;
    private int fieldsForPurchase = 5;
    // TODO make checker for scalePhosphor for events
    public int seasonCount = 1; //Integer that tells us the day we are currently at.
    // TODO When seasonCount hits certain values: Events
    //public int phosphor = 1; //Integer that tells us the amount of phosphor we have.
    public int seasonProgress = 0;
    public int land = 1;
    public void setIsPhosphorized(boolean z) {
        isPhophorized = z;
    }
    @Override
    public void reduceFieldsForPurchase() {
        fieldsForPurchase -= 1;
    }

    public void checkVictoryConditions() {
        if (Context.cityImplementation.isSppDone && Context.cityImplementation.isBfDone && Context.farmImplementation.scalePhosphor > 100) {
            PrintingUtilities.printOnScreen("Congrats, YOU HAVE WON!!!");

            main.Game.context.makeDone();
        }
    }

    @Override
    public int getFieldsForPurchase() {
        return fieldsForPurchase;
    }

    public int getPriceOfLand() {
        return priceOfLand;
    }

    public boolean getIsPhosphorized() {
        return isPhophorized;
    }

    public void fieldInterface(FieldImplementation fieldImplementation) {
        this.field = fieldImplementation;
    }

    @Override
    public int getScale() {
        return this.scalePhosphor;
    }

    @Override
    public void setIsInFarm(boolean z) {
        isInFarm = z;
    }

    @Override
    public boolean getIsInFarm() {
        return isInFarm;
    }


    public void checkSeasonProgress() {
        if (seasonProgress == 3) {
            endSeason();
        }
    }

    @Override
    public void endSeason() {
        //This is where we will type the code to end the day, but im not quite sure how we are gonna do that yet. We need the day counter and the time im pretty sure, before we can write the code for this.
        PrintingUtilities.printOnScreen("Ending Day " + seasonCount);
        //TODO All the events that happen after the day need to be implemented, eks: seed growth or events, university research projects.
        // This is so that the endDay command doesnt just contiously end the day, but we actually reset it, so we can type it again the next day.
        //field.sowSeed(1, phosphor, land);
        if ((seasonCount + 1) % 4 == 0) // making a year 4 seasons
            endYear();
        seasonProgress = 0;
        seasonCount += 1;
        PrintingUtilities.printOnScreen(MadManUtils.madEvent());
    }

    public double calculateProfit() {
        double profit;
        if (isPhophorized) {
            profit = land * phosphorEffect * yieldOfLand;
        } else {
            profit = land * 2 * yieldOfLand;
        }
        return profit;
    }

    public void calculateProjects() {
        if (Context.cityImplementation.isSfDone) {
            Context.farmImplementation.phosphorEffect = Context.farmImplementation.phosphorEffect * 2;
            Context.farmImplementation.phosphorConsumationSpeed = 3;
            PrintingUtilities.printOnScreen("Congrats, you now own a Super Farm!!!");
            Context.cityImplementation.availableProjectsList.remove("SuperFarm (SF)");
            Context.cityImplementation.isBfReady = true;
        }
        if (Context.cityImplementation.isBfDone) {
            Context.cityImplementation.isSfDone = false;
            Context.farmImplementation.phosphorEffect = Context.farmImplementation.phosphorEffect * 3;
            Context.farmImplementation.phosphorConsumationSpeed = 2;
            PrintingUtilities.printOnScreen("Congrats, you now own a Bio Farm!!!");
            Context.cityImplementation.availableProjectsList.remove("BioFarm (BF)");
        }
        if (Context.cityImplementation.isPpDone) {
            Context.farmImplementation.scalePhosphor = Context.farmImplementation.scalePhosphor + 1;
            PrintingUtilities.printOnScreen("Congrats, you now own a Purification Plant!!!");
            Context.cityImplementation.availableProjectsList.remove("PurificationPlant (PP)");
            Context.cityImplementation.isSppReady = true;
        }
        if (Context.cityImplementation.isSppDone) {
            Context.cityImplementation.isPpDone = false;
            Context.farmImplementation.scalePhosphor = Context.farmImplementation.scalePhosphor + 5;
            Context.farmImplementation.phosphorConsumationSpeed = 0.5;
            PrintingUtilities.printOnScreen("Congrats, you now own a Super Purification Plant!!!");
            Context.cityImplementation.availableProjectsList.remove("Super Purification Plant(SPP)");
        }
    }

    public boolean calculateIsHunger() {
        return Context.playerImplementation.money < Context.cityImplementation.population;
    }

    public void endYear() {
        PrintingUtilities.printOnScreen("Ending Year");
        Context.cityImplementation.quizzCount=0;
//        for (int i = 5; i > 0; i--) {
//            try {
//              //  Thread.sleep(400);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            PrintingUtilities.printOnScreen("* ");
//        }
        PrintingUtilities.printOnScreen("\nHarvesting the corn on you fields amounts to: ");
        PrintingUtilities.printOnScreen(String.valueOf(calculateProfit()));
        PrintingUtilities.printOnScreen(" gold!");
        Context.playerImplementation.addMoney(calculateProfit());
        PrintingUtilities.printOnScreen("Ending Year ");
        Context.cityImplementation.isHunger = calculateIsHunger();
        PrintingUtilities.printOnScreen("\nSo after harvesting you have: " + Context.playerImplementation.money + " gold.");
        PrintingUtilities.printOnScreen("But the people in Capitol City is hungry, and the population is: " + Context.cityImplementation.population);
        PrintingUtilities.printOnScreen("After delivering food to Capitol City you have: ");
        Context.playerImplementation.useMoney(Context.cityImplementation.population);
        PrintingUtilities.printOnScreen(Context.playerImplementation.money + " gold.");
        PrintingUtilities.printOnScreen("\nThe phosphor on your fields has sunken into the ground and does not have any effect on your harvest, you may need to buy more...");
        Context.farmImplementation.isPhophorized = false;
        calculateProjects();
        checkVictoryConditions();
        if (!Context.cityImplementation.isHunger) { // If there is no hunger hte population increments with 25 every week
            Context.cityImplementation.population += 25;
            PrintingUtilities.printOnScreen("Population is growing, city now has " + Context.cityImplementation.population + " inhabitants");
        } else {
            Context.cityImplementation.population /= 2; // If player can't feed the city, the population decrements to half the size!
            PrintingUtilities.printOnScreen("Hunger has hit the city, the population has fallen to " + Context.cityImplementation.population);

        }
        if (Context.playerImplementation.money <= 0) {
            PrintingUtilities.printOnScreen("You ran out of cash, too bad...");
            main.Game.context.makeDone();
        }

    }
}

