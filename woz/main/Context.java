package main;

import business.implementations.CityImplementation;
import business.implementations.FarmImplementation;

import java.util.Scanner;


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
      case "city":
        cityImplementation.setIsInCity();
        System.out.println("hunger: " + cityImplementation.getHunger() + " population: " + cityImplementation.getPopulation());
        break;
      case "madman":
        System.out.println("BOOOOH!!!");
        System.out.println("The madman is the hut: '" + cityImplementation.visitMadman() + "'");
        break;
      case "shop":
        cityImplementation.visitShop();
      case "uni":
        cityImplementation.visitUni();

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

