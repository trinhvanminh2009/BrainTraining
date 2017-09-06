package minh095.braintraining.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

import minh095.braintraining.R;
import minh095.braintraining.model.pojo.BalanceQuestion;

/**
 * Created by trinh on 9/6/2017.
 */

public class ModelBalanceQuestion {
    public static BalanceQuestion getBalanceQuestion(Context context) {
        ArrayList<String> operatorsListWrong = new ArrayList<>();
        ArrayList<String> operatorsListCorrect = new ArrayList<>();
        BalanceQuestion balanceQuestion;
        Random random = new Random();
        int numberX = random.nextInt(99) + 1;
        int numberY = random.nextInt(99) + 1;
        String correctAnswer = "";
        String wrongAnswer = "";
        if (numberX == numberY) {
            operatorsListWrong.add(context.getString(R.string.lessThan));
            operatorsListWrong.add(context.getString(R.string.greaterThan));
            operatorsListCorrect.add(context.getString(R.string.equal));
            operatorsListCorrect.add(context.getString(R.string.lessThanOrEqual));
            operatorsListCorrect.add(context.getString(R.string.greaterThanOrEqual));
            wrongAnswer = operatorsListWrong.get(random.nextInt(operatorsListWrong.size()));
            correctAnswer = operatorsListCorrect.get(random.nextInt(operatorsListCorrect.size()));

        }
        if (numberX > numberY) {
            operatorsListCorrect.add(context.getString(R.string.greaterThan));
            operatorsListCorrect.add(context.getString(R.string.greaterThanOrEqual));
            operatorsListWrong.add(context.getString(R.string.equal));
            operatorsListWrong.add(context.getString(R.string.lessThan));
            operatorsListWrong.add(context.getString(R.string.lessThanOrEqual));
            wrongAnswer = operatorsListWrong.get(random.nextInt(operatorsListWrong.size()));
            correctAnswer = operatorsListCorrect.get(random.nextInt(operatorsListCorrect.size()));

        }
        if (numberX < numberY) {
            operatorsListCorrect.add(context.getString(R.string.lessThan));
            operatorsListCorrect.add(context.getString(R.string.lessThanOrEqual));
            operatorsListWrong.add(context.getString(R.string.equal));
            operatorsListWrong.add(context.getString(R.string.greaterThan));
            operatorsListWrong.add(context.getString(R.string.greaterThanOrEqual));
            wrongAnswer = operatorsListWrong.get(random.nextInt(operatorsListWrong.size()));
            correctAnswer = operatorsListCorrect.get(random.nextInt(operatorsListCorrect.size()));
        }
        balanceQuestion= new BalanceQuestion(numberX, numberY, correctAnswer, wrongAnswer);
        return balanceQuestion;
    }
}
