package business.implementations;

import business.interfaces.City;

import java.util.Scanner;

public class CityImplementation implements City {
    int hunger = 0;
    boolean isInCity;
    int population = 0;

    private final String[] madArray = new String[]{
            "Go not forth hastily to strive, lest thou know not what to do in the end thereof, when thy neighbour hath put thee to shame.",
            "Hast thou found honey? eat so much as is sufficient for thee, lest thou be filled therewith, and vomit it.",
            "It is not good to eat much honey: so for men to search their own glory is not glory.",
            "As snow in summer, and as rain in harvest, so honour is not seemly for a fool.",
            "Seest thou a man wise in his own conceit? there is more hope of a fool than of him.",
            "Be thou diligent to know the state of thy flocks, and look well to thy herds. For riches are not for ever: and doth the crown endure to every generation?"};


    @Override
    public void setIsInCity(boolean z) {
        isInCity = z;
    }


    @Override
    public boolean getIsInCity() {
        return isInCity;
    }

    @Override
    public int getHunger() {
        return this.hunger;
    }

    @Override
    public int getPopulation() {
        return this.population;
    }


    @Override
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

    @Override
    public void visitUni() {
        boolean leaveUni = false;
        Scanner uniScanner = new Scanner(System.in);
        System.out.println("Dean : 'Good to see you, we have some very promising projects, but we are in lack of sufficient funds'");
        System.out.println("Would you like to support any of our projects??");
        System.out.println("We currently have 2 projects, a superfarm (SF) and a purification plant (PP)");
        System.out.println("To support with 100 gold type SF or PP, if not well....just type leave_uni.");

        while (!leaveUni) {
            try {
                String uniInput = uniScanner.nextLine();
                switch (uniInput) {
                    case "SF":
                        System.out.println("You have helped the project of building a super farm, the project is at {sf_scale}. \nYou can leave by typing leave_uni or support more projects by typing SF or PP");
                        // superfarm_scale increase by one
                        break;
                    case "PP":
                        System.out.println("You have helped the project of building a purification plant, the project is at {pp_scale}.\nYou can leave by typing leave_uni or support more projects by typing SF or PP");
                        // purification_scale is increase by one
                        break;
                    case "leave_uni":
                        leaveUni = true; // Set the leaveUni flag to true to exit the university
                        break;
                    default:
                        System.out.println("Dean: 'We count on you!!, what do you say??'");
                }

            } catch (Exception e) {
                System.out.println("There has been a problem with the input");
                e.printStackTrace();
            } finally {
                //uniScanner.close();
            }

        }
        System.out.println("Dean: 'Hope to see you again soon!'");


    }


    @Override
    public String visitMadman() {
        return (this.madArray[(int) (Math.random() * 6)]);
    }

    @Override
    public void calculateHunger() {

    }
}
