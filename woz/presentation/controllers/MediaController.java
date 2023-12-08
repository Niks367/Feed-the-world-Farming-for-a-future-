package presentation.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import main.VideoQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static main.Context.cityImplementation;
import static main.Context.playerImplementation;


public class MediaController {
    public HBox textBoxReplies;
    public Button quitButton;
    public TextArea replyText;
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

    private static final List<VideoQuestions> questions = new ArrayList<>();


    public void initController(RoomController roomController) {
        //mainly used to call setLabels() to update the infobar from mediaPlaer
        this.roomController = roomController;
    }


    public void quitQuiz(ActionEvent event) {
        if (cityImplementation.quizzCount > 3) {
            Node source = (Node) event.getSource(); // Cast to a node
            Stage stage = (Stage) source.getScene().getWindow(); // Get the stage
            stage.close();
            roomController.setLabels();
        } else {
            MediaPlayer player = mediaMediaView.getMediaPlayer();
            if (mediaPlayer != null) {
                player.stop();
                player.dispose();
            }
            Node source = (Node) event.getSource(); // Cast to a node
            Stage stage = (Stage) source.getScene().getWindow(); // Get the stage
            stage.close();
            roomController.setLabels();
        }
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
        if (answerIndex == currentQuestion.getCorrectAnswer()) { // when answer is correct
            questions.remove(currentQuestion); // remove question from list
            cityImplementation.knowledge += 1; // increase knowledge attribute
            playerImplementation.addMoney(25); // give player gold
            cityImplementation.quizzCount++; // increase quizzcount attribute
            int choice = (int) (Math.random() * 3); // pick one out of three replies
            switch (choice) {
                case 0 -> replyText.setText("Correct, you have been rewarded!");
                case 1 -> replyText.setText("Excellent, here is a little something!");
                case 2 -> replyText.setText("You never cease to amaze me, take this little token of appreciation!");
            }

            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            if (cityImplementation.quizzCount <= 3) { // if player has not yet seen 3 videos
                option1.setDisable(false); // re-activate buttons
                option2.setDisable(false);
                option3.setDisable(false);
                option4.setDisable(false);
                loadVideoQuestion(questions.get(0)); // see another video
            } else {
                replyLabel.setText("Correct, come again soon!"); // player has seen 3 videos
            }
        } else { // wrong answer
            replyText.setText("No, I'm sorry, that's wrong!");
            cityImplementation.quizzCount++; // increase quizzcount attribute
            if (cityImplementation.quizzCount <= 3) { // if player has not yet seen 3 videos
                option1.setDisable(false); // re-activate buttons
                option1.setDisable(false);
                option2.setDisable(false);
                option3.setDisable(false);
                option4.setDisable(false);
                Collections.shuffle(questions);//shuffle list in order to decrease chance of repeated question
                loadVideoQuestion(questions.get(0)); // see another video
            } else { // player has seen 3 videos
                replyText.setText("I'm sorry, but that's just not true, come again soon!");
                if (mediaPlayer != null) {
                    player.stop();
                    player.dispose();
                }
                Node source = (Node) event.getSource(); // Cast event to a node
                Stage stage = (Stage) source.getScene().getWindow(); // Get the stage
                roomController.setLabels(); // update infobar
            }
        }
    }

    private void loadNextQuestion() {

        if (!questions.isEmpty()) {
            Collections.shuffle(questions);
            loadVideoQuestion(questions.get(0));
//            // Re-enable answer buttons
//            option1.setDisable(false);
//            option2.setDisable(false);
//            option3.setDisable(false);
//            option4.setDisable(false);
//            // Reset the reply label
//            replyLabel.setText("");
        } else {
            System.out.println("No more questions");
            //TODO implement this right
        }
    }

    @FXML
    private void initialize() {
        questions.add(new VideoQuestions("/rooms/media/Video1.mp4", 3));
        questions.add(new VideoQuestions("/rooms/media/Video2.mp4", 2));
        questions.add(new VideoQuestions("/rooms/media/Video3.mp4", 1));
        questions.add(new VideoQuestions("/rooms/media/Video4.mp4", 3));
        questions.add(new VideoQuestions("/rooms/media/Video5.mp4", 1));
        questions.add(new VideoQuestions("/rooms/media/Video6.mp4", 2));
        questions.add(new VideoQuestions("/rooms/media/Video7.mp4", 2));
        questions.add(new VideoQuestions("/rooms/media/Video8.mp4", 3));
        questions.add(new VideoQuestions("/rooms/media/Video9.mp4", 4));
        questions.add(new VideoQuestions("/rooms/media/Video10.mp4", 3));
        questions.add(new VideoQuestions("/rooms/media/Video11.mp4", 2));
        questions.add(new VideoQuestions("/rooms/media/Video12.mp4", 3));
        questions.add(new VideoQuestions("/rooms/media/Video13.mp4", 3));
        questions.add(new VideoQuestions("/rooms/media/Video14.mp4", 2));
        questions.add(new VideoQuestions("/rooms/media/Video15.mp4", 3));
        questions.add(new VideoQuestions("/rooms/media/Video16.mp4", 2));
        questions.add(new VideoQuestions("/rooms/media/Video17.mp4", 3));
        questions.add(new VideoQuestions("/rooms/media/Video18.mp4", 2));
        questions.add(new VideoQuestions("/rooms/media/Video19.mp4", 3));
        questions.add(new VideoQuestions("/rooms/media/Video20.mp4", 3));
        questions.add(new VideoQuestions("/rooms/media/Video21.mp4", 2));
        questions.add(new VideoQuestions("/rooms/media/Video22.mp4", 1));
        questions.add(new VideoQuestions("/rooms/media/Video23.mp4", 4));
        questions.add(new VideoQuestions("/rooms/media/Video24.mp4", 4));
        questions.add(new VideoQuestions("/rooms/media/Video25.mp4", 2));
        questions.add(new VideoQuestions("/rooms/media/Video26.mp4", 3));
        questions.add(new VideoQuestions("/rooms/media/Video27.mp4", 2));
        questions.add(new VideoQuestions("/rooms/media/Video28.mp4", 2));
        questions.add(new VideoQuestions("/rooms/media/Video29.mp4", 1));
        questions.add(new VideoQuestions("/rooms/media/Video30.mp4", 1));
        questions.add(new VideoQuestions("/rooms/media/Video31.mp4", 2));


        //TODO add more videos
        loadNextQuestion();

    }
}
