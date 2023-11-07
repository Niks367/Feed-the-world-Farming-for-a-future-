package main;
/* Main class for launching the game
 */

import business.utils.PrintingUtilities;
import main.commands.*;

import java.util.Scanner;

public class Game {
    static World world = new World();
    public static Context context = new Context(world.getEntry());
    static Command fallback = new CommandUnknown();
    static Registry registry = new Registry(context, fallback);
    static Scanner scanner = new Scanner(System.in);

    private static void initRegistry() {
        Command cmdExit = new CommandExit();
        registry.register("quit", cmdExit);
        registry.register("go", new CommandGo());
        registry.register("show", new CommandShow());
        registry.register("buy", new CommandBuy());
        registry.register("support", new CommandSupport());
        registry.register("end", new CommandEnd());
        registry.register("help", new CommandHelp(registry));

    }

    public static void main(String[] args) {
        PrintingUtilities.printOnScreen("Welcome to the farm!");
        initRegistry();
        context.getCurrent().welcome();
        context.initPlayer();
        while (!context.isDone()) {
            try {
                System.out.print("> ");
                String line = scanner.nextLine();
                registry.dispatch(line);
            } catch (Exception e) {
                PrintingUtilities.printOnScreen("Something went wrong");
                e.printStackTrace();
            }
        }
        PrintingUtilities.printOnScreen("Game Over 😥");
    }
}
