package minh095.braintraining.activity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;

import butterknife.OnClick;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivityNoToolbar;

import java.util.List;

import minh095.braintraining.model.ModelTrueFalse;
import minh095.braintraining.model.pojo.TrueFalse;

public class TrueFalseActivity extends BaseActivityNoToolbar {

    @BindView(R.id.pb_loading)
    ProgressBar progressBar;
    @BindView(R.id.tvQuestion)
    TextView tvQuestion;

    private int currentCheck = -1;

    public static final int TIME_OF_GAME = 3 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);
        setProgressMax(100);
        handleRandom();
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
                    List<TrueFalse>trueFalseList ;
                    trueFalseList =  ModelTrueFalse.randomTrueFalse(30,getApplicationContext());
                    for(int i = 0; i < trueFalseList.size(); i++)
                    {
                        if(tvQuestion != null)
                        {
                            tvQuestion.setText(trueFalseList.get(i).getNumberX() + " " + trueFalseList.get(i).getOperator() + " " +
                                    trueFalseList.get(i).getNumberY() + " = " + trueFalseList.get(i).getResult() + " "+trueFalseList.get(i).isTrueOrFalse());
                            if(currentCheck != -1)
                            {
                                if(currentCheck == 0 &&  !trueFalseList.get(i).isTrueOrFalse())
                                {
                                    Log.e("0", " "+ !trueFalseList.get(i).isTrueOrFalse());
                                }
                                if(currentCheck == 0 &&  trueFalseList.get(i).isTrueOrFalse())
                                {
                                    Log.e("0", " "+ !trueFalseList.get(i).isTrueOrFalse());
                                }
                                if(currentCheck == 1 &&  trueFalseList.get(i).isTrueOrFalse() == false)
                                {
                                    Log.e("1", " "+ !trueFalseList.get(i).isTrueOrFalse());
                                }
                                if(currentCheck == 1 &&  trueFalseList.get(i).isTrueOrFalse() == true)
                                {
                                    Log.e("1", " "+ !trueFalseList.get(i).isTrueOrFalse());
                                }
                            }
                        }
                        currentCheck = -1;
                    }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void handleRandom()
    {

    }

    @OnClick({R.id.btnFalse, R.id.btnTrue})
    public void eventClick(View v) {
        switch (v.getId()) {
            case R.id.btnFalse:
                currentCheck = 0;
                break;
            case R.id.btnTrue:
                currentCheck =1;
                break;
        }
    }


}