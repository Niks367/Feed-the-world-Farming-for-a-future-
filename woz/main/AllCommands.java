package main;

public class AllCommands extends BaseCommand implements Command {


    AllCommands() {
        description = "Other commands";
    }

    public void printOnScreen(String text) {
        System.out.println(text);
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        context.initInterfaces();
        switch (command) {
            case "day_count":
                Context.farmImplementation.dayCount(command);
                break;
            case "show_phosphor":
                description = "show the current phosphor reserves";
                printOnScreen("The amount of phosphor you have is " + Context.farmImplementation.phosphor + " and the phosphor there is in the world is " + Context.farmImplementation.scalePhosphor);
                break;
            case "end_day":
                Context.farmImplementation.endDay();
                break;
            case "buy_seeds":
                if (Context.cityImplementation.getIsInShop()) {
                    System.out.println("The boy in the shop hands you a bag of Seeds: 'Here you go...'");
                    //Seed array increase by one
                    break;
                } else {
                    System.out.println("You have to be in the shop to buy seeds");
                    break;
                }
            case "buy_phosphor":
                if (Context.cityImplementation.getIsInShop()) {
                    System.out.println("The boy in the shop hands you a bag of Phosphor: 'Here you go...'");
                    // Phosphor array increase by one, Phosphor_Scale decrease by one
                    break;
                } else {
                    System.out.println("You have to be in the shop to buy Phosphor");
                    break;
                }
            case "buy_land":
                if (Context.cityImplementation.getIsInShop()) {
                    System.out.println("The boy in the shop hands you a scroll of paper with a contract: You now own this piece of land!");
                    //Farm array increase by one field, Fields_for_purchase array decrease by one
                    break;
                } else {
                    System.out.println("You have to be in the shop to buy land");
                    break;
                }
            case "SF":
                if (Context.cityImplementation.getIsInUni()) {
                    System.out.println("You have helped the project of building a super farm, the project is at {sf_scale}. \nYou can leave by typing go east or support more projects by typing SF or PP");
                    // superfarm_scale increase by one
                    break;
                } else {
                    System.out.println("You have to be in the university to support our projects");
                    break;
                }
            case "PP":
                if (Context.cityImplementation.getIsInUni()) {
                    System.out.println("You have helped the project of building a purification plant, the project is at {pp_scale}.\nYou can leave by typing go east or support more projects by typing SF or PP");
                    // purification_scale is increase by one
                    break;
                } else {
                    System.out.println("You have to be in the university to support our projects");
                    break;
                }
            case "check_seed":
                if (Context.farmImplementation.isFarm) {
                    printOnScreen(String.valueOf(Context.seedImplementation.seedAmount));
                } else {
                    printOnScreen("You need to be in farm to check the seeds");
                }
                break;

            default:
//                if (Context.cityImplementation.getIsInShop()) {
//                    System.out.println("Boy in shop: 'What will your choice be??'");
//                }
        }
    }
}

