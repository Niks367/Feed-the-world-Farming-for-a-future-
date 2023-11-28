package business.implementations;

import business.interfaces.Farm;
import business.interfaces.Field;
import business.utils.MadManUtils;
import business.utils.PrintingUtilities;
import main.*;
import presentation.controllers.RoomController;

public class FarmImplementation implements Farm {
    public int scalePhosphor = 100;
    public double phosphorConsumationSpeed = 1.0; // This is for the SF project, to speed up use of phosphor!
    private double phosphorEffect = 5;
    Field field;
    public boolean isInFarm;
    public boolean isPhophorized;
    public int phosphorPrice = 10;
    private final int priceOfLand = 100;
    private final int yieldOfLand = 25;
    private int fieldsForPurchase = 5;
    // TODO make checker for scalePhosphor for events
    public int dayCount = 0; //Integer that tells us the day we are currently at.
    // TODO When dayCount hits certain values: Events
    //public int phosphor = 1; //Integer that tells us the amount of phosphor we have.
    public int dayProgress = 0;
    public int land = 1;
    public void setIsPhosphorized(boolean z) {
        isPhophorized = z;
    }
    @Override
    public void reduceFieldsForPurchase() {
        fieldsForPurchase -= 1;
    }

    public void checkVictoryConditions() {
        if (Context.cityImplementation.isPpDone && Context.farmImplementation.scalePhosphor > 100) {
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


    //TODO do some changes to this later, so it works with the day count.
    @Override
    public void runDay(String input) {
        // This will be displayed when the game is launched and display this message, so people playing will know how to end the day.
        if ("end_day".equalsIgnoreCase(input)) {
            endDay(); //Space for the method to define how we end the day, I haven't filled it out yet cuz im not sure how to do it.
            //dayCount += 1; //Adds days to the dayCount integer, so everytime we end the day, the dayCount integer goes up by one.
        } else {
            PrintingUtilities.printOnScreen("Type 'end_day' to end the day:");
        }
    }

    @Override
    public void dayCount(String input) {
        if ("days".equalsIgnoreCase(input)) {
            PrintingUtilities.printOnScreen("You are currently on Day " + dayCount);
        }
    }

    public void checkDayProgress() {
        if (dayProgress == 2) {
            endDay();
        }
    }

    @Override
    public void endDay() {
        //This is where we will type the code to end the day, but im not quite sure how we are gonna do that yet. We need the day counter and the time im pretty sure, before we can write the code for this.
        PrintingUtilities.printOnScreen("Ending Day " + dayCount);
        //TODO All the events that happen after the day need to be implemented, eks: seed growth or events, university research projects.
        // This is so that the endDay command doesnt just contiously end the day, but we actually reset it, so we can type it again the next day.
        //field.sowSeed(1, phosphor, land);
        if ((dayCount + 1) % 4 == 0) // making a week 4 days
            endWeek();
        dayProgress = 0;
        dayCount += 1;
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
        }
        if (Context.cityImplementation.isSuperPpDone) {
            Context.cityImplementation.isPpDone = false;
            Context.farmImplementation.scalePhosphor = Context.farmImplementation.scalePhosphor + 5;
            Context.farmImplementation.phosphorConsumationSpeed = 0.5;
            PrintingUtilities.printOnScreen("Congrats, you now own a Super Purification Plant!!!");
            Context.cityImplementation.availableProjectsList.remove("Super Purification Plant(SuperPP)");
        }
    }

    public boolean calculateIsHunger() {
        return Context.playerImplementation.money < Context.cityImplementation.population;
    }

    public void endWeek() {
        PrintingUtilities.printOnScreen("Ending Week ");
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
        PrintingUtilities.printOnScreen("Ending Week ");
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
            Game.context.makeDone();
        }

    }
}

