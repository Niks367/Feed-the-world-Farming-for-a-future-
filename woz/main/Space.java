package main;

import java.util.Set;

class Space extends Node {
    Space(String name) {
        super(name);
    }

    public void welcome() {

        System.out.println("You are now walking to " + name);


        for (int i = 5; i > 0; i--) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(".");
        }
        System.out.println("\nYou are now at " + name);

        Set<String> exits = edges.keySet();
        System.out.println("Current exits are:");
        for (String exit : exits) {
            System.out.println(" - " + exit);
        }
    }


    public void goodbye() {
        System.out.println("You are leaving " + name);
    }

    @Override
    public Space followEdge(String direction) {
        return (Space) (super.followEdge(direction));
    }
}
