package presentation.controllers;

import business.utils.PrintingUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import main.Context;
import main.Game;

import java.util.Objects;
import java.util.Stack;


public class RoomController {
    @FXML
    public static TextArea lakeText;
    public static Stage roomStage;

    public static Stack<Stage> roomStack = new Stack<>();
    @FXML
    private Label roomName;
    @FXML
    private Label descriptionLabel;

    @FXML
    public void init(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_farm");
        goAnotherRoom("/rooms/farm.fxml");
    }
    public void setEntry(String room, String description){
        roomName.setText(room);
        descriptionLabel.setText(description);
    }
    public void setRoomStage(Stage roomStage) {
        RoomController.roomStage = roomStage;
    }
    public void setLakeText(String text){
        lakeText.setText(text);
    }
    @FXML
    private void goToLake(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_lake");
        goAnotherRoom("/rooms/lake.fxml");
        lakeText = (TextArea)roomStage.getScene().lookup("#lakeText");
        setLakeText(Context.lakeImplementation.visitlake());
    }
    @FXML
    private void goFieldToFarm(ActionEvent actionEvent) {
        Game.dispatchCommand("go field_to_farm");
        goAnotherRoom("/rooms/farm.fxml");
    }
    @FXML
    private void goToLakeFarm(ActionEvent actionEvent){
        Game.dispatchCommand("go lake_to_farm");
        goAnotherRoom("/rooms/farm.fxml");
    }
    @FXML
    private void goToCIty(){
        Game.dispatchCommand("go to_city");
    }
    @FXML
    private void goToField(){
        Game.dispatchCommand("go to_field");
        goAnotherRoom("/rooms/field.fxml");
        //TODO needs fxml file
    }

    public void goAnotherRoom(String roomFXM) {

        //TODO implementation when to switch the room
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(roomFXM));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            System.out.println(getClass().getResource("/rooms/styles.css"));
            String css = Objects.requireNonNull(getClass().getResource("/rooms/styles.css")).toExternalForm();
            parent.getStylesheets().add(css);
            roomStage.close();
            stage.show();
            setRoomStage(stage);
            roomStack.push(stage);
        }catch (Exception e){
            PrintingUtilities.printOnScreen("Error with switching views");
            e.printStackTrace();
        }
    }
    private void checkNavigation(){
        // Check if there's a previous room on the stack
        if (!roomStack.isEmpty()) {
            Stage previousRoomStage = roomStack.pop();

            // Close the current room's stage
            Stage currentRoomStage = roomStage;
            currentRoomStage.close();

            // Show the previous room again
            previousRoomStage.show();
        }
    }
}
