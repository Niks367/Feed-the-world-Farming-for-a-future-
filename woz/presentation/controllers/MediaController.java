package presentation.controllers;

import business.utils.PrintingUtilities;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import main.Context;
import main.Game;
import main.VideoQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static main.Context.cityImplementation;
import static main.Context.playerImplementation;


public class MediaController {
    public HBox textBoxReplies;
    @FXML
    private HBox buttonBox;

    @FXML
    private VBox buttonVBox;

    @FXML
    private AnchorPane mediaAnchorPane;

    @FXML
    private BorderPane mediaBorderPane;

    @FXML
    private MediaView mediaMediaView;

    @FXML
    private Button option1;

    @FXML
    private Button option2;

    @FXML
    private Button option3;

    @FXML
    private Button option4;
    @FXML
    private Label replyLabel;

    @FXML
    private Button replayButton;
    private MediaPlayer mediaPlayer;

    private RoomController roomController = new RoomController();

    private static List<VideoQuestions> questions = new ArrayList<>();
    private int currentScore = 0;
    private int currentQuestionIndex = 0;
    public void initController(RoomController roomController){
        this.roomController = roomController;
    }
    public void quitQuiz(ActionEvent event) {
        MediaPlayer player = mediaMediaView.getMediaPlayer();
        player.stop();
        player.dispose();
        Node source = (Node) event.getSource(); // Cast to a node
        Stage stage = (Stage) source.getScene().getWindow(); // Get the stage
        stage.close();
        roomController.setLabels();


    }
    private void loadVideoQuestion(VideoQuestions question) {
        Platform.runLater(() -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose(); // Dispose the current media player
            }
            String uri = Objects.requireNonNull(getClass().getResource(question.getVideoPath())).toExternalForm();
            Media media = new Media(uri);
            mediaPlayer = new MediaPlayer(media);
            mediaMediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        });
    }

    @FXML
    private void handleReplay(ActionEvent event) {
        MediaPlayer player = mediaMediaView.getMediaPlayer();
        if (player != null) {
            player.stop();
            player.seek(player.getStartTime());
            player.play();
        }
    }

    @FXML
    private void handleAnswer(ActionEvent event) {
        MediaPlayer player = mediaMediaView.getMediaPlayer();
        Button clickedButton = (Button) event.getSource();
        String answerText = clickedButton.getText();
        int answerIndex = 0;
        switch (answerText) {
            case "A" -> answerIndex = 1;
            case "B" -> answerIndex = 2;
            case "C" -> answerIndex = 3;
            case "D" -> answerIndex = 4;
        }
            // Disable all answer buttons to prevent multiple attempts
        option1.setDisable(true);
        option2.setDisable(true);
        option3.setDisable(true);
        option4.setDisable(true);
        VideoQuestions currentQuestion = questions.get(0); // currently loaded question
        if (answerIndex == currentQuestion.getCorrectAnswer()) {
            currentScore++;
            questions.remove(currentQuestion); // remove question from list
            replyLabel.setText("Correct, you have been rewarded!");
            cityImplementation.knowledge += 1;
            playerImplementation.addMoney(25);
            player.stop();
            cityImplementation.quizzCount++;
            if(cityImplementation.quizzCount < 3) {
                //TODO IF(QuizCounter < 3)
                option1.setDisable(false);
                option2.setDisable(false);
                option3.setDisable(false);
                option4.setDisable(false);
                loadVideoQuestion(questions.get(0));
            }
            else {
                replyLabel.setText("No more Questions!");
                System.out.println("Too many questions for now");
            }
            // remove video
            // Correct answer
            // Increment score
        } else {
            replyLabel.setText("No, I'm sorry, that's wrong!");
            loadNextQuestion();//TODO maybe not!
            // Wrong answer

        }
        // Proceed to next question or do something else
    }

    private void loadNextQuestion() {

        if(!questions.isEmpty()) {
            Collections.shuffle(questions);
            loadVideoQuestion(questions.get(0));
//            // Re-enable answer buttons
//            option1.setDisable(false);
//            option2.setDisable(false);
//            option3.setDisable(false);
//            option4.setDisable(false);
//            // Reset the reply label
//            replyLabel.setText("");
        }
        else{
            System.out.println("No more questions");
            //TODO implement this right
        }
    }

    @FXML
    private void initialize() {
        questions.add(new VideoQuestions("/rooms/media/Question1.mp4",3));
        questions.add(new VideoQuestions("/rooms/media/Question2.mp4",2));
        questions.add(new VideoQuestions("/rooms/media/Question3.mp4",1));
        questions.add(new VideoQuestions("/rooms/media/Question4.mp4",3));
        questions.add(new VideoQuestions("/rooms/media/Question5.mp4",1));
        //TODO add more videos
        loadNextQuestion();

    }
}
