package business.implementations;

import business.interfaces.City;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CityImplementation implements City {
    int hunger = 0;
    private boolean isInCity;
    private boolean isInUni;
    private boolean isInShop = false;
    int population = 100;
    public boolean isHunger = false;


    /* allProjectsList is a list of all projects that can be available during different phases of the game:
    It is hardcoded.
    For now the Dean uses this list.*/
    private ArrayList<String> allProjectsList = new ArrayList(Arrays.asList("SuperFarm (SF)", "PurificationPlant (PP)"));

    private void printAllProjectsList() {
        System.out.println(allProjectsList);
    }

    // availableProjectsList is  a list of the projects that player can currently support:
    private ArrayList<String> availableProjectsList;

    // availableProjects is a method that will calculate and update the list 'availableProjects at end of day:
    private void availableProjects(ArrayList<String> projects) {
        /*TODO logic that takes time, already build projects and possibly other factors into considerations and returns a list of available projects.
        For now it returns the complete list.
         */
        ArrayList<String> result = new ArrayList<>();
        result = projects;
        availableProjectsList = result;
    }
    // this method calculates the number of available projects and will be used by Dean at the University
    // in his intro.
    public int calculateAvailableProjects() {
        return allProjectsList.size(); //TODO to be changed to availableProjectsList!
        //return availableProjectsList.size();
    }
    public void printOnScreen(String text) {
        System.out.println(text);
    }

    public ArrayList<String> returnCurrentProjects(ArrayList<Integer> index) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < calculateAvailableProjects(); i++) {
            result.add(availableProjectsList.get(index.get(i)));
        }
        return result;
    }

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
    public void setIsInShop(boolean z) {
        isInShop = z;
    }

    @Override
    public boolean getIsInShop() {
        return isInShop;
    }

    @Override
    public void setIsInUni(boolean z) {
        isInShop = z;
    }

    @Override
    public boolean getIsInUni() {
        return isInShop;
    }


    @Override
    public void visitShop() {
        setIsInShop(true);
        //System.out.println("isInShop is now: '" + isInShop + "' printed from visitShop() in cityImplementation after setting it true here.");
        System.out.println("Boy in the shop: 'What can I do for you today??'");
        System.out.println("Following items are available for purchase...");
        System.out.println("Seeds, Phosphor and Land.");
        System.out.println("For Seeds, write buy_seeds, for Phosphor, write buy_phosphor and for Land, write buy_land, to leave shop write leave_shop!");
    }

    @Override
    public void visitUni() {
        setIsInUni(true);
        System.out.println("Dean : 'Good to see you, we have some very promising projects, but we are in lack of sufficient funds'");
        System.out.println("Would you like to support any of our projects??");
        System.out.printf("We currently have %d projects)", calculateAvailableProjects());
        printAllProjectsList();
        System.out.println("To support with 100 gold type SF or PP, if not well....just type 'go east' and you will be back in the city in a jiffy.");
    }
//
//        while (!leaveUni) {
//            try {
//                String uniInput = uniScanner.nextLine();
//                switch (uniInput) {
//                    case "SF":
//                        System.out.println("You have helped the project of building a super farm, the project is at {sf_scale}. \nYou can leave by typing leave_uni or support more projects by typing SF or PP");
//                        // superfarm_scale increase by one
//                        break;
//                    case "PP":
//                        System.out.println("You have helped the project of building a purification plant, the project is at {pp_scale}.\nYou can leave by typing leave_uni or support more projects by typing SF or PP");
//                        // purification_scale is increase by one
//                        break;
//                    case "leave_uni":
//                        leaveUni = true; // Set the leaveUni flag to true to exit the university
//                        break;
//                    default:
//                        System.out.println("Dean: 'We count on you!!, what do you say??'");
//                }
//
//            } catch (Exception e) {
//                System.out.println("There has been a problem with the input");
//                e.printStackTrace();
//            } finally
//            {
//                //uniScanner.close();
//            }
//
//        }
//        System.out.println("Dean: 'Hope to see you again soon!'");
//    }


    @Override
    public String visitMadman() {
        return (this.madArray[(int) (Math.random() * 6)]);
    }

    @Override
    public void calculateHunger() {

    }
}

