package main;

import business.implementations.*;
import business.utils.PrintingUtilities;


public class Context {
    public static FarmImplementation farmImplementation = new FarmImplementation();
    public static CityImplementation cityImplementation = new CityImplementation();
    public static RiverImplementation riverImplementation = new RiverImplementation();
    public static FieldImplementation fieldImplementation = new FieldImplementation();
    public static PlayerImplementation playerImplementation = new PlayerImplementation();
    public static SeedImplementation seedImplementation = new SeedImplementation();
    public static TimeImplementation timeImplementation = new TimeImplementation();

    Space current;
    boolean done = false;

    public boolean getIsInShop() {
        return cityImplementation.getIsInShop();
    }

    public boolean getIsInFarm() {
        return farmImplementation.getIsInFarm();
    }

    public void initPlayer() {
        playerImplementation.spawn();
    }

    Context(Space node) {
        current = node;
    }

    Space getCurrent() {
        return current;
    }

    public void initInterfaces() {
        farmImplementation.fieldInterface(fieldImplementation);
        fieldImplementation.seedInterface(seedImplementation);
        seedImplementation.initPlayerInterface(playerImplementation);
        fieldImplementation.farmInterface(farmImplementation);
    }

    public void end(String command) {
        switch (command) {
            case "day":
                farmImplementation.endDay();
                break;
        }
    }


    public void show(String command) {
        switch (command) {
            case "days" -> farmImplementation.dayCount(command);
            case "phosphor" ->
                    PrintingUtilities.printOnScreen("The amount of phosphor you have is " + farmImplementation.phosphor +
                            " and the phosphor there is in the world is " + Context.farmImplementation.scalePhosphor);
            case "seeds" -> {
                if (farmImplementation.getIsInFarm()) {
                    PrintingUtilities.printOnScreen("There is " + seedImplementation.seedAmount + " seed your inventory.");
                } else {
                    PrintingUtilities.printOnScreen("You need to be in farm to check the seeds");
                }
            }
            case "hunger" -> {
                if (cityImplementation.isHunger) {
                    PrintingUtilities.printOnScreen("The people in the city are starving! Hurry up and give them something to eat.");
                } else {
                    PrintingUtilities.printOnScreen("The people in the city are happy and not hungry.");
                }
            }
            case "population" -> PrintingUtilities.printOnScreen(String.valueOf(cityImplementation.getPopulation()));
            case "money" ->
                    PrintingUtilities.printOnScreen("Your current cash situation is: " + playerImplementation.money + " money");
        }
    }

    public void support(String command) {
        //context.initInterfaces();
        switch (command) {
            case "PP", "pp" -> {
                if (cityImplementation.getIsInUni()) {
                    if (playerImplementation.money > 100) {
                        PrintingUtilities.printOnScreen("You have helped the project of building a purification plant, the project is at {pp_scale}." +
                                "\nYou can leave by typing go east or support more projects by typing SF or PP");
                        playerImplementation.useMoney(100);
                        // TODO purification_scale is increased by one
                    } else {
                        PrintingUtilities.printOnScreen("Unfortunately, you don't seem to have the required amount of cash!");
                    }

                } else {
                    PrintingUtilities.printOnScreen("You have to be in the university to support our projects");
                }
            }
            case "SF", "sf" -> {
                if (cityImplementation.getIsInUni()) {
                    if (playerImplementation.money > 100) {
                        PrintingUtilities.printOnScreen("You have helped the project of building a super farm, the project is at {sf_scale}." +
                                "\nYou can leave by typing go east or support more projects by typing SF or PP");
                        playerImplementation.useMoney(100);
                        // TODO superfarm_scale is increased by one
                    } else {
                        PrintingUtilities.printOnScreen("Unfortunately, you don't seem to have the required amount of cash!");
                    }
                } else {
                    PrintingUtilities.printOnScreen("You have to be in the university to support our projects");
                }
            }
        }
    }

