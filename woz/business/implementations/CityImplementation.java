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
    private final ArrayList<String> allProjectsList = new ArrayList(Arrays.asList("SuperFarm (SF)", "PurificationPlant (PP)", "BioFarm (BF)", "Super Purification Plant(SuperPP)"));

    private void printAllProjectsList() {
        PrintingUtilities.printOnScreen(String.valueOf(allProjectsList));
    }

    public ArrayList<String> availableProjectsList = new ArrayList(Arrays.asList("SuperFarm(SF)", "PurificationPlant (PP)", "BioFarm (BF)", "Super Purification Plant(SuperPP)"));
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
            "Be thou diligent to know the state of thy flocks, and look well to thy herds. For riches are not for ever: and doth the crown endure to every generation?",
            "Phosphorus is crucial for food production, commonly used in fertilizers to sustain crop yields. Modern agriculture heavily depends on phosphorus derived from phosphate rock, a non-renewable resource.",
            "Current global reserves of phosphate rock could be depleted in 50–100 years. The demand for phosphorus is increasing, but the quality of remaining phosphate rock is declining, leading to higher production costs.",
            "Predicted around 2030, the peak of phosphorus production is a significant concern, as it signals a decrease in availability and quality of phosphate rock.",
            "Earlier sources included manure, human excreta, guano, and later, phosphate rock. The transition from organic to mineral sources marked a significant shift in agricultural practices.",
            "The production and transportation of phosphate fertilizers are energy-intensive and contribute to environmental pollution.",
            "The article advocates for recognizing long-term phosphorus scarcity as a critical issue for global food security. It emphasizes the need for efficient phosphorus use and exploring recovery and recycling methods.",
            "The paper discusses the challenges in managing the phosphorus cycle effectively and the need for integrated approaches to ensure sustainable use and recycling of phosphorus",
            "Since the mid-20th century, human activity has significantly increased the environmental flow of phosphorus, primarily through the use of phosphate reserves for fertilizer production. This has led to a one-way flow of P from rocks to farms and then to water bodies, causing ecological harm.",
            "There's growing concern about the long-term availability of cheap phosphorus for fertilizers, as mineable phosphate rock deposits are limited. Unlike nitrogen, phosphorus cannot be synthesized, and there’s no known substitute.",
            "The control of phosphorus reserves is concentrated in a few countries, with Morocco holding a significant share. This concentration poses geopolitical risks and affordability issues for farmers, especially in the developing world.",
            "Solutions proposed include recycling phosphorus (e.g., from human waste and animal manure) and developing more efficient usage methods in agriculture. This would help mitigate both the environmental impacts of excess phosphorus and the scarcity of mineable resources.",
            "Phosphorus is crucial for all life on Earth, it's irreplaceable in food production, and its raw material reserves are unevenly distributed globally.",
            "Phosphorus access is a matter of survival for humans and nations. Most countries, including Denmark and Europe, rely heavily on phosphorus imports. The geopolitical aspects of phosphorus distribution affect global food chains.",
            "The Russian invasion of Ukraine has exacerbated phosphorus-related issues, leading to record-high raw phosphate prices and impacting global food prices.",
            "Major phosphorus-producing countries like the USA, Russia, and China have restricted their phosphorus exports. The EU recognized phosphorus as a critical raw material in 2014 due to its significance for security and prosperity.",
            "The concept of 'peak phosphorus' highlights when global phosphorus production will reach its maximum, after which supply adjustment becomes challenging. The depletion of reserves in major countries within 40 years could significantly impact future political dynamics and potentially lead to resource conflicts.",
            "Morocco holds about 70% of the world's phosphorus reserves, positioning it as a key player in global stability or a potential target in resource conflicts.",
            "Phosphorus also plays a significant role in freshwater quality, with losses from agriculture leading to pollution. The EU's focus on circular economy and initiatives like phosphorus recycling from wastewater are steps towards addressing these challenges.",
            "There is need for a paradigm shift in phosphorus management, for sustainable solutions that integrate environmental restoration with resource recovery.",
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
        return (this.madArray[(int) (Math.random() * 25)]);
    }

    @Override
    public void calculateHunger() {

    }
}





