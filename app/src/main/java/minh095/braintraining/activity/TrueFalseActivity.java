package minh095.braintraining.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
<<<<<<< HEAD
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
=======
import android.os.Bundle;
>>>>>>> 3b57548c17be43319945d913515c693a2813ffa0
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivityNoToolbar;
import minh095.braintraining.model.ModelTrueFalse;
import minh095.braintraining.model.pojo.TrueFalse;

public class TrueFalseActivity extends BaseActivityNoToolbar implements Animator.AnimatorListener {

    @BindView(R.id.pb_loading)
    ProgressBar progressBar;

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;

    @BindView(R.id.tvScore)
    TextView tvScore;

<<<<<<< HEAD
    private boolean currentCheck = true;
=======
    private boolean isCorrect = false;
>>>>>>> 3b57548c17be43319945d913515c693a2813ffa0
    private int score = 0;
    public static final int TIME_OF_GAME = 6 * 1000;
    ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar.getProgress(), 0);
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

    ObjectAnimator animation;

    private void startProgressAnimate(final int progressTo) {
        if (progressBar != null) {
<<<<<<< HEAD
            currentCheck = false;

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


=======
            if (animation == null) {
                animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar.getProgress(), progressTo * 100);
                animation.setDuration(TIME_OF_GAME);
                animation.setInterpolator(new DecelerateInterpolator());
                animation.addListener(this);
            }
            animation.start();
>>>>>>> 3b57548c17be43319945d913515c693a2813ffa0
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
                if (!trueFalseList.get(currentIndex).isTrueOrFalse()) {
                    setProgressMax(100);
                    score++;
                    tvScore.setText(String.valueOf(score));
                    isCorrect = true;

                    if (animation != null) {
                        animation.cancel();
                        startProgressAnimate(0);
                    }
                } else {
                    if (animation != null) {
                        animation.cancel();
                    }
                    showDialogResultGame();
                }


                break;
            case R.id.btnTrue:
                if (trueFalseList.get(currentIndex).isTrueOrFalse()) {

                    setProgressMax(100);
                    score++;
                    tvScore.setText(String.valueOf(score));
                    isCorrect = true;

                    if (animation != null) {
                        animation.cancel();
                        startProgressAnimate(0);
                    }
                } else {
                    if (animation != null) {
                        animation.cancel();
                    }
                    showDialogResultGame();
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

        tvCurrentScore.setText(String.valueOf(score));

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

            //Make sure user do not play again and back to menu screen
            alertDialogResultGame.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if(keyCode == KeyEvent.KEYCODE_BACK)
                    {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        alertDialogResultGame.dismiss();
                    }
                    return true;
                }
            });
            alertDialogResultGame.setCanceledOnTouchOutside(false);
            if (alertDialogResultGame.getWindow() != null) {
                alertDialogResultGame.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
        }

        alertDialogResultGame.show();

    }


    @Override
    public void onAnimationStart(Animator animation) {

        trueFalseList = ModelTrueFalse.randomTrueFalse(5, getApplicationContext());
        int index = random.nextInt(trueFalseList.size());
        currentIndex = index;
        tvQuestion.setText(trueFalseList.get(index).getNumberX() + " " +
                trueFalseList.get(index).getOperator() + " " +
                trueFalseList.get(index).getNumberY() + " = " +
                trueFalseList.get(index).getResult());
        isCorrect = false;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (!isCorrect) {
            showDialogResultGame();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}