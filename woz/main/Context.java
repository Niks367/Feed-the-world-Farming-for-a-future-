package main;

import business.implementations.*;

import java.util.Set;


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
            case "exits":
                System.out.println(Game.context.current.edges.keySet());
                // TODO make this nicer, without brackets

            case "days":
                farmImplementation.dayCount(command);
                break;
            case "land":
                System.out.println("You currently own " + farmImplementation.land + " fields.");
                break;
            case "phosphor":
                System.out.println("The phosphor reserves in the world is " + Context.farmImplementation.scalePhosphor);
                break;
            case "seeds":
                if (farmImplementation.getIsInFarm()) {
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
                System.out.println("Your current cash situation is: " + playerImplementation.money + " money");

        }
    }

    public void support(String command) {
        //context.initInterfaces();
        switch (command) {
            case "PP", "pp":
                if (cityImplementation.getIsInUni()) {
                    if (playerImplementation.money > 100) {
                        System.out.println("You have helped the project of building a purification plant, the project is at {pp_scale}." +
                                "\nYou can leave by typing go east or support more projects by typing SF or PP");
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
                        System.out.println("You have helped the project of building a super farm, the project is at {sf_scale}." +
                                "\nYou can leave by typing go east or support more projects by typing SF or PP");
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
                    if (!farmImplementation.getIsPhosphorized()){
                        if (playerImplementation.money > farmImplementation.getPriceOfLand() && farmImplementation.land <= farmImplementation.getFieldsForPurchase()) {
                            System.out.println("The boy in the shop hands you a scroll of paper with a contract: You now own this piece of land!");
                            farmImplementation.land += 1;
                            playerImplementation.useMoney(100);
                            farmImplementation.reduceFieldsForPurchase();
                            break;
                        } else {
                            System.out.println("Unfortunately, you don't seem to have the required amount of cash or there is not any more land to purchase!");
                            break;
                        }
                    }else {
                        if (playerImplementation.money > farmImplementation.getPriceOfLand()+farmImplementation.phosphorPrice) {
                            System.out.println("Since you recently have phosphorized your fields you will be charged " +
                                    (farmImplementation.getPriceOfLand()+farmImplementation.phosphorPrice) + " money!");
                            System.out.println("The boy in the shop hands you a scroll of paper with a contract: You now own this piece of land!");
                            farmImplementation.land += 1;
                            playerImplementation.useMoney(100);
                            farmImplementation.reduceFieldsForPurchase();
                            break;
                        } else {
                            System.out.println("Unfortunately, you don't seem to have the required amount of cash!");
                            break;
                        }
                    }


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
            case "freemoney":
                playerImplementation.addMoney(100);
                System.out.println("ka-chiiiing!!!");
                break; //TODO Remove this cheatcode at some point
            case "phosphor":
                if (cityImplementation.getIsInShop()) {
                    if ((playerImplementation.money > farmImplementation.land * farmImplementation.phosphorPrice)&& !farmImplementation.getIsPhosphorized()) {
                        System.out.printf("You currently own %d pieces of land, so you will be charged %d money to buy phosphor.",
                                farmImplementation.land, farmImplementation.land * farmImplementation.phosphorPrice);
                        playerImplementation.useMoney(farmImplementation.land * farmImplementation.phosphorPrice);
                        System.out.println("The boy in the shop hands you a bag of Phosphor: 'Here you go...'");
                        farmImplementation.phosphor += 1;
                        farmImplementation.scalePhosphor -= 1;
                        farmImplementation.setIsPhosphorized(true);
                        // Phosphor increase by one, Phosphor_Scale decrease by one
                        break;
                    } else {
                        System.out.println("Unfortunately, you don't seem to have the required amount of cash or you have already phosphorized your fields this week!");
                        break;
                    }
                } else {
                    System.out.println("You have to be in the shop to buy Phosphor");
                    break;
                }
        }
    }

    void checkEndDay() {
        switch((int)farmImplementation.dayProgress) {
            case 0:
                System.out.println("It's morning.");
                break;
            case 1:
                System.out.println("It's noon.");
                break;
//            case 2:
//                System.out.println("it's afternoon.");
//                break;
//            case 3:
//                System.out.println("it's evening.");
//                farmImplementation.endDay();
//                break;

        }
    }

    public void transition(String direction) {
        Space next = current.followEdge(direction);
        initInterfaces();
        farmImplementation.checkDayprogress();
        checkEndDay();
        if (next == null) { // changed to print help
            System.out.println("You are confused, and walk in a circle looking for '" + direction +
                    "'. Type 'help' to view list of commands");
        } else {
            current.goodbye();
            current = next;
            current.welcome();
            farmImplementation.dayProgress += 1;
                switch (direction) {
                    case "to_farm", "river_to_farm", "fields_to_farm":
                        farmImplementation.setIsInFarm(true);
                        break;
                    case "to_river", "city_to_river":
                        riverImplementation.visitRiver();
                        break;
                    case "to_city":
                        cityImplementation.setIsInCity(true);
                        System.out.println("You are entering the Capital City...");
                        System.out.println(" population is : " + cityImplementation.getPopulation());
                        if (cityImplementation.isHunger) {
                            System.out.println("The people in the city are starving! Hurry up and give them something to eat.");
                        }
                        else {
                            System.out.println("The people in the city are happy and not hungry.");
                        }
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

    public void makeDone() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }
}

