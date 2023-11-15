package business.implementations;

import business.interfaces.Lake;
import business.utils.PrintingUtilities;
import main.Context;

public class LakeImplementation implements Lake {

    @Override
    public void visitlake() {
        if (Context.farmImplementation.scalePhosphor > 90) {
            PrintingUtilities.printOnScreen("\033[3mThe birds are singing and as you stroll at the lakeside towards the city,\nyou notice that the fresh water in the lake is so clean that you see a fish standing in the shade under the bridge.\033[0m");
        } else if (Context.farmImplementation.scalePhosphor > 80) {
            PrintingUtilities.printOnScreen("\033[3mAs you stroll at the lakeside towards the city, you notice that the water is slightly more green than usual..\033[0m");
        } else if (Context.farmImplementation.scalePhosphor > 70) {
            PrintingUtilities.printOnScreen("\033[3mAs you stroll at the lakeside towards the city, you notice that the water is QUITE more green than usual..\033[0m");
        } else if (Context.farmImplementation.scalePhosphor > 60) {
            PrintingUtilities.printOnScreen("\033[3mThere is a undefined smell as you stroll at the lakeside towards the city, the water is quite unclear!\033[0m");
        } else if (Context.farmImplementation.scalePhosphor > 40) {
            PrintingUtilities.printOnScreen("\033[3mThere is a ROTTEN smell as you stroll at the lakeside towards the city, the water is very unclear!!!\033[0m");
        } else if (Context.farmImplementation.scalePhosphor > 20) {
            PrintingUtilities.printOnScreen("There is a ROTTEN smell as you stroll at the lakeside towards the city, you see DEAD fish floating on the lake!!!!");
        }

    }
}
