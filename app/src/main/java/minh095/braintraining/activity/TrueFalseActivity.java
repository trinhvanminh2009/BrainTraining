package minh095.braintraining.activity;

import android.view.MenuItem;

import butterknife.OnClick;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivityNoToolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivity;
import minh095.braintraining.model.ModelTrueFalse;
import minh095.braintraining.model.pojo.TrueFalse;

public class TrueFalseActivity extends BaseActivityNoToolbar {

    @BindView(R.id.pb_loading)
    ProgressBar progressBar;


    public static final int TIME_OF_GAME = 6 * 1000;

   /* @BindView(R.id.btnTest)
    Button btnTest;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);
        setProgressMax(100);
        startProgressAnimate(0);

    }

    private void setProgressMax(int max) {
        if (progressBar != null) {
            progressBar.setMax(max * 100);
            progressBar.setProgress(max * 100);
        }
    }

    private void startProgressAnimate(int progressTo) {
        if(progressBar!=null) {
            ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar.getProgress(), progressTo * 100);
            animation.setDuration(TIME_OF_GAME);
            animation.setInterpolator(new DecelerateInterpolator());
            animation.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    setProgressMax(100);
                    startProgressAnimate(0);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animation.start();
        }

    }
/*
    @OnClick(R.id.btnTest)
    public void Test()
    {
        ModelTrueFalse modelTrueFalse = new ModelTrueFalse(this);
        List<TrueFalse> trueFalseList;
        trueFalseList = modelTrueFalse.RandomTrueFalse(44);
        for(int i = 0 ; i < trueFalseList.size(); i++)
        {
            Toast.makeText(this, trueFalseList.get(i).getNumberX() + " "+trueFalseList.get(i).getOperator() + " "+ trueFalseList.get(i).getNumberY() +" = " +
                    trueFalseList.get(i).getResult() + " " + trueFalseList.get(i).isTrueOrFalse()  + " Size "+ trueFalseList.size(), Toast.LENGTH_SHORT).show();
        }



    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick({R.id.btnFalse, R.id.btnTrue})
    public void eventClick(View v) {
        switch (v.getId()) {

            case R.id.btnFalse:
                Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnTrue:
                Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
