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
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import main.Context;
import main.Game;
import main.VideoQuestions;

import java.util.Objects;

import static main.Context.cityImplementation;


public class MediaController {
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

    public VideoQuestions[] questions = new VideoQuestions[] {
            new VideoQuestions("/rooms/media/Question1.mp4", 1),
            new VideoQuestions("/rooms/media/Question2.mp4", 2),
            // Add more questions as needed
    };
    private int currentQuestionIndex = 0;
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
        Button clickedButton = (Button) event.getSource();
        int answerIndex = Integer.parseInt(clickedButton.getText());
        // Disable all answer buttons to prevent multiple attempts
        option1.setDisable(true);
        option2.setDisable(true);
        option3.setDisable(true);
        option4.setDisable(true);
        if (answerIndex == questions[currentQuestionIndex].getCorrectAnswer()) {
            replyLabel.setText("Correct!");
            // remove video
            // Correct answer
            // Increment score
        } else {
            replyLabel.setText("Wrong!");
            // Wrong answer

        }
        // Proceed to next question or do something else
    }
    private void loadNextQuestion() {
        // Check if there are more questions
        if (currentQuestionIndex < questions.length - 1) {
            currentQuestionIndex++;
            loadVideoQuestion(questions[currentQuestionIndex]);

            // Re-enable answer buttons
            option1.setDisable(false);
            option2.setDisable(false);
            option3.setDisable(false);
            option4.setDisable(false);

            // Reset the reply label
            replyLabel.setText("");
        } else {
            // Handle the end of the quiz
            // e.g., display final score, show a completion message, etc.
        }
    }
    @FXML
    private void initialize() {

        loadVideoQuestion(questions[currentQuestionIndex]);
        // Initialize the MediaPlayer with your video file
        String uri = Objects.requireNonNull(getClass().getResource("/rooms/media/Question1.mp4")).toExternalForm();
        Media media = new Media(uri);
        mediaPlayer = new MediaPlayer(media);
        mediaMediaView.setMediaPlayer(mediaPlayer);
        mediaMediaView.setStyle("-fx-border-color: red;");
        mediaPlayer.statusProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("MediaPlayer Status: " + newValue);
        });
        mediaPlayer.setOnError(() -> System.out.println("Error with MediaPlayer: " + mediaPlayer.getError()));
        media.errorProperty().addListener((observable, oldValue, newValue) ->
                System.out.println("Error with Media: " + newValue)
        );

        mediaPlayer.play();
    }
}
