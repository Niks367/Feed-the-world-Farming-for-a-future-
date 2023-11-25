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
import presentation.controllers.RoomController;

import java.util.Objects;
import java.util.Scanner;

public class Game extends Application {
    static World world = new World();
    public static Context context = new Context(world.getEntry());
    static Command fallback = new CommandUnknown();
    static Registry registry = new Registry(context, fallback);
    static Scanner scanner = new Scanner(System.in);
//    public static Stage roomStage;
//    public static Stack<Stage> roomStack = new Stack<>();
//    public static void setRoomStage(Stage roomStage) {
//        Game.roomStage = roomStage;
//    }
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
        launch(args);
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
    public static void dispatchCommand(String command){
        registry.dispatch(command);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/rooms/room.fxml"));
        Parent root = loader.load();
        RoomController roomController = loader.getController();
        roomController.setEntry("Entry", "");
        roomController.setRoomStage(stage);
        stage.setTitle("Farmland");
        stage.setScene(new Scene(root,400,300));
        stage.show();
//        setRoomStage(stage);
    }
}
