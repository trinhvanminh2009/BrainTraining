package minh095.braintraining.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minh095.braintraining.R;
import minh095.braintraining.model.pojo.QuickMathematic;
import minh095.braintraining.model.pojo.TrueFalse;

/**
 * Created by trinh on 8/31/2017.
 */

public class ModelQuickMathematic {
    public static List<QuickMathematic>randomQuickMathematic(Context context){
        List<QuickMathematic>quickMathematicsList = new ArrayList<>();
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
        return quickMathematicsList;
    }
}
