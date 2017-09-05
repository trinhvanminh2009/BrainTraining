package minh095.braintraining.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minh095.braintraining.R;
import minh095.braintraining.model.pojo.QuickMathematics;

/**
 * Created by trinh on 8/31/2017.
 */

public class ModelQuickMathematics {

    /**
     * There are three difficulty levels with different operators
     * randomUnknownPosition to know where is unknown number in that question
     */
    public static List<QuickMathematics> randomQuickMathematics(Context context, int currentScore) {
        List<QuickMathematics> quickMathematicsList = new ArrayList<>();
        ArrayList<String> levelList = new ArrayList<>();
        ArrayList<String> operatorsEasyList = new ArrayList<>();
        ArrayList<String> operatorsNormalList = new ArrayList<>();
        ArrayList<String> operatorsHardList = new ArrayList<>();
        Random random = new Random();

        operatorsEasyList.add(context.getResources().getString(R.string.addition));
        operatorsEasyList.add(context.getResources().getString(R.string.subtraction));


        operatorsNormalList.add(context.getResources().getString(R.string.addition));
        operatorsNormalList.add(context.getResources().getString(R.string.subtraction));
        operatorsNormalList.add(context.getResources().getString(R.string.multiplication));

        operatorsHardList.add(context.getResources().getString(R.string.addition));
        operatorsHardList.add(context.getResources().getString(R.string.subtraction));
        operatorsHardList.add(context.getResources().getString(R.string.multiplication));
        operatorsHardList.add(context.getResources().getString(R.string.division));

        levelList.add(context.getResources().getString(R.string.easy));
        levelList.add(context.getResources().getString(R.string.normal));
        levelList.add(context.getResources().getString(R.string.hard));

        String currentOperator;
        int randomX;
        int randomY;
        int randomUnknownPosition;
        int result = 0;
        ArrayList<Integer> listWrongResult = new ArrayList<>(3);
        ArrayList<Integer> listWrongX = new ArrayList<>(3);
        ArrayList<Integer> listWrongY = new ArrayList<>(3);
        if (currentScore <= 10) {
            randomX = random.nextInt(10) + 1;
            randomY = random.nextInt(10) + 1;
            randomUnknownPosition = random.nextInt(3) + 1;
            currentOperator = operatorsEasyList.get(random.nextInt(operatorsEasyList.size()));
            switch (currentOperator) {
                case "+":
                    result = randomX + randomY;
                    break;
                case "-":
                    result = randomX - randomY;
                    break;
            }
            for (int i = 0; i < 10; i++) {
                if (listWrongResult.size() < 3) {
                    int randomWrongEasyResult = random.nextInt(10) + 1;
                    if (randomWrongEasyResult != result && !listWrongResult.contains(randomWrongEasyResult)) {
                        listWrongResult.add(randomWrongEasyResult);
                    }
                }
                if (listWrongX.size() < 3) {
                    int randomWrongX = random.nextInt(10) + 1;
                    if (randomWrongX != randomX && !listWrongX.contains(randomWrongX)) {
                        listWrongX.add(randomWrongX);
                    }
                }

                if (listWrongY.size() < 3) {
                    int randomWrongY = random.nextInt(10) + 1;
                    if (randomWrongY != randomY && !listWrongY.contains(randomWrongY)) {
                        listWrongY.add(randomWrongY);
                    }
                }
            }
            QuickMathematics quickMathematics = new QuickMathematics(randomX, randomY,
                    currentOperator, result,listWrongX,listWrongY, listWrongResult, randomUnknownPosition);
            quickMathematicsList.add(quickMathematics);

        }

        if (currentScore > 10 && currentScore <= 30) {
            randomX = random.nextInt(30) + 1;
            randomY = random.nextInt(30) + 1;
            randomUnknownPosition = random.nextInt(3) + 1;
            currentOperator = operatorsNormalList.get(random.nextInt(operatorsNormalList.size()));
            switch (currentOperator) {
                case "+":
                    result = randomX + randomY;
                    break;
                case "-":
                    result = randomX - randomY;
                    break;
                case "*":
                    result = randomX * randomY;
                    break;
            }
            for (int i = 0; i < 10; i++) {
                if(listWrongResult.size() <3)
                {
                    int randomWrongEasyResult = random.nextInt(50) + 1;
                    if (randomWrongEasyResult != result && !listWrongResult.contains(randomWrongEasyResult)) {
                        listWrongResult.add(randomWrongEasyResult);
                    }
                }


                if (listWrongX.size() < 3) {
                    int randomWrongX = random.nextInt(50) + 1;
                    if (randomWrongX != randomX && !listWrongX.contains(randomWrongX)) {
                        listWrongX.add(randomWrongX);
                    }
                }

                if (listWrongY.size() < 3) {
                    int randomWrongY = random.nextInt(50) + 1;
                    if (randomWrongY != randomY && !listWrongY.contains(randomWrongY)) {
                        listWrongY.add(randomWrongY);
                    }
                }

            }
            QuickMathematics quickMathematics = new QuickMathematics(randomX, randomY,
                    currentOperator, result,listWrongX,listWrongY, listWrongResult, randomUnknownPosition);

            quickMathematicsList.add(quickMathematics);
        }


        if (currentScore > 30) {
            randomX = random.nextInt(99) + 1;
            randomY = random.nextInt(99) + 1;
            randomUnknownPosition = random.nextInt(3) + 1;
            currentOperator = operatorsHardList.get(random.nextInt(operatorsHardList.size()));
            if (currentOperator.equals("/")) {
                if (randomX % randomY != 0) {
                    while (randomX % randomY == 0) {
                        randomX = random.nextInt(99) + 1;
                        randomY = random.nextInt(99) + 1;
                    }
                }
            }
            switch (currentOperator) {
                case "+":
                    result = randomX + randomY;
                    break;
                case "-":
                    result = randomX - randomY;
                    break;
                case "*":
                    result = randomX * randomY;
                    break;
                case "/":
                    result = randomX / randomY;
                    break;
            }
            for (int i = 0; i < 10; i++) {
                if(listWrongResult.size() <3)
                {
                    int randomWrongEasyResult = random.nextInt(99) + 1;
                    if (randomWrongEasyResult != result && !listWrongResult.contains(randomWrongEasyResult)) {
                        listWrongResult.add(randomWrongEasyResult);
                    }
                }


                if (listWrongX.size() < 3) {
                    int randomWrongX = random.nextInt(99) + 1;
                    if (randomWrongX != randomX && !listWrongX.contains(randomWrongX)) {
                        listWrongX.add(randomWrongX);
                    }
                }

                if (listWrongY.size() < 3) {
                    int randomWrongY = random.nextInt(99) + 1;
                    if (randomWrongY != randomY && !listWrongY.contains(randomWrongY)) {
                        listWrongY.add(randomWrongY);
                    }
                }

            }
            QuickMathematics quickMathematics = new QuickMathematics(randomX, randomY,
                    currentOperator, result,listWrongX,listWrongY, listWrongResult, randomUnknownPosition);
            quickMathematicsList.add(quickMathematics);


        }
        return quickMathematicsList;
    }

    public static QuickMathematics getQuestionMathematics(Context context) {
        List<QuickMathematics> quickMathematicsList = ModelQuickMathematics.randomQuickMathematics(context, 5);
        int index = new Random().nextInt(quickMathematicsList.size());
        return quickMathematicsList.get(index);
    }
}