    public void buy(String command) {
        //context.initInterfaces();
        switch (command) {
            case "land" -> {
                if (cityImplementation.getIsInShop()) {
                    PrintingUtilities.printOnScreen("The boy in the shop hands you a scroll of paper with a contract: You now own this piece of land!");
                    //TODO Farm array increase by one field, Fields_for_purchase array decrease by one
                    if (!farmImplementation.getIsPhosphorized()) {
                        if (playerImplementation.money > farmImplementation.getPriceOfLand() && farmImplementation.land <= farmImplementation.getFieldsForPurchase()) {
                            PrintingUtilities.printOnScreen("The boy in the shop hands you a scroll of paper with a contract: You now own this piece of land!");
                            farmImplementation.land += 1;
                            playerImplementation.useMoney(100);
                            farmImplementation.reduceFieldsForPurchase();
                        } else {
                            PrintingUtilities.printOnScreen("Unfortunately, you don't seem to have the required amount of cash or there is not any more land to purchase!");
                        }
                    } else {
                        if (playerImplementation.money > farmImplementation.getPriceOfLand() + farmImplementation.phosphorPrice) {
                            PrintingUtilities.printOnScreen("Since you recently have phosphorized your fields you will be charged " +
                                    (farmImplementation.getPriceOfLand() + farmImplementation.phosphorPrice) + " money!");
                            PrintingUtilities.printOnScreen("The boy in the shop hands you a scroll of paper with a contract: You now own this piece of land!");
                            farmImplementation.land += 1;
                            playerImplementation.useMoney(100);
                            farmImplementation.reduceFieldsForPurchase();
                        } else {
                            PrintingUtilities.printOnScreen("Unfortunately, you don't seem to have the required amount of cash!");
                        }
                    }


                } else {
                    PrintingUtilities.printOnScreen("You have to be in the shop to buy land");
                }
            }
            case "seeds" -> {
                if (cityImplementation.getIsInShop()) {
                    PrintingUtilities.printOnScreen("The boy in the shop hands you a bag of Seeds: 'Here you go...'");
                    //Seed increase by one
                } else {
                    PrintingUtilities.printOnScreen("You have to be in the shop to buy seeds");
                }
            }
            case "freemoney" -> {
                playerImplementation.addMoney(100);
                PrintingUtilities.printOnScreen("ka-chiiiing!!!");//TODO Remove this cheatcode at some point
            }
            case "phosphor" -> {
                if (cityImplementation.getIsInShop()) {
                    PrintingUtilities.printOnScreen("The boy in the shop hands you a bag of Phosphor: 'Here you go...'");
                    // Phosphor increase by one, Phosphor_Scale decrease by one
                    if ((playerImplementation.money > farmImplementation.land * farmImplementation.phosphorPrice) && !farmImplementation.getIsPhosphorized()) {
                        System.out.printf("You currently own %d pieces of land, so you will be charged %d money to buy phosphor.",
                                farmImplementation.land, farmImplementation.land * farmImplementation.phosphorPrice);
                        playerImplementation.useMoney(farmImplementation.land * farmImplementation.phosphorPrice);
                        PrintingUtilities.printOnScreen("The boy in the shop hands you a bag of Phosphor: 'Here you go...'");
                        farmImplementation.phosphor += 1;
                        farmImplementation.scalePhosphor -= 1;
                        farmImplementation.setIsPhosphorized(true);
                        // Phosphor increase by one, Phosphor_Scale decrease by one
                    } else {
                        PrintingUtilities.printOnScreen("Unfortunately, you don't seem to have the required amount of cash or you have already phosphorized your fields this week!");
                    }
                } else {
                    PrintingUtilities.printOnScreen("You have to be in the shop to buy Phosphor");
                }
            }

        }
    }

    //    void checkEndDay() {
//        PrintingUtilities.printOnScreen("It's currently " + farmImplementation.dayProgress + " o'clock");
//        if (farmImplementation.dayProgress == 4) {//TODO ADDED by Lars: Check it it works by using this function
//            //TODO earlier is stop some functionality, does not do switch if day ends.
//            farmImplementation.endDay();
//        }
//    }
    void checkEndDay() {
        switch (farmImplementation.dayProgress) {
            case 0 -> PrintingUtilities.printOnScreen("It's morning.");
            case 1 -> PrintingUtilities.printOnScreen("It's noon.");

//            case 2:
//                PrintingUtilities.printOnScreen("it's afternoon.");
//                break;
//            case 3:
//                PrintingUtilities.printOnScreen("it's evening.");
//                farmImplementation.endDay();
//                break;

        }
    }
    private void dayChecking(){
        checkEndDay();
        farmImplementation.checkDayProgress();
    }
    public void transition(String direction) {
        Space next = current.followEdge(direction);
        initInterfaces();
        if (next == null) { // changed to print help
            PrintingUtilities.printOnScreen("You are confused, and walk in a circle looking for '" + direction +
                    "'. Type 'help' to view list of commands");
        } else {
            try {
                current.goodbye();
                current = next;
                current.welcome();
                farmImplementation.dayProgress += 1;
                switch (direction) {
                    case "to_farm", "river_to_farm", "fields_to_farm" -> {
                        farmImplementation.setIsInFarm(true);
                        dayChecking();
                    }
                    case "to_river", "city_to_river" -> {
                        dayChecking();
                        riverImplementation.visitRiver();
                    }
                    case "to_city" -> {
                        dayChecking();
                        cityImplementation.setIsInCity(true);
                        PrintingUtilities.printOnScreen("You are entering the Capital City...");
                        PrintingUtilities.printOnScreen(" population is : " + cityImplementation.getPopulation());
                        if (cityImplementation.isHunger) {
                            PrintingUtilities.printOnScreen("The people in the city are starving! Hurry up and give them something to eat.");
                        } else {
                            PrintingUtilities.printOnScreen("The people in the city are happy and not hungry.");
                        }
                    }
                    case "to_madman" -> {
                        dayChecking();
                        PrintingUtilities.printOnScreen("BOOOOH!!!");
                        PrintingUtilities.printOnScreen("The madman is the hut: '" + cityImplementation.visitMadman() + "'");
                    }
                    case "to_shop" -> {
                        dayChecking();
                        cityImplementation.setIsInShop(true);
                        cityImplementation.visitShop();
                    }
                    //PrintingUtilities.printOnScreen("printer her fra context, getIsInShop is here: "+cityImplementation.getIsInShop());
                    case "west" -> {
                        dayChecking();
                        cityImplementation.setIsInShop(false);
                    }
                    case "to_uni" -> {
                        dayChecking();
                        cityImplementation.visitUni();
                    }

                    //PrintingUtilities.printOnScreen("printer her fra context, getIsInShop is here: "+cityImplementation.getIsInShop());
                    case "east" -> {
                        dayChecking();
                        cityImplementation.setIsInUni(false);
                    }
                    default -> {
                        PrintingUtilities.printOnScreen("Cannot find the location entered");
                    }

//        case "to_uni":
//          cityImplementation.visitUni();
//          break;
                }
            } catch (Exception e) {
                PrintingUtilities.printOnScreen("Bad input");
                e.printStackTrace();

            }
        }
    }

    public void makeDone() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }
}

