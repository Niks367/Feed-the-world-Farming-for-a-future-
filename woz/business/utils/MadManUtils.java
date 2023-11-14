package business.utils;

import main.Context;

import java.util.Random;

public class MadManUtils {

    public static String madEvent(){
        Random random = new Random();
        String[] madEvent = new String[]{"Thou seek profit while others suffer from hunger...", "Town hunger brings unrest and you feel someone watching you...", "With hunger comes anger and you can feel a chill down your spine...", "You take a cookie from your pocket and hear some noises not far away..."};
        if (Context.cityImplementation.isHunger){
            int randomIndex = random.nextInt(madEvent.length);
            return madEvent[randomIndex];
        }
        return "Another peaceful day";
    }
}
