package minh095.braintraining.model.pojo;

/**
 * Created by trinh on 9/6/2017.
 */

public class ColorMatchingWithCode {
    private String color;
    private String codeColor;

    public ColorMatchingWithCode(String color, String codeColor) {
        this.color = color;
        this.codeColor = codeColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCodeColor() {
        return codeColor;
    }

    public void setCodeColor(String codeColor) {
        this.codeColor = codeColor;
    }
}
