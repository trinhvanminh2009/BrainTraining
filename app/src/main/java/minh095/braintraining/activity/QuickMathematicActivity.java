package minh095.braintraining.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivity;
import minh095.braintraining.activity.base.BaseActivityNoToolbar;

public class QuickMathematicActivity extends BaseActivityNoToolbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_mathematic);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
