package business.implementations;
import business.interfaces.City;

import java.util.Scanner;

public class ShopImplementation {

    public void visitShop() {
        boolean leaveShop = false;
        Scanner shopScanner = new Scanner(System.in);
        System.out.println("Boy in the shop: 'What can I do for you today??'");
        System.out.println("Following items are available for purchase...");
        System.out.println("Seeds, Phosphor and Land");
        System.out.println("For Seeds, write buy_seeds, for Phosphor, write buy_phosphor and for Land, write buy_land, to leave shop write leave_shop!");

        while (!leaveShop) {
            String shopInput = shopScanner.nextLine();
            switch (shopInput) {
                case "buy_seeds":
                    System.out.println("The boy in the shop hands you a bag of Seeds: 'Here you go...'");
                    // Seed array increase by one
                    break;
                case "buy_phosphor":
                    System.out.println("The boy in the shop hands you a bag of Phosphor: 'Here you go...'");
                    // Phosphor array increase by one, Phosphor_Scale decrease by one
                    break;
                case "buy_land":
                    System.out.println("The boy in the shop hands you a scroll of paper with a contract: You now own this piece of land!");
                    //Farm array increase by one field, Fields_for_purchase array decrease by one
                    break;
                case "leave_shop":
                    leaveShop = true; // Set the leaveShop flag to true to exit the shop
                    break;
                default:
                    System.out.println("Boy in shop: 'What will your choice be??'");
            }


        }
        System.out.println("Boy in the shop: 'Bye, come back soon!'");


    }
}
