package main;

import business.implementations.CityImplementation;
import business.implementations.FarmImplementation;
import business.implementations.RiverImplementation;


class Context {
  FarmImplementation farmImplementation = new FarmImplementation();
  CityImplementation cityImplementation = new CityImplementation();
  RiverImplementation riverImplementation = new RiverImplementation();

  Space current;
  boolean done = false;
  
  Context (Space node) {
    current = node;
  }
  
  public Space getCurrent() {
    return current;
  }
  
  public void transition (String direction) {
    Space next = current.followEdge(direction);
    if (next==null) { // changed to print help
      System.out.println("You are confused, and walk in a circle looking for '"+direction+"'. Type 'help' to view list of commands");
    }
    else {
      current.goodbye();
      current = next;
      current.welcome();
      switch(direction) {
        case "to_river":
          riverImplementation.visitRiver();
          break;
        case "city_to_river":
          riverImplementation.visitRiver();
          break;
        case "south":
          //System.out.println(getCurrent().edges);
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
          break;
        case "to_uni":
          cityImplementation.visitUni();
          break;
      }
    }
  }


  public void makeDone () {
    done = true;
  }
  
  public boolean isDone () {
    return done;
  }
}

