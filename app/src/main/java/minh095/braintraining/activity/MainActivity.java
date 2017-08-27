package minh095.braintraining.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import butterknife.BindView;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer_main)
    DrawerLayout drawerMain;


    @BindView(R.id.navigation_main)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Brain training");
        setUpDrawer();



    }

    private void setUpDrawer() {
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerMain, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerMain.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                             @Override
                                                             public boolean onNavigationItemSelected(MenuItem item) {
                                                                 switch (item.getItemId()) {

                                                                 }
                                                                 return true;
                                                             }
                                                         }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
