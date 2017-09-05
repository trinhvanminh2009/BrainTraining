package minh095.braintraining.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivityWithToolbar;
import minh095.braintraining.model.database.Game;
import minh095.braintraining.model.database.User;

public class MainActivity extends BaseActivityWithToolbar {

    @BindView(R.id.drawer_main)
    DrawerLayout drawerMain;

    @BindView(R.id.navigation_main)
    NavigationView navigationView;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_name));
        initRealm();
        setUpDrawer();
        createNewDataForUser();

    }

    //Init database of realm object
    private void initRealm()
    {
        Realm.init(this);
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    private void createNewDataForUser()
    {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if(realm.where(User.class).count() == 0)
                {
                    User currentUser = realm.createObject(User.class);
                    currentUser.setUserName("Student");
                    Game trueFalseGame = realm.createObject(Game.class,"trueFalseGame");
                    Game quickMathematicsGame = realm.createObject(Game.class,"quickMathematicsGame");
                    trueFalseGame.setBestScore(0);
                    quickMathematicsGame.setBestScore(0);
                    RealmList<Game>gameRealmList = new RealmList<>();
                    gameRealmList.add(trueFalseGame);
                    gameRealmList.add(quickMathematicsGame);
                    currentUser.setGameList(gameRealmList);
                }
            }
        });

    }

    @OnClick({R.id.btnGameFreakingMath, R.id.btnGameTrueFalse})
    public void eventClick(View v) {
        Intent nextIntent = null;
        switch (v.getId()) {

            case R.id.btnGameFreakingMath:
                nextIntent = new Intent(this, QuickMathematicsActivity.class);
                break;
            case R.id.btnGameTrueFalse:
                nextIntent = new Intent(this, TrueFalseActivity.class);
                break;
        }
        startActivity(nextIntent);
    }


    private void setUpDrawer() {
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerMain, getToolBar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerMain.addDrawerListener(toggle);
        toggle.syncState();

    }


}
