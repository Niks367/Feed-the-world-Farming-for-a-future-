package business.implementations;

import business.interfaces.Farm;
import main.*;

public class FarmImplementation implements Farm {
    public int scalePhosphor = 100;
    private double phosphorEffect = 5;
    FieldImplementation fieldImplementation;
    public boolean isInFarm;
    public boolean isPhophorized;
    public int phosphorPrice = 10;
    public void setIsPhosphorized(boolean z) {
        isPhophorized = z;
    }
    private int priceOfLand = 100;
    private int yieldOfLand = 25;
    private int fieldsForPurchase = 5;
    @Override
    public void reduceFieldsForPurchase() {
        fieldsForPurchase -= 1;
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
    public void printOnScreen(String text) {
        System.out.println(text);
    }
    public void fieldInterface(FieldImplementation fieldImplementation){
        this.fieldImplementation = fieldImplementation;
    }
    // TODO make checker for scalePhosphor for events
    public int dayCount = 0; //Integer that tells us the day we are currently at.
    // TODO When dayCount hits certain values: Events
    public int phosphor = 0; //Integer that tells us the amount of phosphor we have.
    public int dayProgress = 0;
    public int land = 1;
    private boolean endDay = false; // We initialize the boolean for endDay, so it is defined in our code and we can use it later to end the day.

    //TODO do some changes to this later, so it works with the day count.
    @Override
    public void runDay(String input) {
        // This will be displayed when the game is launched and display this message, so people playing will know how to end the day.
        if ("end_day".equalsIgnoreCase(input)) {
            endDay = true;
            endDay(); //Space for the method to define how we end the day, I haven't filled it out yet cuz im not sure how to do it.
            //dayCount += 1; //Adds days to the dayCount integer, so everytime we end the day, the dayCount integer goes up by one.
        } else {
            System.out.println("Type 'end_day' to end the day:");
        }


    }

    @Override
    public void dayCount(String input) {
        if ("days".equalsIgnoreCase(input)) {
            System.out.println("You are currently on Day " + dayCount);
        }
    }
    public void checkDayprogress () {
        if (dayProgress > 2) {
            endDay();
        }
    }

    @Override
    public void endDay() {
        //This is where we will type the code to end the day, but im not quite sure how we are gonna do that yet. We need the day counter and the time im pretty sure, before we can write the code for this.
        System.out.println("Ending Day "+dayCount);
        //TODO All the events that happen after the day need to be implemented, eks: seed growth or events, university research projects.
        // This is so that the endDay command doesnt just contiously end the day, but we actually reset it, so we can type it again the next day.
        fieldImplementation.sowSeed(1, phosphor, land);
        if((dayCount+1)%3 == 0) // making a week 3 days
            endWeek();
        endDay = false;
        dayProgress = 0;
        dayCount += 1;
    }
    public double calculateProfit() {
        double profit = 0;
        if(isPhophorized) {
            profit = land*phosphorEffect*yieldOfLand;
        } else{
            profit = land*2*yieldOfLand;
        }
        return profit;
    }
    public boolean calculateIsHunger() {
        if (Context.playerImplementation.money < Context.cityImplementation.population) {
            return true;
        }
        else
            return false;
    }
    public void endWeek() {
        System.out.println("Ending Week ");
        for (int i = 5; i > 0; i--) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(".");
        }
        System.out.println("");
        System.out.println("Harvesting the corn on you fields amounts to: ");
        System.out.println(calculateProfit());
        Context.playerImplementation.addMoney(calculateProfit());
        Context.cityImplementation.isHunger = calculateIsHunger();
        System.out.println("You now have: "+Context.playerImplementation.money+" money.");
        System.out.println("The city needs food, and the population is: " + Context.cityImplementation.population);
        System.out.println("After paying for food you have: ");
        Context.playerImplementation.useMoney(Context.cityImplementation.population);
        System.out.print(Context.playerImplementation.money+ " money.");
        System.out.println("The phosphor on your fields has sunken into the ground and does not have any effect on your harvest, you may need to buy more...");
        Context.farmImplementation.isPhophorized = false;

        if(!Context.cityImplementation.isHunger) { // If there is no
            Context.cityImplementation.population += 25;
            System.out.println("Population is growing, city now has " + Context.cityImplementation.population + " inhabitants");

            if(Context.playerImplementation.money <= 0) {
                System.out.println("Game Over");
                main.Game.context.makeDone();
            }
        }else {
            Context.cityImplementation.population /= 2;
            printOnScreen("Hunger has hit the city, the population has fallen to "+Context.cityImplementation.population);
            if(Context.playerImplementation.money <= 0) {
                System.out.println("Game Over");
                main.Game.context.makeDone();
            }

        }


    }
}

