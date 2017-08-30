package minh095.braintraining.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.Tag;
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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;

import butterknife.BindView;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivityNoToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import minh095.braintraining.model.ModelTrueFalse;
import minh095.braintraining.model.pojo.TrueFalse;

public class TrueFalseActivity extends BaseActivityNoToolbar {

    @BindView(R.id.pb_loading)
    ProgressBar progressBar;

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;

    @BindView(R.id.tvScore)
    TextView tvScore;

    private boolean currentCheck = false;
    private int score = 0;
    public static final int TIME_OF_GAME = 6 * 1000;

    Random random = new Random();
    List<TrueFalse> trueFalseList = new ArrayList<>();
    int currentIndex = 0;


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

    private void startProgressAnimate(final int progressTo) {
        if (progressBar != null) {

            ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar.getProgress(), progressTo * 100);
            animation.setDuration(TIME_OF_GAME);
            animation.setInterpolator(new DecelerateInterpolator());

            animation.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                    trueFalseList = ModelTrueFalse.randomTrueFalse(5, getApplicationContext());
                    int index = random.nextInt(trueFalseList.size());
                    currentIndex = index;
                    tvQuestion.setText(trueFalseList.get(index).getNumberX() + " " +
                            trueFalseList.get(index).getOperator() + " " +
                            trueFalseList.get(index).getNumberY() + " = " +
                            trueFalseList.get(index).getResult());
                    currentCheck = false;
                }

                @Override
                public void onAnimationEnd(Animator animation) {

                    if (!currentCheck) {
                        showDialogResultGame();
                    }
                    Log.e("Show", " " + currentCheck);

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


    @OnClick({R.id.btnFalse, R.id.btnTrue})
    public void eventClick(View v) {


        switch (v.getId()) {
            case R.id.btnFalse:
                if (trueFalseList.get(currentIndex).isTrueOrFalse() == false) {
                    setProgressMax(100);
                    startProgressAnimate(0);
                    Log.e("false", "false");
                    currentCheck = true;
                    score++;
                    tvScore.setText(" " + score);
                    break;
                }
                if (trueFalseList.get(currentIndex).isTrueOrFalse() == true) {
                    Log.e("false", "true");
                    showDialogResultGame();
                    Log.e("Show", "Show in false");
                    currentCheck = false;
                    break;
                }
                break;
            case R.id.btnTrue:
                if (trueFalseList.get(currentIndex).isTrueOrFalse() == true) {
                    setProgressMax(100);
                    startProgressAnimate(0);
                    currentCheck = true;
                    score++;
                    Log.e("Show", "Show in true");
                    tvScore.setText(" " + score);
                    break;
                }
                if (trueFalseList.get(currentIndex).isTrueOrFalse() == false) {
                    showDialogResultGame();
                    Log.e("Show", "Show in true");
                    currentCheck = false;
                    break;
                }
                break;
        }
    }

    android.support.v7.app.AlertDialog alertDialogResultGame;

    public void showDialogResultGame() {
        android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_result_game, null);
        dialogBuilder.setView(dialogView);
        TextView tvWrongAnswer = (TextView) dialogView.findViewById(R.id.tvWrongAnswer);
        TextView tvCorrectAnswer = (TextView) dialogView.findViewById(R.id.tvCorrectAnswer);
        TextView tvCurrentScore = (TextView) dialogView.findViewById(R.id.tvCurrentScore);
        TextView tvBestScore = (TextView) dialogView.findViewById(R.id.tvBestScore);
        int result = 0;
        switch (trueFalseList.get(currentIndex).getOperator()) {
            case "+":
                result = trueFalseList.get(currentIndex).getNumberX() + trueFalseList.get(currentIndex).getNumberY();
                break;
            case "-":
                result = trueFalseList.get(currentIndex).getNumberX() - trueFalseList.get(currentIndex).getNumberY();
                break;
            case "*":
                result = trueFalseList.get(currentIndex).getNumberX() * trueFalseList.get(currentIndex).getNumberY();
                break;
            case "/":
                result = trueFalseList.get(currentIndex).getNumberX() / trueFalseList.get(currentIndex).getNumberY();
                break;
        }
        tvWrongAnswer.setText(this.getResources().getText(R.string.your_answer) +
                " " + trueFalseList.get(currentIndex).getNumberX() + " " +
                trueFalseList.get(currentIndex).getOperator() + " " +
                trueFalseList.get(currentIndex).getNumberY() + " = " +
                trueFalseList.get(currentIndex).getResult());

        tvCorrectAnswer.setText(this.getResources().getText(R.string.correct_answer)
                + " " + trueFalseList.get(currentIndex).getNumberX() + " " +
                trueFalseList.get(currentIndex).getOperator() + " " +
                trueFalseList.get(currentIndex).getNumberY() + " = " + result
        );

        tvCurrentScore.setText(" " + score);

        if (alertDialogResultGame == null) {
            dialogView.findViewById(R.id.btnGoToMenu).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    alertDialogResultGame.dismiss();
                }
            });
            dialogView.findViewById(R.id.btnRestartGame).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    alertDialogResultGame.dismiss();
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
}