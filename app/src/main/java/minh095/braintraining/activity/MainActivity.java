package minh095.braintraining.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivityWithToolbar;

public class MainActivity extends BaseActivityWithToolbar {

    @BindView(R.id.drawer_main)
    DrawerLayout drawerMain;

    @BindView(R.id.navigation_main)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_name));
        setUpDrawer();
    }

    @OnClick({R.id.btnGameFreakingMath, R.id.btnGameTrueFalse})
    public void eventClick(View v) {
        Intent nextIntent = null;
        switch (v.getId()) {

            case R.id.btnGameFreakingMath:
                nextIntent = new Intent(this, QuickMathematicActivity.class);
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
/*        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                             @Override
                                                             public boolean onNavigationItemSelected(MenuItem item) {
                                                                 switch (item.getItemId()) {

                                                                 }
                                                                 return true;
                                                             }
                                                         }
        );*/
    }


}
