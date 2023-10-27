package main;

import business.implementations.CityImplementation;
import business.implementations.FarmImplementation;


class Context {
  FarmImplementation farmImplementation = new FarmImplementation();
  CityImplementation cityImplementation = new CityImplementation();

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
    switch(direction) {
      case "south":
        //System.out.println(getCurrent().edges);
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


    if (next==null) {
      System.out.println("You are confused, and walk in a circle looking for '"+direction+"'. In the end you give up 😩");
    }
    else {
      current.goodbye();
      current = next;
      current.welcome();
    }
  }


  public void makeDone () {
    done = true;
  }
  
  public boolean isDone () {
    return done;
  }
}

