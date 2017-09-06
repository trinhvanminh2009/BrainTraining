package minh095.braintraining.model.pojo;

/**
 * Created by trinh on 9/6/2017.
 */

public class ColorAndTextQuestion {
    private String colorCode;
    private String colorName;
    private boolean trueOrFalse;

    public ColorAndTextQuestion(String colorCode, String colorName, boolean trueOrFalse) {
        this.colorCode = colorCode;
        this.colorName = colorName;
        this.trueOrFalse = trueOrFalse;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public boolean isTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }
}
