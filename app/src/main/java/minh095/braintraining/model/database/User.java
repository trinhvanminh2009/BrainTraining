package minh095.braintraining.model.database;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import minh095.braintraining.model.pojo.Game;

/**
 * Created by trinh on 8/30/2017.
 */

public class User extends RealmObject {
    @PrimaryKey
    private String userName;
    private RealmList<Game> gameList;

    public RealmList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(RealmList<Game> gameList) {
        this.gameList = gameList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
