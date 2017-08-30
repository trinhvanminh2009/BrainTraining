package minh095.braintraining.model.database;

import android.graphics.Bitmap;

import io.realm.RealmObject;

/**
 * Created by trinh on 8/30/2017.
 */

public class User extends RealmObject {
    private String userName;
    private String level;
    private int bestScoreTrueFalse;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getBestScoreTrueFalse() {
        return bestScoreTrueFalse;
    }

    public void setBestScoreTrueFalse(int bestScoreTrueFalse) {
        this.bestScoreTrueFalse = bestScoreTrueFalse;
    }
}
