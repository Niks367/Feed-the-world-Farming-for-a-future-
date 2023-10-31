package business.implementations;

import business.interfaces.Farm;

import java.util.Scanner;

public class FarmImplementation implements Farm {
    int scalePhosphor = 1000;

    @Override
    public int getScale() {
        return this.scalePhosphor;
    }
    // TODO make checker for scalePhosphor for events
    int dayCount = 0; //Integer that tells us the day we are currently at.
    // TODO When dayCount hits certain values: Events
    int phosphor = 1; //Integer that tells us the amount of phosphor we have.
    private boolean endDay = false; // We initialize the boolean for endDay, so it is defined in our code and we can use it later to end the day.

    //TODO do some changes to this later, so it works with the day count.
    @Override
    public void runDay(String input) {
        // This will be displayed when the game is launched and display this message, so people playing will know how to end the day.
        if ("end_day".equalsIgnoreCase(input)) {
            endDay = true;
            endDay(); //Space for the method to define how we end the day, i havent filled it out yet cuz im not sure how to do it.
            dayCount += 1; //Adds days to the dayCount integer, so everytime we end the day, the dayCount integer goes up by one.
        } else {
            System.out.println("Type 'endDay' to end the day:");
        }


    }

    @Override
    public void endDay() {
        //This is where we will type the code to end the day, but im not quite sure how we are gonna do that yet. We need the day counter and the time im pretty sure, before we can write the code for this.
        System.out.println("Ending the day...");
        //TODO All the events that happen after the day need to be implemented, eks: seed growth or events, university research projects.

        // This is so that the endDay command doesnt just contiously end the day, but we actually reset it, so we can type it again the next day.
        endDay = false;
    }
}

