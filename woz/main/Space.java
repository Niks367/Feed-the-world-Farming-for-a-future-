package main;

import business.utils.PrintingUtilities;

import java.util.Set;

class Space extends Node {
    Space(String name) {
        super(name);
    }

    public void welcome() {
        PrintingUtilities.printOnScreen("You are now walking to " + name);
        for (int i = 5; i > 0; i--) {
            // Thread.sleep(200);
            System.out.print(".");
        }
        PrintingUtilities.printOnScreen("\nYou are now at " + name);

        Set<String> exits = edges.keySet();
        PrintingUtilities.printOnScreen("Current exits are:");
        for (String exit : exits) {
            PrintingUtilities.printOnScreen(" - " + exit);
        }
    }


    public void goodbye() {
        PrintingUtilities.printOnScreen("You are leaving " + name);
    }

    @Override
    public Space followEdge(String direction) {
        return (Space) (super.followEdge(direction));
    }
}
