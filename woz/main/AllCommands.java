package main;

import business.implementations.CityImplementation;
import business.implementations.FarmImplementation;

public class AllCommands extends BaseCommand implements Command {
    FarmImplementation farmImplementation = new FarmImplementation();
    CityImplementation cityImplementation = new CityImplementation();

    AllCommands () {
        description = "Other commands";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        switch (command) {
            case "show_phosphor":
                int scale = farmImplementation.getScale();
                System.out.println("The amount of phosphor is " + scale);
                break;

            default:

        }
    }
}