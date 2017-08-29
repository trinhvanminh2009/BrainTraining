package minh095.braintraining.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minh095.braintraining.R;
import minh095.braintraining.model.pojo.TrueFalse;

/**
 * Created by trinh on 8/28/2017.
 */

public class ModelTrueFalse {

    private Context context;
    public ModelTrueFalse(Context context){
        //Add context to get String from string.xml
        this.context = context;
    }

    public List<TrueFalse> randomTrueFalse(int currentSore){
        ArrayList<String>levelList = new ArrayList<>();
        ArrayList<String>operatorsEasyList = new ArrayList<>();
        ArrayList<String>operatorsNormalList = new ArrayList<>();
        ArrayList<String>operatorsHardList = new ArrayList<>();
        List<TrueFalse>trueFalseList = new ArrayList<>();
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
        if(currentSore <= 10)
        {

            while (trueFalseList.size() < 200)
            {
                int randomEasyX = random.nextInt(10)+1;
                int randomEasyY = random.nextInt(10)+1;
                int randomEasyResult = random.nextInt(10)+1;
                currentOperator = operatorsEasyList.get(random.nextInt(operatorsEasyList.size()));
                TrueFalse trueFalseEasy = new TrueFalse(randomEasyX,randomEasyY, currentOperator,randomEasyResult,levelList.get(0));
                switch (currentOperator)
                {
                    case "+":
                        if(trueFalseEasy.getNumberX() + trueFalseEasy.getNumberY() == trueFalseEasy.getResult())
                        {
                            trueFalseEasy.setTrueOrFalse(true);
                        }
                        else{
                            trueFalseEasy.setTrueOrFalse(false);
                        }
                        break;
                    case "-":
                        if(trueFalseEasy.getNumberX() - trueFalseEasy.getNumberY() == trueFalseEasy.getResult())
                        {
                            trueFalseEasy.setTrueOrFalse(true);
                        }
                        else{
                            trueFalseEasy.setTrueOrFalse(false);
                        }
                        break;
                    default:
                        break;

                }
                trueFalseList.add(trueFalseEasy);
            }


        }
        if(currentSore >10 && currentSore <=30)
        {
            while (trueFalseList.size() < 3000)
            {
                int randomNormalX = random.nextInt(20)+1;
                int randomNormalY = random.nextInt(20)+1;
                int randomNormalResult = random.nextInt(40)+1;
                currentOperator = operatorsNormalList.get(random.nextInt(operatorsNormalList.size()));
                TrueFalse trueFalseNormal = new TrueFalse(randomNormalX,randomNormalY, currentOperator,randomNormalResult,levelList.get(1));
                switch (currentOperator)
                {
                    case "+":
                        if(trueFalseNormal.getNumberX() + trueFalseNormal.getNumberY() == trueFalseNormal.getResult())
                        {
                            trueFalseNormal.setTrueOrFalse(true);
                        }
                        else{
                            trueFalseNormal.setTrueOrFalse(false);
                        }
                        break;
                    case "-":
                        if(trueFalseNormal.getNumberX() - trueFalseNormal.getNumberY() == trueFalseNormal.getResult())
                        {
                            trueFalseNormal.setTrueOrFalse(true);
                        }
                        else{
                            trueFalseNormal.setTrueOrFalse(false);
                        }
                        break;
                    case "*":
                        if(trueFalseNormal.getNumberX() * trueFalseNormal.getNumberY() == trueFalseNormal.getResult())
                        {
                            trueFalseNormal.setTrueOrFalse(true);
                        }
                        else{
                            trueFalseNormal.setTrueOrFalse(false);
                        }
                        break;
                    default:
                        break;
                }

                trueFalseList.add(trueFalseNormal);
            }

        }

        if(currentSore >30)
        {
            while (trueFalseList.size() < 4000)
            {
                int randomHardX = random.nextInt(30)+1;
                int randomHardY = random.nextInt(30)+1;
                int randomHardResult = random.nextInt(50)+1;
                currentOperator = operatorsHardList.get(random.nextInt(operatorsHardList.size()));
                //Check X able to division for Y
                if(currentOperator.equals("/"))
                {
                    if(randomHardX % randomHardY != 0)
                    {
                        while (randomHardX % randomHardY == 0)
                        {
                             randomHardX = random.nextInt(50)+1;
                             randomHardY = random.nextInt(50)+1;
                             randomHardResult = random.nextInt(70)+1;
                        }
                    }
                }
                TrueFalse trueFalseHard = new TrueFalse(randomHardX,randomHardY, currentOperator,randomHardResult,levelList.get(2));
                switch (currentOperator)
                {
                    case "+":
                        if(trueFalseHard.getNumberX() + trueFalseHard.getNumberY() == trueFalseHard.getResult())
                        {
                            trueFalseHard.setTrueOrFalse(true);
                        }
                        else{
                            trueFalseHard.setTrueOrFalse(false);
                        }
                        break;
                    case "-":
                        if(trueFalseHard.getNumberX() - trueFalseHard.getNumberY() == trueFalseHard.getResult())
                        {
                            trueFalseHard.setTrueOrFalse(true);
                        }
                        else{
                            trueFalseHard.setTrueOrFalse(false);
                        }
                        break;
                    case "*":
                        if(trueFalseHard.getNumberX() * trueFalseHard.getNumberY() == trueFalseHard.getResult())
                        {
                            trueFalseHard.setTrueOrFalse(true);
                        }
                        else{
                            trueFalseHard.setTrueOrFalse(false);
                        }
                        break;
                    case "/":

                        if(trueFalseHard.getNumberX() / trueFalseHard.getNumberY() == trueFalseHard.getResult() &&
                                trueFalseHard.getNumberX() % trueFalseHard.getNumberY() == 0)
                        {
                            trueFalseHard.setTrueOrFalse(true);
                        }
                        else{
                            trueFalseHard.setTrueOrFalse(false);
                        }
                        break;
                }

                trueFalseList.add(trueFalseHard);
            }

        }
        trueFalseList = balanceTrueFalse(trueFalseList,currentSore);
        return trueFalseList;
    }

