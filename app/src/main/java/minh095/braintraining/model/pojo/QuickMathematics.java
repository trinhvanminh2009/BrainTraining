package minh095.braintraining.model.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trinh on 8/31/2017.
 */

public class QuickMathematics{
    private int numberX;
    private int numberY;
    private String operator;
    private int result;
    private String level;
    private ArrayList<Integer> listWrongResult;
    private ArrayList<Integer> listWrongX;
    private ArrayList<Integer> listWrongY;
    private int unknownPosition;

    public QuickMathematics(int numberX, int numberY, String operator, int result,ArrayList<Integer>listWrongX,
                            ArrayList<Integer>listWrongY, ArrayList<Integer> listWrongResult, int unknownPosition) {
        this.numberX = numberX;
        this.numberY = numberY;
        this.operator = operator;
        this.result = result;
        this.listWrongX = listWrongX;
        this.listWrongY = listWrongY;
        this.listWrongResult = listWrongResult;
        this.unknownPosition = unknownPosition;
    }

    public ArrayList<Integer> getListWrongX() {
        return listWrongX;
    }

    public void setListWrongX(ArrayList<Integer> listWrongX) {
        this.listWrongX = listWrongX;
    }

    public ArrayList<Integer> getListWrongY() {
        return listWrongY;
    }

    public void setListWrongY(ArrayList<Integer> listWrongY) {
        this.listWrongY = listWrongY;
    }

    public int getUnknownPosition() {
        return unknownPosition;
    }

    public void setUnknownPosition(int unknownPosition) {
        this.unknownPosition = unknownPosition;
    }

    public ArrayList<Integer> getListWrongResult() {
        return listWrongResult;
    }

    public void setListWrongResult(ArrayList<Integer> listWrongResult) {
        this.listWrongResult = listWrongResult;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
