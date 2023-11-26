package presentation.controllers;

import business.utils.PrintingUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Context;
import main.Game;

import java.util.Stack;


public class RoomController {

    @FXML
    public static TextArea lakeText;
    public static Stage roomStage;
    @FXML
    public static TextArea quiz;
    @FXML
    public static TextArea uniText;

    public static Stack<Stage> roomStack = new Stack<>();
    @FXML
    public static TextArea shopText;
    @FXML
    public Button playGameButton;
    @FXML
    public Button buyPhosphorButton;
    @FXML
    public Button buyLandButton;
    @FXML
    public Button goToCityFromShopButton;
    public TextArea populationBox;
    public Label goldLabel;
    public TextArea goldBox;
    public Label phosphorLabel;

    public TextArea phosphorBox;
    public HBox infoBox;
    public Label phosphorText;
    public Label populationText;
    public Label goldText;
    public Label populationLabel;
    @FXML
    private Label roomName;
    @FXML
    private Label descriptionLabel;

    @FXML
    public void init(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_farm");
        goAnotherRoom("/rooms/farm.fxml");
    }

    public void setEntry(String room, String description) {
        roomName.setText(room);
        descriptionLabel.setText(description);
    }

    public void setRoomStage(Stage roomStage) {
        RoomController.roomStage = roomStage;
    }

    public void setLakeText(String text) {
        lakeText.setText(text);
    }

    public void setMoney(String text) {
        goldLabel.setText(text);
    }

    public void getMoney() {
        goldLabel = (Label) roomStage.getScene().lookup("#goldLabel");
        setMoney(String.valueOf(Context.playerImplementation.money));

    }
    public void setPhosphor(String text) {
        phosphorLabel.setText(text);
    }
    public void getPhosphor() {
        phosphorLabel = (Label) roomStage.getScene().lookup("#phosphorLabel");
        setPhosphor(String.valueOf(Context.farmImplementation.scalePhosphor));
    }
    public void setPopulation(String text) {
        populationLabel.setText(text);
    }
    public void getPopulation() {
        populationLabel = (Label) roomStage.getScene().lookup("#populationLabel");
        setPopulation(String.valueOf(Context.cityImplementation.getPopulation()));
    }

    @FXML
    private void goToLake(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_lake");
        goAnotherRoom("/rooms/lake.fxml");
        lakeText = (TextArea) roomStage.getScene().lookup("#lakeText");
        setLakeText(Context.lakeImplementation.visitlake());
    }

    public void setLabels() {
        getMoney();
        getPhosphor();
        getPopulation();
    }

    @FXML
    private void goToLakeFromCity() {
        Game.dispatchCommand("go city_to_lake");
        goAnotherRoom("/rooms/lake.fxml");
        lakeText = (TextArea) roomStage.getScene().lookup("#lakeText");
        setLakeText(Context.lakeImplementation.visitlake());
    }

    @FXML
    private void goToCityFromShop() {
        Game.dispatchCommand("go west");
        goAnotherRoom("/rooms/city.fxml");
    }

    @FXML
    private void goFieldToFarm(ActionEvent actionEvent) {
        Game.dispatchCommand("go fields_to_farm");
        goAnotherRoom("/rooms/farm.fxml");
    }

    @FXML
    private void goToLakeFarm(ActionEvent actionEvent) {
        Game.dispatchCommand("go lake_to_farm");
        goAnotherRoom("/rooms/farm.fxml");
    }


    @FXML
    private void goToField() {
        Game.dispatchCommand("go to_fields");
        goAnotherRoom("/rooms/field.fxml");

    }

    public void goAnotherRoom(String roomFXM) {

        //TODO implementation when to switch the room
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(roomFXM));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
//            String css = Objects.requireNonNull(getClass().getResource("/rooms/styles.css")).toExternalForm();
//            parent.getStylesheets().add(css);
            roomStage.close();
            stage.show();
            setRoomStage(stage);
            roomStack.push(stage);
            setLabels();
        } catch (Exception e) {
            PrintingUtilities.printOnScreen("Error with switching views");
            e.printStackTrace();
        }
    }

    private void checkNavigation() {
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

    public void goToCity(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_city");
        goAnotherRoom("/rooms/city.fxml");
    }

    public void goToCityFromUni(ActionEvent actionEvent) {
        Game.dispatchCommand("go east");
        goAnotherRoom("/rooms/city.fxml");
    }

    @FXML
    private void goToCityFromMadman() {
        Game.dispatchCommand("go north");
        goAnotherRoom("/rooms/city.fxml");
    }

    public void goToMadman(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_madman");
        goAnotherRoom("/rooms/madman.fxml");
    }

    @FXML
    private void supportProjectPP() {
        //TODO implement supporting uni.
        Game.dispatchCommand("support pp");
    }

    @FXML
    private void supportProjectSF() {
        //TODO implement supporting uni.
        Game.dispatchCommand("support sf");
    }

    @FXML
    private void quizStart() {
        //TODO implement the quiz
    }

    public void goToUni(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_uni");
        goAnotherRoom("/rooms/uni.fxml");
        quiz = (TextArea) roomStage.getScene().lookup("#quiz");
        quiz.setText("Would you like to answer some questions about the phoshphor problematic and earn some money?");
        uniText = (TextArea) roomStage.getScene().lookup("#uniText");
        uniText.setText("Dean : 'Good to see you, we have some very promising projects, but we are in lack of sufficient funds, would you like to support any of our projects? Price 100 gold");
    }

    public void goToShop(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_shop");
        goAnotherRoom("/rooms/shop.fxml");
        shopText = (TextArea) roomStage.getScene().lookup("#shopText");
        shopText.setText("Boy in the shop: 'What can I do for you today? " +
                "Following items are available for purchase..." +
                "Phosphor and Land.");
    }

    public void buyLand(ActionEvent actionEvent) {
        Game.dispatchCommand("buy land");
        setLabels();
    }

    public void buyPhosphor(ActionEvent actionEvent) {
        Game.dispatchCommand("buy phosphor");
        setLabels();
    }
}
