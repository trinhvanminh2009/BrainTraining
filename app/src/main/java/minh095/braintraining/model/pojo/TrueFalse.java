package minh095.braintraining.model.pojo;

import java.util.ArrayList;

/**
 * Created by trinh on 8/28/2017.
 */

public class TrueFalse {
    private int numberX;
    private int numberY;
    private ArrayList<String>listLevel;
    private int result;

    public TrueFalse(int numberX, int numberY, ArrayList<String> listLevel, int result) {
        this.numberX = numberX;
        this.numberY = numberY;
        this.listLevel = listLevel;
        this.result = result;
    }

    public ArrayList<String> getListLevel() {
        return listLevel;
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
