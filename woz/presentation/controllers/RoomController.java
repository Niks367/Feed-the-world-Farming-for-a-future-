package presentation.controllers;

import business.utils.PrintingUtilities;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Context;
import main.Game;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

import static main.Context.*;


public class RoomController {
    @FXML
    public static TextArea cityText;
    @FXML
    public static TextArea madmanText;
    @FXML
    public static TextArea fieldText;
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
    @FXML
    public Button quizButton;
    @FXML
    public StackPane quizStackPane;
    @FXML
    public MediaView quizMediaView;
    public AnchorPane myAnchor;
    public Button ppText;
    public TextArea introText;
    public Button sfText;

    private MediaPlayer mediaPlayer;

    public TextArea populationBox;
    public Label goldLabel;
    public Label phosphorLabel;
    public HBox infoBox;
    public Label phosphorText;
    public Label populationText;
    public Label goldText;
    public Label populationLabel;
    public Label knowledgeLabel;
    public Label seasonsLabel;
    public ProgressBar seasonsProgress;
    public Label knowledgeText;
    @FXML
    private Label roomName;
    @FXML
    private Label descriptionLabel;
    String sfHoverText = "The SuperFarm will double the production of your fields for the same cost. It will take 3 levels each costing" + cityImplementation.projectCost + " gold to finish. There is a upgrade available after finish!";

    String ppHoverText = "The PurificationPlant will reduce the amount of phosphor used on your fields, by regaining 1 unit of phosphor for every 2 units used. It will take 3 levels each costing" + cityImplementation.projectCost + " gold to finish. There is a upgrade available after finish!";

    String originalUniText = "Dean : 'Good to see you, we have some very promising projects, but we are in lack of sufficient funds, would you like to support any of our projects? It will take 3 levels each costing " + cityImplementation.projectCost + " gold per to finish. There is a upgrade available after finish!";

    @FXML
    public void init(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_farm");
        goAnotherRoom("/rooms/fxml/farm.fxml");
        dayProgress();
    }

    public void setEntry(String room, String description) {
        introText.setText("This game is an interactive learning experience! \n" +
                "Throughout the game, you will learn about the phosphourus problems we have in our world. \n" +
                "In the game there are various ways of reaching the ultimate goal of compleating the Super Farm and purifacation plant.\n" +
                " While you are working towards saving the world, you will have to balance your hunger, money and population.\n" +
                " So, be careful of how you choose to spend your time! The new season may be the start of a better world, or the end to your demise.\n" +
                " Also, remember to listen to the right people some may spread misinformation... Others will help you understand the worlds problems further.\n" +
                " With all that said, enjoy your time at the Farm!");

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
        setMoney(String.valueOf(playerImplementation.money));
    }

    public void setPhosphor(String text) {
        phosphorLabel.setText(text);
    }

    public void getPhosphor() {
        phosphorLabel = (Label) roomStage.getScene().lookup("#phosphorLabel");
        setPhosphor(String.valueOf(Context.farmImplementation.scalePhosphor));
    }

    public void setKnowledge(String text) {
        knowledgeLabel.setText(text);
    }

    public void getKnowledge() {
        knowledgeLabel = (Label) roomStage.getScene().lookup("#knowledgeLabel");
        setKnowledge(String.valueOf(cityImplementation.knowledge));
    }

    public void setPopulation(String text) {
        populationLabel.setText(text);
    }

    public void getPopulation() {
        populationLabel = (Label) roomStage.getScene().lookup("#populationLabel");
        setPopulation(String.valueOf(Context.cityImplementation.getPopulation()));
    }
    public void getPpProgress() {
        uniText.setText("Your PurificationPlant is now at level: " + String.valueOf(cityImplementation.pp_Progress));
        setLabels();
        if(cityImplementation.isPpDone) {
            uniText.setText("The PurificationPlant is now done, congratulations!");
        }
    }
    public void getSfProgress() {
        uniText.setText("Your SuperFarm is now at level: " + String.valueOf(cityImplementation.sf_Progress));
        setLabels();
        if(cityImplementation.isSfDone) {
            uniText.setText("The SuperFarm is now finished, congratulations!");
        }
    }

