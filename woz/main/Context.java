package main;

import business.implementations.CityImplementation;
import business.implementations.FarmImplementation;
import business.implementations.RiverImplementation;


public class Context {
    public static FarmImplementation farmImplementation = new FarmImplementation();
    public static CityImplementation cityImplementation = new CityImplementation();
    public static RiverImplementation riverImplementation = new RiverImplementation();

    Space current;
    boolean done = false;
    public boolean getIsInShop() {
        boolean z = cityImplementation.getIsInShop();
        return z;
    }

    Context(Space node) {
        current = node;
    }

    public Space getCurrent() {
        return current;
    }

    public void transition(String direction) {
        Space next = current.followEdge(direction);
        if (next == null) { // changed to print help
            System.out.println("You are confused, and walk in a circle looking for '" + direction + "'. Type 'help' to view list of commands");
        } else {
            current.goodbye();
            current = next;
            current.welcome();
            farmImplementation.dayProgress +=1;
            if (farmImplementation.dayProgress == 4) {
                farmImplementation.endDay();

            } else {
                switch (direction) {
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

