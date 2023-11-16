package presentation.controllers;

import javafx.fxml.FXML;
import main.Game;

public class FarmHandler {
    public void initFarm(){
        try {
            Game.dispatchCommand("go to_farm");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void goToLake(){
        Game.dispatchCommand("go to_lake");
    }
    @FXML
    private void goToField(){
        Game.dispatchCommand("go to_field");
    }
}
