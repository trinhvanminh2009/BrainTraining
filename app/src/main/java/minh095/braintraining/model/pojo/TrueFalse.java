package minh095.braintraining.model.pojo;

import java.util.ArrayList;

/**
 * Created by trinh on 8/28/2017.
 */

public class TrueFalse {
    private int numberX;
    private int numberY;
    private String operator;
    private int result;
    private String level;
    private boolean trueOrFalse;

    public TrueFalse(int numberX, int numberY, String operator, int result, String level) {
        this.numberX = numberX;
        this.numberY = numberY;
        this.operator = operator;
        this.result = result;
        this.level = level;
    }

    public boolean isTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
