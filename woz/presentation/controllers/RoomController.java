package presentation.controllers;

import business.utils.PrintingUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Game;

public class RoomController {
    @FXML
    private Label roomName;
    @FXML
    private Label descriptionLabel;
    public void init(String room, String description) {
        roomName.setText(room);
        descriptionLabel.setText(description);
    }

    @FXML
    private void goAnotherRoom(ActionEvent event) {
        //TODO implementation when to switch the room
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rooms/farm.fxml"));
            Parent farmRoot = loader.load();

            // Get the controller instance
            FarmHandler farmHandler = loader.getController();

            // Initialize the farm view
            farmHandler.initFarm();

            // Set up a new stage for the farm view
            Stage farmStage = new Stage();
            farmStage.setTitle("Farm");
            farmStage.setScene(new Scene(farmRoot, 400, 300));
            // Show the farm stage
            farmStage.show();
            Game.roomStage.close();
            Game.setRoomStage(farmStage);
        }catch (Exception e){
            PrintingUtilities.printOnScreen("Error with switching views");
            e.printStackTrace();
        }
    }
}
