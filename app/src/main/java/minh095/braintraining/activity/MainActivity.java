package minh095.braintraining.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

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

    android.support.v7.app.AlertDialog alertDialogResultGame;

    public void showDialogResultGame() {
        android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_result_game, null);
        dialogBuilder.setView(dialogView);

        if (alertDialogResultGame == null) {
            dialogView.findViewById(R.id.btnGoToMenu).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Go menu", Toast.LENGTH_SHORT).show();
                }
            });
            dialogView.findViewById(R.id.btnRestartGame).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Restart game", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialogResultGame = dialogBuilder.create();
            alertDialogResultGame.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                }
            });
            alertDialogResultGame.setCanceledOnTouchOutside(false);
            if (alertDialogResultGame.getWindow() != null) {
                alertDialogResultGame.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
        }

        alertDialogResultGame.show();
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
