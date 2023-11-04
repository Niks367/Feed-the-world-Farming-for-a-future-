package main;

import business.implementations.*;


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

    public void initPlayer() {
        playerImplementation.spawn();
    }

    Context(Space node) {
        current = node;
    }

    public Space getCurrent() {
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
            case "days":
                farmImplementation.dayCount(command);
                break;
            case "phosphor":
                System.out.println("The amount of phosphor you have is " + farmImplementation.phosphor + " and the phosphor there is in the world is " + Context.farmImplementation.scalePhosphor);
                break;
            case "seeds":
                if (farmImplementation.isFarm) {
                    System.out.println("There is " + String.valueOf(seedImplementation.seedAmount + " seed your inventory."));
                } else {
                    System.out.println("You need to be in farm to check the seeds");
                }
                break;
            case "hunger":
                if (cityImplementation.isHunger) {
                    System.out.println("The people in the city are starving! Hurry up and give them something to eat.");
                } else {
                    System.out.println("The people in the city are happy and not hungry.");
                }
                break;
            case "population":
                System.out.println(cityImplementation.getPopulation());
                break;
            case "money":
                System.out.println("Your current cash situation is: "+playerImplementation.money+" money");

        }
    }

    public void support(String command) {
        //context.initInterfaces();
        switch (command) {
            case "PP", "pp":
                if (cityImplementation.getIsInUni()) {
                    if (playerImplementation.money > 100) {
                        System.out.println("You have helped the project of building a purification plant, the project is at {pp_scale}.\nYou can leave by typing go east or support more projects by typing SF or PP");
                        playerImplementation.useMoney(100);
                        break;
                        // TODO purification_scale is increased by one
                    } else {
                        System.out.println("Unfortunately, you don't seem to have the required amount of cash!");
                        break;
                    }

                } else {
                    System.out.println("You have to be in the university to support our projects");
                    break;
                }
            case "SF", "sf":
                if (cityImplementation.getIsInUni()) {
                    if (playerImplementation.money > 100) {
                        System.out.println("You have helped the project of building a super farm, the project is at {sf_scale}.\nYou can leave by typing go east or support more projects by typing SF or PP");
                        playerImplementation.useMoney(100);
                        break;
                        // TODO superfarm_scale is increased by one
                    } else {
                        System.out.println("Unfortunately, you don't seem to have the required amount of cash!");
                        break;
                    }
                } else {
                    System.out.println("You have to be in the university to support our projects");
                    break;
                }
        }
    }

    public void buy(String command) {
        //context.initInterfaces();
        switch (command) {
            case "land":
                if (cityImplementation.getIsInShop()) {
                    System.out.println("The boy in the shop hands you a scroll of paper with a contract: You now own this piece of land!");
                    //TODO Farm array increase by one field, Fields_for_purchase array decrease by one
                    break;
                } else {
                    System.out.println("You have to be in the shop to buy land");
                    break;
                }
            case "seeds":
                if (cityImplementation.getIsInShop()) {
                    System.out.println("The boy in the shop hands you a bag of Seeds: 'Here you go...'");

                    //Seed increase by one
                    break;
                } else {
                    System.out.println("You have to be in the shop to buy seeds");
                    break;
                }
            case "phosphor":
                if (cityImplementation.getIsInShop()) {
                    System.out.println("The boy in the shop hands you a bag of Phosphor: 'Here you go...'");
                    farmImplementation.phosphor += 1;
                    farmImplementation.scalePhosphor -= 1;
                    // Phosphor increase by one, Phosphor_Scale decrease by one
                    break;
                } else {
                    System.out.println("You have to be in the shop to buy Phosphor");
                    break;
                }
        }
    }

    void checkEndday() {
        System.out.println("It's currently " + farmImplementation.dayProgress + " o'clock");
        if (farmImplementation.dayProgress == 4) {//TODO ADDED by Lars: Check it it works by using this function
            //TODO earlier is stop some functionality, does not do switch if day ends.
            farmImplementation.endDay();
        }
    }

    public void transition(String direction) {
        Space next = current.followEdge(direction);
        initInterfaces();
        checkEndday();
        if (next == null) { // changed to print help
            System.out.println("You are confused, and walk in a circle looking for '" + direction + "'. Type 'help' to view list of commands");
        } else {
            current.goodbye();
            current = next;
            current.welcome();
            farmImplementation.dayProgress += 1;

            {
                switch (direction) {
                    case "to_farm", "river_to_farm", "fields_to_farm":
                        farmImplementation.isFarm = true;
                        break;
                    case "to_river", "city_to_river":
                        riverImplementation.visitRiver();
                        break;
                    case "to_city":
                        cityImplementation.setIsInCity(true);
                        System.out.println("hunger: " + cityImplementation.getHunger() + " population: " + cityImplementation.getPopulation());
                        break;
                    case "to_madman":
                        System.out.println("BOOOOH!!!");
                        System.out.println("The madman is the hut: '" + cityImplementation.visitMadman() + "'");
                        break;
                    case "to_shop":
                        cityImplementation.setIsInShop(true);
                        cityImplementation.visitShop();
                        //System.out.println("printer her fra context, getIsInShop is here: "+cityImplementation.getIsInShop());
                        break;
                    case "west":
                        cityImplementation.setIsInShop(false);
                        break;
                    case "to_uni":
                        cityImplementation.visitUni();
                        //System.out.println("printer her fra context, getIsInShop is here: "+cityImplementation.getIsInShop());
                        break;
                    case "east":
                        cityImplementation.setIsInUni(false);
                        break;
//        case "to_uni":
//          cityImplementation.visitUni();
//          break;
                }
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

