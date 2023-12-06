package presentation.controllers;

import business.implementations.FarmImplementation;
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
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Context;
import main.Game;

import java.io.IOException;
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
    public TextArea victoryText;
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

    FarmImplementation farmImplementation = new FarmImplementation();

    String sfHoverText = "The SuperFarm will double the production of your fields for the same cost. " +
            "It will take " + cityImplementation.projectLimit + " levels each costing " + cityImplementation.projectCost +
            " gold to finish. There is a upgrade available after finish!";
    String bfHoverText = "The BioFarm will further increase the production, but for a lower phosphor cost. " +
            "It will take " + cityImplementation.projectLimit + " levels each costing " + cityImplementation.superProjectCost
            + " gold to finish.";
    String ppHoverText = "The PurificationPlant will reduce the amount of phosphor used on your fields, " +
            "by regaining 1 unit of phosphor for every 2 units used. It will take " + cityImplementation.projectLimit +
            " levels each costing " + cityImplementation.projectCost + " gold to finish. " +
            "There is a upgrade available after finish!";
    String sppHoverText = "The Super PurificationPlant will further reduce the amount of phosphor used on your fields " +
            "and regain a lot of phosphor from nature. It will take " + cityImplementation.projectLimit +
            " levels each costing " + cityImplementation.superProjectCost + " gold to finish.";
    String originalUniText = "Dean: 'Good to see you, we have some very promising projects, " +
            "but we are in lack of sufficient funds, would you like to support any of our projects? " +
            "It will take " + cityImplementation.projectLimit + " levels each costing " + cityImplementation.projectCost +
            " gold per to finish. " +
            "There is a upgrade available after finish!";
    String superProjectsText = "Dean: 'We have some new exciting projects for you, even better than the previous, but also a bit more pricy..., I think you will like them!'";

    @FXML
    public void init(ActionEvent actionEvent) throws IOException {
        Game.dispatchCommand("go to_farm");
        goAnotherRoom("/rooms/fxml/farm.fxml");
        seasonProgress();
    }

    public void quitButton(ActionEvent event){
        Platform.exit();
        System.out.println("You have successfully closed the game!");
        System.exit(0);
    }
    public void openPopup() {
        try {
            // Load the FXML file for the popup
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rooms/fxml/Help.fxml"));
            Parent root = loader.load();

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); // Block interactions with other windows
            popupStage.setTitle("Popup");
            popupStage.setScene(new Scene(root));

            // Show the popup window
            popupStage.showAndWait(); // Show and wait for it to close
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closePopup(ActionEvent event) {
        // Get the reference to the button's stage and close it
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void endYearButton(ActionEvent event){
        if (Context.farmImplementation != null){
            Context.farmImplementation.endYear();
        } else {
            System.out.print("you done fucked up");
        }
    }

    public void setEntry(String room, String description) {
        introText.setText("This game is an interactive learning experience! \n" +
                "Throughout the game, you will learn about the phosphorus problems we have in our world. \n" +
                "In the game there are various ways of reaching the ultimate goal of completing the Super Farm and purification plant.\n" +
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
        if (cityImplementation.isPpDone) {
            uniText.setText("The PurificationPlant is now done, congratulations!");
        }
    }

    public void getSfProgress() {
        uniText.setText("Your SuperFarm is now at level: " + String.valueOf(cityImplementation.sf_Progress));
        setLabels();
        if (cityImplementation.isSfDone) {
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

    private void seasonProgress() throws IOException {
        seasonsLabel = (Label) roomStage.getScene().lookup("#seasonsLabel");
        seasonsProgress = (ProgressBar) roomStage.getScene().lookup("#seasonsProgress");
        if (farmImplementation.seasonCount % 4 == 0) {
            seasonsLabel.setText("Winter");
            seasonsProgress.setProgress(0);
            if (farmImplementation.scalePhosphor <= 0) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/rooms/fxml/gameOver.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.setResizable(false); // Prevent resizing of the window
                stage.setScene(new Scene(parent));
                stage.show();
                Platform.setImplicitExit(true); // when closing window game stops (system exit)
                stage.setOnCloseRequest((ae) -> {
                    Platform.exit();
                    System.exit(0);
                });
            }
            if (Context.cityImplementation.isSppDone && Context.cityImplementation.isBfDone && Context.farmImplementation.scalePhosphor > 100) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/rooms/fxml/victory.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.setResizable(false); // Prevent resizing of the window
                stage.setScene(new Scene(parent));
                stage.show();
                Platform.setImplicitExit(true); // when closing window game stops (System.exit)
                stage.setOnCloseRequest((ae) -> {
                    Platform.exit();
                    System.exit(0);
                });
            }
            if (quizButton != null) {
                quizButton.setDisable(false);
            }
        } else if (farmImplementation.seasonCount % 4 == 1) {
            seasonsLabel.setText("Spring");
            seasonsProgress.setProgress(0.33);
        } else if (farmImplementation.seasonCount % 4 == 2) {
            seasonsLabel.setText("Summer");
            seasonsProgress.setProgress(0.66);
        } else if (farmImplementation.seasonCount % 4 == 3) {
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
            seasonProgress();
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
        if (!cityImplementation.isPpDone) {
            Game.dispatchCommand("support pp");
            setLabels();
        } else {
            Game.dispatchCommand("support spp");
            setLabels();
        }
    }

    public void changePpText(MouseEvent mouseEvent) {
        uniText = (TextArea) roomStage.getScene().lookup("#uniText");
        if (!cityImplementation.isPpDone){
            uniText.setText(ppHoverText);
        }
        if (cityImplementation.isPpDone){
            uniText.setText(sppHoverText);
        }

    }

    public void changePpButton(MouseEvent mouseEvent) {
        if (!cityImplementation.isPpDone) {
            Game.dispatchCommand("support pp");
            setLabels();
            getPpProgress();
            if (!cityImplementation.isSppReady && cityImplementation.isPpDone) {
                ppText.setDisable(true);
                ppText.setText("Project done!!!");
            }
        }
        if (cityImplementation.isSppReady) {
            ppText.setText("Support Super Purification Plant");
            Game.dispatchCommand("support spp");
            setLabels();
            if (cityImplementation.isSppDone) {
                ppText.setDisable(true);
                ppText.setText("Project done!!!");
                if (cityImplementation.isBfDone) {
                    uniText = (TextArea) roomStage.getScene().lookup("#uniText");
                    uniText.setText("Well done, all our projects are build. It's a bright future!");
                }
            }
        }
    }

    public void changeSfText() {
        uniText = (TextArea) roomStage.getScene().lookup("#uniText");
        if (!cityImplementation.isSfDone) {
            uniText.setText(sfHoverText);}
        if (cityImplementation.isSfDone){
            uniText.setText(bfHoverText);
        }
    }

    public void changeSfButton(MouseEvent mouseEvent) {
        if (!cityImplementation.isSfDone) {
            Game.dispatchCommand("support sf");
            setLabels();
            getSfProgress();
            if (!cityImplementation.isBfReady && cityImplementation.isSfDone) {
                sfText.setDisable(true);
                sfText.setText("Project done!!!");
            }
        }
        if (cityImplementation.isBfReady) {
            sfText.setText("Support Bio-Farm");
            Game.dispatchCommand("support bf");
            setLabels();
            if (cityImplementation.isBfDone) {
                sfText.setDisable(true);
                sfText.setText("Project done!!!");
                if (cityImplementation.isSppDone) {
                    uniText = (TextArea) roomStage.getScene().lookup("#uniText");
                    uniText.setText("Well done, all our projects are build. It's a bright future!");
                }
            }
        }
    }

    public void changeToOriginalText() {
        uniText = (TextArea) roomStage.getScene().lookup("#uniText");
        if (cityImplementation.isSfDone && cityImplementation.isPpDone && !cityImplementation.isBfReady && !cityImplementation.isSppReady){
            uniText.setText("Well done, all our projects are build. But we have some new ideas for improvements. Stay tuned!");
        }
        if (cityImplementation.isSppDone && cityImplementation.isBfDone) {
            uniText.setText("Well done, all our projects are build. It's a bright future!");
        }
        if (!cityImplementation.isPpDone || !cityImplementation.isSfDone) {
            uniText.setText(originalUniText);
        }
        if ((cityImplementation.isSppReady || cityImplementation.isBfReady) && (!cityImplementation.isBfDone || !cityImplementation.isSppDone)) {
            uniText.setText(superProjectsText);
        }

    }


    @FXML
    private void quizStart() throws IOException {
        MediaController mediaController = new MediaController();
        mediaController.initController(this);
        if (cityImplementation.quizzCount <= 3) {
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
        // set the buttons and textAreas right when entering uni:
        quiz = (TextArea) roomStage.getScene().lookup("#quiz");
        quiz.setText("Would you like to answer some questions about the phosphor problematic and earn some money?");
        uniText = (TextArea) roomStage.getScene().lookup("#uniText");
        if (!cityImplementation.isSfDone || !cityImplementation.isPpDone) {
            uniText.setText(originalUniText);
        }
        if (cityImplementation.isBfReady || cityImplementation.isSppReady) {
            uniText.setText(superProjectsText);
        }
        if (!cityImplementation.isBfReady && cityImplementation.isSfDone) {
            sfText = (Button) roomStage.getScene().lookup("#sfText");
            sfText.setDisable(true);
            sfText.setText("Project done!!!");
        }
        if (cityImplementation.isBfReady) {
            sfText = (Button) roomStage.getScene().lookup("#sfText");
            sfText.setText("Support Bio-Farm");
        }
        if (!cityImplementation.isSppReady && cityImplementation.isPpDone) {
            ppText = (Button) roomStage.getScene().lookup("#ppText");
            ppText.setDisable(true);
            ppText.setText("Project done!!!");
        }
        if (cityImplementation.isSppReady) {
            ppText = (Button) roomStage.getScene().lookup("#ppText");
            ppText.setText("Support Super purification plant");
        }
        if (cityImplementation.isBfDone) {
            sfText = (Button) roomStage.getScene().lookup("#sfText");
            sfText.setDisable(true);
            sfText.setText("Project done!!!");
        }
        if (cityImplementation.isSppDone) {
            ppText = (Button) roomStage.getScene().lookup("#ppText");
            ppText.setDisable(true);
            ppText.setText("Project done!!!");
        }
        if (cityImplementation.isPpDone && cityImplementation.isSfDone && !cityImplementation.isBfReady && !cityImplementation.isSppReady) {
            uniText = (TextArea) roomStage.getScene().lookup("#uniText");
            uniText.setText("New super projects will soon be available soon!");
        }
        if (cityImplementation.isSppDone && cityImplementation.isBfDone) {
            uniText = (TextArea) roomStage.getScene().lookup("#uniText");
            uniText.setText("Well done, you have finished all our projects!");
        }
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

