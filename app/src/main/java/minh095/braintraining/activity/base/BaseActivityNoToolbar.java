package minh095.braintraining.activity.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import minh095.braintraining.R;


public class BaseActivityNoToolbar extends BaseActivity {



    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
