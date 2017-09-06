package minh095.braintraining.model.pojo;

/**
 * Created by trinh on 9/6/2017.
 */

public class BalanceQuestion {
    private int numberX;
    private int numberY;
    private String correctAnswer;
    private String wrongAnswer;

    public BalanceQuestion(int numberX, int numberY, String correctAnswer, String wrongAnswer) {
        this.numberX = numberX;
        this.numberY = numberY;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
    }

    public int getNumberX() {
        return numberX;
    }

    public void setNumberX(int numberX) {
        this.numberX = numberX;
    }

    public int getNumberY() {
        return numberY;
    }

    public void setNumberY(int numberY) {
        this.numberY = numberY;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(String wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }
}
