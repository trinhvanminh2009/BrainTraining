package minh095.braintraining.model;

import java.util.ArrayList;
import java.util.List;

import minh095.braintraining.model.pojo.Score;

/**
 * Created by ng.hoang.minh095gmail.com on 8/27/17.
 */

public class ModelScore {

    public static List<Score> getAllScore() {
        List<Score> listScore = new ArrayList<>();
        listScore.add(new Score(1));
        listScore.add(new Score(2));
        listScore.add(new Score(3));
        listScore.add(new Score(4));
        listScore.add(new Score(5));
        listScore.add(new Score(1));
        listScore.add(new Score(2));
        listScore.add(new Score(3));
        listScore.add(new Score(4));
        listScore.add(new Score(5));
        listScore.add(new Score(1));
        listScore.add(new Score(2));
        listScore.add(new Score(3));
        listScore.add(new Score(4));
        listScore.add(new Score(5));
        listScore.add(new Score(1));
        listScore.add(new Score(2));
        listScore.add(new Score(3));
        listScore.add(new Score(4));
        listScore.add(new Score(5));
        return listScore;
    }
}
