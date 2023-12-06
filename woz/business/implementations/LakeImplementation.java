package business.implementations;

import business.interfaces.Lake;
import business.utils.PrintingUtilities;
import main.Context;

public class LakeImplementation implements Lake {

    @Override
    public String visitlake() {
        if (Context.farmImplementation.scalePhosphor > 95) {
            return ("\033The birds are singing and as you stroll at the lakeside towards the city,\nyou notice that the fresh water in the lake is so clean that you see a fish standing in the shade under the bridge.\033");
        } else if (Context.farmImplementation.scalePhosphor > 90) {
            return ("\033As you stroll at the lakeside towards the city, you notice that the water is slightly more green than usual..\033");
        } else if (Context.farmImplementation.scalePhosphor > 80) {
            return ("\033As you stroll at the lakeside towards the city, you notice that the water is QUITE more green than usual..\033");
        } else if (Context.farmImplementation.scalePhosphor > 60) {
            return ("\033There is a undefined smell as you stroll at the lakeside towards the city, the water is quite unclear!\033");
        } else if (Context.farmImplementation.scalePhosphor > 40) {
            return ("\033There is a ROTTEN smell as you stroll at the lakeside towards the city, the water is very unclear!!!\033");
        }
        return ("There is a ROTTEN smell as you stroll at the lakeside towards the city, you see DEAD fish floating on the lake!!!!");

    }
}
