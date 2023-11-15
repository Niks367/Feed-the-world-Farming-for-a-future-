package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
        Game.dispatchCommand("go to_farm");
    }
}
