package business.implementations;

import business.interfaces.Farm;
import business.interfaces.Field;
import business.utils.MadManUtils;
import business.utils.PrintingUtilities;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Context;

import java.io.IOException;

import static main.Context.cityImplementation;
import static main.Context.playerImplementation;

public class FarmImplementation implements Farm {
    public int scalePhosphor = 100;
    public double phosphorConsumationSpeed = 2.0; // This is for the SF project, to speed up use of phosphor!
    private double phosphorEffect = 3;
    Field field;
    public boolean isInFarm;
    public boolean isPhophorized;
    public int phosphorPrice = 15;
    public final int priceOfLand = 150;
    private final int yieldOfLand = 20;
    public int fieldsForPurchase = 5;
    public boolean isGameEnded = false;

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

    // Next 3: Last year's numbers for endYearTextPrint() in RoomController, since endYear has already happened here when printing.
    public double calculatedProfit;
    public double lastYearsTax;
    public double lastYearsMoney;
    public boolean lastYearsHunger;

    public void checkVictoryConditions() {
        if (Context.cityImplementation.isSppDone && Context.cityImplementation.isBfDone && Context.farmImplementation.scalePhosphor > 100) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/rooms/fxml/victory.fxml"));
            isGameEnded = true;
            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setResizable(false); // Prevent resizing of the window
            stage.setScene(new Scene(parent));
            stage.show();
            Platform.setImplicitExit(true); // when closing window game stops (system exit)
            stage.setOnCloseRequest((ae) -> {
                Platform.exit();
                System.exit(0);
            });


        }
    }

    public void checkLoosingConditions() {
        if (Context.farmImplementation.scalePhosphor <= 0 || cityImplementation.getPopulation() <= 0) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/rooms/fxml/gameOver.fxml"));
            isGameEnded = true;
            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setResizable(false); // Prevent resizing of the window
            stage.setScene(new Scene(parent));
            stage.show();
            Platform.setImplicitExit(true); // when closing window game stops (system exit)
            stage.setOnCloseRequest((ae) -> {
                Platform.exit();
                System.exit(0);
            });
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
        if (seasonProgress == 5) {
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
            Context.farmImplementation.scalePhosphor = Context.farmImplementation.scalePhosphor + 3;
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

    public double calculateProfit() {
        double profit;
        if (isPhophorized) {
            profit = land * phosphorEffect * yieldOfLand;
        } else {
            profit = land * yieldOfLand;
        }
        return profit;
    }


    public void endYearPopulationCalculater() {
        if (cityImplementation.calculateHunger()) { // If hunger: population decrease with 15!
            cityImplementation.population = cityImplementation.population  - 15;
            cityImplementation.isHunger = true;
        }
        if (!cityImplementation.calculateHunger()) { // If there is no hunger the population increments with 10 every week
            Context.cityImplementation.population += 10;
            PrintingUtilities.printOnScreen("Population is growing, city now has " + Context.cityImplementation.population + " inhabitants");
            cityImplementation.isHunger = false;
        }
    }

    public double endYearTaxCalculater() { // pay for food
        return Context.cityImplementation.population + ((100 - scalePhosphor) * 3.0);
    }

    public void endYear() {
        calculatedProfit = calculateProfit();
        lastYearsTax = endYearTaxCalculater();
        lastYearsMoney = playerImplementation.money;
        lastYearsHunger = cityImplementation.isHunger;
        calculateProjects(); // mainly about phosphor consumption and price
        Context.cityImplementation.quizzCount = 0; // reset quiz
        PrintingUtilities.printOnScreen("Ending Year...");
        PrintingUtilities.printOnScreen("\nHarvesting the corn on you fields amounts to: ");
        PrintingUtilities.printOnScreen(String.valueOf(calculateProfit())); // This is profit from fields
        PrintingUtilities.printOnScreen(" gold!");
        Context.playerImplementation.addMoney(calculateProfit()); // paying player
        PrintingUtilities.printOnScreen("\nAfter harvesting you had: " + Context.playerImplementation.money + " gold.");
        PrintingUtilities.printOnScreen("But the people are hungry, and to pay for food you spent: " + endYearTaxCalculater() + " inc taxes...");
        PrintingUtilities.printOnScreen("After delivering food to Capitol City you have: ");
        Context.playerImplementation.useMoney(endYearTaxCalculater()); // pay for food
        PrintingUtilities.printOnScreen(playerImplementation.money + " gold.");
        PrintingUtilities.printOnScreen("\nThe phosphor on your fields has sunken into the ground and does not have any effect on your harvest, you may need to buy more...");
        Context.farmImplementation.isPhophorized = false; // phosphor gone
        checkVictoryConditions(); // have player won?
        checkLoosingConditions(); // or lost?
        endYearPopulationCalculater(); // population increase or decrease?
    }
}