    @FXML
    private void goToLake(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_lake");
        goAnotherRoom("/rooms/fxml/lake.fxml");
        lakeText = (TextArea) roomStage.getScene().lookup("#lakeText");
        setLakeText(Context.lakeImplementation.visitlake());
    }

    public void setLabels() {
        getMoney();
        getPhosphor();
        getPopulation();
        getKnowledge();
    }

    @FXML
    private void goToLakeFromCity() {
        Game.dispatchCommand("go city_to_lake");
        goAnotherRoom("/rooms/fxml/lake.fxml");
        lakeText = (TextArea) roomStage.getScene().lookup("#lakeText");
        setLakeText(Context.lakeImplementation.visitlake());
    }

    @FXML
    private void goToCityFromShop() {
        Game.dispatchCommand("go west");
        goAnotherRoom("/rooms/fxml/city.fxml");
        cityTextUpdate();
    }

    private void dayProgress() {
        seasonsLabel = (Label) roomStage.getScene().lookup("#seasonsLabel");
        seasonsProgress = (ProgressBar) roomStage.getScene().lookup("#seasonsProgress");
        if (farmImplementation.dayCount % 4 == 0) {
            seasonsLabel.setText("Winter");
            seasonsProgress.setProgress(0);
            if (quizButton != null) {
                quizButton.setDisable(false);
            }
        } else if (farmImplementation.dayCount % 4 == 1) {
            seasonsLabel.setText("Spring");
            seasonsProgress.setProgress(0.33);
        } else if (farmImplementation.dayCount % 4 == 2) {
            seasonsLabel.setText("Summer");
            seasonsProgress.setProgress(0.66);
        } else if (farmImplementation.dayCount % 4 == 3) {
            seasonsLabel.setText("Autumn");
            seasonsProgress.setProgress(1);
        } else {
            seasonsLabel.setText("Out of season");
        }
    }

    private void cityTextUpdate() {
        cityText = (TextArea) roomStage.getScene().lookup("#cityText");
        if (cityImplementation.isHunger) {
            cityText.setText("The people in the city are starving! Hurry up and give them something to eat.");
        } else {
            cityText.setText("The people in the city are happy and not very hungry.");
        }
    }

    @FXML
    private void goFieldToFarm(ActionEvent actionEvent) {
        Game.dispatchCommand("go fields_to_farm");
        goAnotherRoom("/rooms/fxml/farm.fxml");
    }

    @FXML
    private void goToLakeFarm(ActionEvent actionEvent) {
        Game.dispatchCommand("go lake_to_farm");
        goAnotherRoom("/rooms/fxml/farm.fxml");
    }


    @FXML
    private void goToField() {
        Game.dispatchCommand("go to_fields");
        goAnotherRoom("/rooms/fxml/field.fxml");
        fieldText = (TextArea) roomStage.getScene().lookup("#fieldText");
        fieldText.setText(Context.fieldImplementation.visitFields());

    }

