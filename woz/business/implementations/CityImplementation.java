package business.implementations;

import business.interfaces.City;
import business.utils.PrintingUtilities;
import main.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CityImplementation implements City {
    int hunger = 0;
    private boolean isInCity;
    private boolean isInUni;
    private boolean isInShop = false;
    int population = 100;
    public int sf_Progress = 0;
    public int pp_Progress = 0;
    public boolean isPpDone = false; //Boolean for PP
    public boolean isSfDone = false; //Boolean for Sf
    boolean isSuperPpDone = false; //Boolean for Super PP
    boolean isBfDone = false; //Boolean for Bio Farm
    public boolean isHunger = false;
    Scanner scanner = new Scanner(System.in);
    /* allProjectsList is a list of all projects that can be available during different phases of the game:
    It is hardcoded.
    For now the Dean uses this list.*/
    private final ArrayList<String> allProjectsList = new ArrayList(Arrays.asList("SuperFarm (SF)", "PurificationPlant (PP)","BioFarm (BF)","Super Purification Plant(SuperPP)"));

    private void printAllProjectsList() {
        PrintingUtilities.printOnScreen(String.valueOf(allProjectsList));
    }

    public ArrayList<String> availableProjectsList = new ArrayList(Arrays.asList("SuperFarm(SF)","PurificationPlant (PP)","BioFarm (BF)","Super Purification Plant(SuperPP)"));
    // availableProjectsList is  a list of the projects that player can currently support:
//    private ArrayList<String> availableProjectsList;
//
//    // availableProjects is a method that will calculate and update the list 'availableProjects at end of day:
//    private void availableProjects(ArrayList<String> projects) {
//        /*TODO logic that takes time, already build projects and possibly other factors into considerations and returns a list of available projects.
//        For now it returns the complete list.
//         */
//        availableProjectsList = projects;
//    }

    // this method calculates the number of available projects and will be used by Dean at the University
    // in his intro.
    public int calculateAvailableProjects() {
        return allProjectsList.size(); //TODO to be changed to availableProjectsList!
        //return availableProjectsList.size();
    }

    public void printOnScreen(String text) {
        PrintingUtilities.printOnScreen(text);
    }
//
//    public ArrayList<String> returnCurrentProjects(ArrayList<Integer> index) {
//        ArrayList<String> result = new ArrayList<>();
//        for (int i = 0; i < calculateAvailableProjects(); i++) {
//            result.add(availableProjectsList.get(index.get(i)));
//        }
//        return result;
//    }

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
        isInUni = z;
    }

    @Override
    public boolean getIsInUni() {
        return isInUni;
    }

    @Override
    public void visitShop() {
        setIsInShop(true);
        //PrintingUtilities.printOnScreen("isInShop is now: '" + isInShop + "' printed from visitShop() in cityImplementation after setting it true here.");
        PrintingUtilities.printOnScreen("Boy in the shop: 'What can I do for you today??'");
        PrintingUtilities.printOnScreen("Following items are available for purchase...");
        PrintingUtilities.printOnScreen("Phosphor and Land.");
        PrintingUtilities.printOnScreen("For Phosphor, just write buy phosphor and for Land, write buy land. You hit downtown by going west.");
    }

    @Override
    public void visitUni() {
        setIsInUni(true);
        PrintingUtilities.printOnScreen("Dean : 'Good to see you, we have some very promising projects, but we are in lack of sufficient funds'");
        PrintingUtilities.printOnScreen("Would you like to support any of our projects??");
        System.out.printf("We currently have %d projects)", calculateAvailableProjects());
        printAllProjectsList();
        scanner.nextLine();
        PrintingUtilities.printOnScreen("To support with 100 gold type support plus SF or PP, if not well....just type 'go east' and you will be back in the city in a jiffy.");
        PrintingUtilities.printOnScreen("Would you like to answer some questions about the phoshphor problematic and earn some money? \nYes or no?");
        if (scanner.hasNext("yes") || scanner.hasNext("Yes")) {
            scanner.nextLine();
            // PrintingUtilities.printOnScreen("Press 1 if you would like to answer some questions\nPress 2 if you dont want to answer");
            boolean answerQuestion = true;
            // int answer = scanner.nextInt();
            // if (answer == 1) {
            while (answerQuestion) {
                if (phoshporQuiz()) {
                    Context.playerImplementation.addMoney(100.0);
                    PrintingUtilities.printOnScreen("You just got $100");
                    Context.playerImplementation.playerBalance();
                    PrintingUtilities.printOnScreen("Yes! The correct answer was 1 Agricultural runoff");
                    PrintingUtilities.printOnScreen("Agricultural runoff, caused by excess fertilizer from farming, often leads to phosphorus pollution in water," +
                            "causing algal blooms and ecosystem issues.");
                    PrintingUtilities.printOnScreen("Would you like to answer some more questions about the phoshphor problematic and earn some money?");
                    PrintingUtilities.printOnScreen("Press 1 if you would like to continue\nPress 2 if you dont want to continue the quiz");
                    int quizContinue = scanner.nextInt();
                    if (quizContinue == 1) {
                        var result = moreQuiz();
                        if (result) {
                            PrintingUtilities.printOnScreen("The correct answer was indeed 1! Excess phosphorus can lead to algae overgrowth, and when the algae decompose, it depletes oxygen, harming aquatic life.");
                            PrintingUtilities.printOnScreen("Excellent! You just answered all the questions correctly.");
                            Context.playerImplementation.addMoney(100.0);
                            PrintingUtilities.printOnScreen("You just got another $100 congratulations!");
                            Context.playerImplementation.playerBalance();
                            PrintingUtilities.printOnScreen("You have just answered all the questions well done!");
                        } else {
                            PrintingUtilities.printOnScreen("Too bad! Wrong answer. No money for you");
                        }
                    }
                    answerQuestion = false;
                } else {
                    PrintingUtilities.printOnScreen("You answer is incorrectly, would you like to try again?");
                    PrintingUtilities.printOnScreen("Press 1 if you would like to continue\nPress 2 if you dont want to continue");
                    int answerContinue = scanner.nextInt();
                    if (answerContinue == 1) {
                        PrintingUtilities.printOnScreen("Nice! Lets try again");
                    } else {
                        PrintingUtilities.printOnScreen("Okay have a nice day!");
                        answerQuestion = false;
                    }
                }
            }
        } else {
            PrintingUtilities.printOnScreen("That was unfortunate...");
        }
    }
    // }


    public boolean phoshporQuiz() {
        PrintingUtilities.printOnScreen("What is the primary cause of phosphorus pollution in the water environment?");
        PrintingUtilities.printOnScreen("Answer with the numbers between 1-3");
        PrintingUtilities.printOnScreen("1: Agricultural runoff\n2: Industrial discharge\n3: Household waste");
        int phosphorProblematic = scanner.nextInt();
        return phosphorProblematic == 1;
    }

    public boolean moreQuiz() {
        PrintingUtilities.printOnScreen("You have to answer 2 questions in a row, to earn some money!");
        PrintingUtilities.printOnScreen("Which form of phosphorus is typically the most bioavailable and easily taken up by plants?");
        PrintingUtilities.printOnScreen("Answer with the numbers between 1-3");
        PrintingUtilities.printOnScreen("1: Organic phosphorus\n2: Phosphorus gas\n3: Phosphate rock");
        int answer = scanner.nextInt();
        if (answer == 3) {
            PrintingUtilities.printOnScreen("Yes! Phosphate rock is the right answer because it contains a form of phosphorus that plants can easily absorb for growth.");
            PrintingUtilities.printOnScreen("Now for the last question");
            PrintingUtilities.printOnScreen("In what ways does excess phosphorus in water bodies contribute to environmental problems?");
            PrintingUtilities.printOnScreen("Answer with the numbers between 1-3");
            PrintingUtilities.printOnScreen("1: It causes oxygen depletion\n2: It enhances biodiversity\n3: It promotes coral reef growth");
            int newAnswer = scanner.nextInt();
            return newAnswer == 1;
        }
        return false;

    }