    private int countTrue(List<TrueFalse>trueFalseList)
    {
        int count = 0;
        for(int i = 0 ; i<trueFalseList.size() ; i++)
        {
            if(trueFalseList.get(i).isTrueOrFalse())
            {
                count++;
            }
        }
        return count;
    }

    private int countFalse(List<TrueFalse>trueFalseList)
    {
        int count = 0;
        for(int i = 0 ; i<trueFalseList.size() ; i++)
        {
            if(!trueFalseList.get(i).isTrueOrFalse())
            {
                count++;
            }
        }
        return count;
    }

    /**This function to make sure list true/false balance
     * */
    private List<TrueFalse> balanceTrueFalse(List<TrueFalse>trueFalseList, int currentScore)
    {

        List<TrueFalse>resultList = new ArrayList<>();
        int countTrue = 0;

        if(currentScore <= 10)
        {
            for(int i =0 ; i < trueFalseList.size(); i++)
            {
                if(countTrue < 5)
                {
                    if(trueFalseList.get(i).isTrueOrFalse())
                    {
                        resultList.add(trueFalseList.get(i));
                        countTrue++;
                    }
                }
                else if(countTrue == 5 ) {
                    resultList.add(trueFalseList.get(i));
                }
                if(resultList.size() == 10)
                {
                    break;
                }
            }
        }
        if(currentScore >10 && currentScore <= 30)
        {
            for(int i =0 ; i < trueFalseList.size(); i++)
            {
                if(countTrue < 10)
                {
                    if(trueFalseList.get(i).isTrueOrFalse())
                    {
                        resultList.add(trueFalseList.get(i));
                        countTrue++;
                    }
                }
                else if(countTrue == 10 ) {
                    resultList.add(trueFalseList.get(i));
                }
                if(resultList.size() == 20)
                {
                    break;
                }
            }
        }
        if(currentScore > 30)
        {
            for(int i =0 ; i < trueFalseList.size(); i++)
            {
                if(countTrue < 30)
                {
                    if(trueFalseList.get(i).isTrueOrFalse())
                    {
                        resultList.add(trueFalseList.get(i));
                        countTrue++;
                    }
                }
                else if(countTrue == 30 ) {
                    resultList.add(trueFalseList.get(i));
                }
                if(resultList.size() == 60)
                {
                    break;
                }
            }
        }
        return resultList;
    }
}
