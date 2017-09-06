package minh095.braintraining.model;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import minh095.braintraining.model.pojo.ColorAndTextQuestion;
import minh095.braintraining.model.pojo.ColorMatchingWithCode;

/**
 * Created by trinh on 9/6/2017.
 */

public class ModelColorAndTextQuestion {
    private static ArrayList<ColorAndTextQuestion> getListColorAndTextQuestion() {
        ArrayList<ColorAndTextQuestion> colorAndTextQuestions = new ArrayList<>();
        ColorAndTextQuestion colorAndTextQuestion;
        Random random = new Random();
        ArrayList<ColorMatchingWithCode> colorMatchingWithCodes = new ArrayList<>();
        for (int i = 0; i < getListCodeColor().size(); i++) {
            colorMatchingWithCodes.add(new ColorMatchingWithCode(getListColor().get(i), getListCodeColor().get(i)));
        }
        int checkCountTrue = 0;
        int checkCountFalse = 0;
        while (colorAndTextQuestions.size() < 10) {
            String randomColorCode = getListCodeColor().get(random.nextInt(getListCodeColor().size() - 1));
            String randomColorName = getListColor().get(random.nextInt(getListColor().size() - 1));
            for (int i = 0; i < colorMatchingWithCodes.size(); i++) {
                if (colorMatchingWithCodes.get(i).getCodeColor().equals(randomColorCode)) {
                    if (colorMatchingWithCodes.get(i).getColor().equals(randomColorName)) {
                        if (checkCountTrue < 6) {
                            colorAndTextQuestion = new ColorAndTextQuestion(randomColorCode, randomColorName, true);
                            colorAndTextQuestions.add(colorAndTextQuestion);
                            checkCountTrue++;
                        }
                    } else {
                        if (checkCountFalse < 6) {
                            colorAndTextQuestion = new ColorAndTextQuestion(randomColorCode, randomColorName, false);
                            colorAndTextQuestions.add(colorAndTextQuestion);
                            checkCountFalse++;
                        }

                    }
                }
            }
        }
        return colorAndTextQuestions;

    }

    public static ColorAndTextQuestion getCurrentColorTextAndQuestion() {
        Random random = new Random();
        Log.e("Size"," " +getListColorAndTextQuestion().size());
        return getListColorAndTextQuestion().get(random.nextInt(getListColorAndTextQuestion().size() - 1));
    }

    private static ArrayList<String> getListCodeColor() {
        ArrayList<String> listCodeColor = new ArrayList();
        listCodeColor.add("#F44336");
        listCodeColor.add("#E91E63");
        listCodeColor.add("#9C27B0");
        listCodeColor.add("#2196F3");
        listCodeColor.add("#4CAF50");
        listCodeColor.add("#FFEB3B");
        listCodeColor.add("#FF9800");
        listCodeColor.add("#795548");
        listCodeColor.add("#9E9E9E");
        listCodeColor.add("#000000");
        return listCodeColor;
    }

    private static ArrayList<String> getListColor() {
        ArrayList<String> listColor = new ArrayList();
        listColor.add("Red");
        listColor.add("Pink");
        listColor.add("Purple");
        listColor.add("Blue");
        listColor.add("Green");
        listColor.add("Yellow");
        listColor.add("Orange");
        listColor.add("Brown");
        listColor.add("Grey");
        listColor.add("Black");
        return listColor;
    }
}
