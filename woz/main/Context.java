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
    public void initPlayer(){
        playerImplementation.spawn();
    }
    Context(Space node) {
        current = node;
    }

    public Space getCurrent() {
        return current;
    }

    public void initInterfaces(){
        farmImplementation.fieldInterface(fieldImplementation);
        fieldImplementation.seedInterface(seedImplementation);
        seedImplementation.initPlayerInterface(playerImplementation);
        fieldImplementation.farmInterface(farmImplementation);
    }

    public void transition(String direction) {
        Space next = current.followEdge(direction);
        initInterfaces();
        if (next == null) { // changed to print help
            System.out.println("You are confused, and walk in a circle looking for '" + direction + "'. Type 'help' to view list of commands");
        } else {
            current.goodbye();
            current = next;
            current.welcome();
            farmImplementation.dayProgress +=1;
            if (farmImplementation.dayProgress == 4) {//TODO this function stop some functionality, does not do switch.
                farmImplementation.endDay();
            } else {
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

