package main;
/* Main class for launching the game
 */

import business.utils.PrintingUtilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.commands.*;
import presentation.RoomController;

import java.util.Scanner;

public class Game extends Application {
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
        launch(args);
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

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/room.fxml"));
        Parent root = loader.load();
        RoomController roomController = loader.getController();
        roomController.init("Entry", "This is the starting room");
        stage.setTitle("Farmland");
        stage.setScene(new Scene(root,400,300));
        stage.show();
    }
}
