package main;

public class VideoQuestions {
    String videoPath;
    int correctAnswer;

    public VideoQuestions(String videoPath, int correctAnswer) {
        this.videoPath = videoPath;
        this.correctAnswer = correctAnswer;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