//
//        while (!leaveUni) {
//            try {
//                String uniInput = uniScanner.nextLine();
//                switch (uniInput) {
//                    case "SF":
//                        PrintingUtilities.printOnScreen("You have helped the project of building a super farm, the project is at {sf_scale}. \nYou can leave by typing leave_uni or support more projects by typing SF or PP");
//                        // superfarm_scale increase by one
//                        break;
//                    case "PP":
//                        PrintingUtilities.printOnScreen("You have helped the project of building a purification plant, the project is at {pp_scale}.\nYou can leave by typing leave_uni or support more projects by typing SF or PP");
//                        // purification_scale is increase by one
//                        break;
//                    case "leave_uni":
//                        leaveUni = true; // Set the leaveUni flag to true to exit the university
//                        break;
//                    default:
//                        PrintingUtilities.printOnScreen("Dean: 'We count on you!!, what do you say??'");
//                }
//
//            } catch (Exception e) {
//                PrintingUtilities.printOnScreen("There has been a problem with the input");
//                e.printStackTrace();
//            } finally
//            {
//                //uniScanner.close();
//            }
//
//        }
//        PrintingUtilities.printOnScreen("Dean: 'Hope to see you again soon!'");
//    }

    @Override
    public String visitMadman() {
        return (this.madArray[(int) (Math.random() * 6)]);
    }

    @Override
    public void calculateHunger() {

    }
}





