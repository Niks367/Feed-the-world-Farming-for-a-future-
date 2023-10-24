package main;

import business.implementations.CityImplementation;
import business.implementations.FarmImplementation;

public class AllCommands extends BaseCommand implements Command {
    FarmImplementation farmImplementation = new FarmImplementation();
    CityImplementation cityImplementation = new CityImplementation();

    @Override
    public void execute(Context context, String command, String[] parameters) {
        switch (command) {
            case "get_phosphor":
                int scale = farmImplementation.getScale();
                System.out.println("The amount of phosphor is " + scale);
                break;
            case "visit_city":
                cityImplementation.setIsInCity(true);
                System.out.println("You are in the city now, hunger: " + cityImplementation.getHunger() + " population: " + cityImplementation.getPopulation());
            default:

        }
    }
}