    public void goAnotherRoom(String roomFXM) {

        //TODO implementation when to switch the room
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(roomFXM));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setResizable(false); // Prevent resizing of the window
            roomStage.close();
            stage.show();
            Platform.setImplicitExit(true);
            stage.setOnCloseRequest((ae) -> {
                Platform.exit();
                System.exit(0);
            });
            setRoomStage(stage);
            roomStack.push(stage);
            setLabels();
            dayProgress();
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
        goAnotherRoom("/rooms/fxml/city.fxml");
        cityTextUpdate();
    }

    public void goToCityFromUni(ActionEvent actionEvent) {
        Game.dispatchCommand("go east");
        goAnotherRoom("/rooms/fxml/city.fxml");
        cityTextUpdate();
    }

    @FXML
    private void goToCityFromMadman() {
        Game.dispatchCommand("go north");
        goAnotherRoom("/rooms/fxml/city.fxml");
        cityTextUpdate();
    }

    public void goToMadman(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_madman");
        goAnotherRoom("/rooms/fxml/madman.fxml");
        madmanText = (TextArea) roomStage.getScene().lookup("#madmanText");
        madmanText.setText(Context.cityImplementation.visitMadman());
    }

    @FXML
    private void supportProjectPP() {
        //TODO implement supporting uni.
        Game.dispatchCommand("support pp");
        setLabels();
    }
    public void changePpText() {
        uniText = (TextArea) roomStage.getScene().lookup("#uniText");
        uniText.setText(ppHoverText);
    }
    public void changePpButton(MouseEvent mouseEvent) {
        Game.dispatchCommand("support pp");
        getPpProgress();
    }

    @FXML
    private void supportProjectSF() {
        //TODO implement supporting uni.
        Game.dispatchCommand("support sf");
        setLabels();
    }
    public void changeSfText() {
        uniText = (TextArea) roomStage.getScene().lookup("#uniText");
        uniText.setText(sfHoverText);
    }
    public void changeSfButton(MouseEvent mouseEvent) {
        Game.dispatchCommand("support sf");
        getSfProgress();


        setLabels();


    }
    public void changeToOriginalText() {
        uniText = (TextArea) roomStage.getScene().lookup("#uniText");
        uniText.setText(originalUniText);
    }


    @FXML
    private void quizStart() throws IOException {
        MediaController mediaController = new MediaController();
        mediaController.initController(this);
        if (cityImplementation.quizzCount < 3) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/rooms/fxml/mediaPlayer.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setResizable(false); // Prevent resizing of the window
            stage.setScene(new Scene(parent));
            stage.show();
            cityImplementation.quizzCount++;
        } else {
            quiz = (TextArea) roomStage.getScene().lookup("#quiz");
            quiz.setText("I can only give you 3 questions per season, but do come back next season. There is a man in the city that knows about things, go see him or simply study nature!");
            quizButton.setDisable(true);
        }


    }


    public void goToUni(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_uni");
        goAnotherRoom("/rooms/fxml/uni.fxml");
        quiz = (TextArea) roomStage.getScene().lookup("#quiz");
        quiz.setText("Would you like to answer some questions about the phoshphor problematic and earn some money?");
        uniText = (TextArea) roomStage.getScene().lookup("#uniText");
        uniText.setText(originalUniText);
    }
    public void goToShop(ActionEvent actionEvent) {
        Game.dispatchCommand("go to_shop");
        goAnotherRoom("/rooms/fxml/shop.fxml");
        shopText = (TextArea) roomStage.getScene().lookup("#shopText");
        shopText.setText("Boy in the shop: 'What can I do for you today? " +
                "Following items are available for purchase..." +
                "Phosphor and Land.");
    }

    public void buyLand(ActionEvent actionEvent) {
        if (playerImplementation.money >= farmImplementation.getPriceOfLand()) {
            if (farmImplementation.getFieldsForPurchase() > 0) {
                Game.dispatchCommand("buy land");
                shopText = (TextArea) roomStage.getScene().lookup("#shopText");
                shopText.setText("Here you go, that will be " + cityImplementation.spentGold + " gold, anything else I can do you for?");
                setLabels();
            } else {
                shopText.setText("There is no more land to purchase...");
            }
        } else {
            shopText.setText("It seems you are out of cash...");
        }

    }


    public void buyPhosphor(ActionEvent actionEvent) {
        if (playerImplementation.money >= farmImplementation.land * farmImplementation.phosphorPrice) {
            if (!farmImplementation.isPhophorized) {
                Game.dispatchCommand("buy phosphor");
                shopText = (TextArea) roomStage.getScene().lookup("#shopText");
                shopText.setText("Here you go, that will be " + cityImplementation.spentGold + " gold, anything else I can do you for?");
                setLabels();
            } else {
                shopText.setText("It seems your fields are already fertilized this season.");
            }
        } else {
            shopText.setText("It seems you lack the sufficient funds...");


        }
    }


}

