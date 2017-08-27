package minh095.braintraining.activity.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import minh095.braintraining.R;


public class BaseActivity extends AppCompatActivity {

    Unbinder unbinder;

    @BindView(R.id.toolBar)
    Toolbar toolBar;

    public Toolbar getToolBar()
    {
        setSupportActionBar(toolBar);
        return toolBar;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
        setTitle(getString(R.string.app_name));


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
