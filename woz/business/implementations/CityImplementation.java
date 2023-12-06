package business.implementations;

import business.interfaces.City;
import business.utils.PrintingUtilities;
import main.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CityImplementation implements City {
    public int spentGold = 0;
    int hunger = 0;
    private boolean isInCity;
    private boolean isInUni;
    private boolean isInShop = false;
    int population = 100;
    public int sf_Progress = 0;
    public int pp_Progress = 0;
    public int spp_Progress = 0;
    public int bf_Progress = 0;
    public int projectLimit = 3;
    public double projectCost = 50;
    public double superProjectCost = 100;
    public boolean isPpDone = false; //Boolean for PP
    public boolean isSfDone = false; //Boolean for Sf
    public boolean isSppDone = false; //Boolean for Super PP
    public boolean isBfDone = false; //Boolean for Bio Farm
    public boolean isHunger = false;
    public boolean isBfReady = false;
    public boolean isSppReady = false;
    public int quizzCount = 0;

    public int knowledge = 0;
    Scanner scanner = new Scanner(System.in);
    /* allProjectsList is a list of all projects that can be available during different phases of the game:
    It is hardcoded.
    For now the Dean uses this list.*/
    private final ArrayList<String> allProjectsList = new ArrayList(Arrays.asList("SuperFarm (SF)", "PurificationPlant (PP)", "BioFarm (BF)", "Super Purification Plant(SPP)"));

    private void printAllProjectsList() {
        PrintingUtilities.printOnScreen(String.valueOf(allProjectsList));
    }

    public ArrayList<String> availableProjectsList = new ArrayList(Arrays.asList("SuperFarm(SF)", "PurificationPlant (PP)", "BioFarm (BF)", "Super Purification Plant(SPP)"));
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
            "Hast thou found honey? eat so much as is sufficient for thee, lest thou be filled therewith, and vomit it.",
            "It is not good to eat much honey: so for men to search their own glory is not glory.",
            "As snow in summer, and as rain in harvest, so honour is not seemly for a fool.",
            "Seest thou a man wise in his own conceit? there is more hope of a fool than of him.",
            "The world is changed. I feel it in the water. I feel it in the earth. I smell it in the air",
            "It all began with the forging of the Great Rings. Three were given to the Elves; immortal, wisest and fairest of all beings. Seven, to the Dwarf Lords great miners and craftsmen of the mountain halls. And Nine, nine rings were gifted to race of Men, who above all else disire power",
            "My mama always said life was like a box of chocolates, you never know what you are going to get.",
            "If you see the dragon fly, best you drink the flagon dry",
            "A wizard is never late. Nor is he early, he arrives precisely when he means to",
            "I amar prestar aen, han mathon ne nen han mathon ne chae a han noston ned 'willith",
            "There is a dual challenge with phosphorus. The environmental pollution due to excess phosphorus, and the limitation of mineable phosphorus resources. This means that there is a problem with having to much phosphorus in some areas leading to pollution and having too little in other areas affecting the agriculture.",
            "Since the mid-20th century, human activity has significantly increased the environmental flow of phosphorus, primarily through the use of phosphate reserves for fertilizer production. This has led to a one-way flow of phosphorus from rocks to farms and then to water bodies, causing ecological harm.",
            "There's growing concern about the long-term availability of cheap phosphorus for fertilizers, as mineable phosphate rock deposits are limited. Unlike nitrogen, phosphorus cannot be synthesized, and there’s no known substitute.",
            "The control of phosphorus reserves is concentrated in a few countries, with Morocco holding a significant share. This concentration poses geopolitical risks and affordability issues for farmers, especially in the developing world.",
            "Solutions for the soon gone phosphorus, includes recycling. For example, from human and animal waste and developing more efficient usage methods of use in agriculture. This would mitigate both the environmental impacts of excess phosphorus, and what is left of the limited mineable resources. ",
            "To help the control of phosphorus, it would be useful with better governance, reliable estimates of the phosphorus reserves, and the establishment of national and international research centers for nutrient sustainability. A solution could also be market based, with phosphorus-emission trading.",
            "The Russian invasion of Ukraine has made the phosphorus-related issues even worse, leading to record-high raw phosphate prices and impacting global food prices.",
            "Major phosphorus-producing countries like the USA, Russia, and China have restricted their phosphorus exports. The EU recognized phosphorus as a critical raw material in 2014 due to its significance for security and prosperity.",
            "Morocco holds about 70% of the world's phosphorus reserves, positioning it as a key player in global stability or a potential target in resource conflicts.",
            "A sustainable solution for the phosphorus problems, would be a paradigm shift in phosphorus management, that advocates for an integrated environment restoration with resource recovery.",

    };



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
    }

    private void quizTime() {
        //System.out.printf("We currently have %d projects)", calculateAvailableProjects());
        printAllProjectsList();
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
        } else {
            PrintingUtilities.printOnScreen("You answer is incorrectly, would you like to try again?");
            PrintingUtilities.printOnScreen("Press 1 if you would like to continue\nPress 2 if you dont want to continue");
            int answerContinue = scanner.nextInt();
            if (answerContinue == 1) {
                PrintingUtilities.printOnScreen("Nice! Lets try again");
            } else {
                PrintingUtilities.printOnScreen("Okay have a nice day!");
            }
        }
    }

    @Override
    public void visitUni() {
        setIsInUni(true);
        //TODO implement the quiz with its own button.
        // quizTime();
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
        return (this.madArray[(int) (Math.random() * 20)]);
    }

    @Override
    public boolean calculateHunger() {
        return (Context.playerImplementation.money < Context.cityImplementation.population);
    }

}





