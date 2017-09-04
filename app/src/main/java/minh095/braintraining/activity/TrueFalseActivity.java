package minh095.braintraining.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivityNoToolbar;
import minh095.braintraining.animation.CountDownAnimation;
import minh095.braintraining.model.ModelTrueFalse;
import minh095.braintraining.model.pojo.TrueFalse;

public class TrueFalseActivity extends BaseActivityNoToolbar implements Animator.AnimatorListener,
        CountDownAnimation.CountDownListener {

    public static final int TIME_OF_GAME = 3* 1000;

    @BindView(R.id.progressTimer)
    ProgressBar progressTimer;

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;

    @BindView(R.id.tvScore)
    TextView tvScore;

    @BindView(R.id.tvCountDownStartGame)
    TextView tvCountDownStartGame;

    @BindView(R.id.layoutAnswer)
    LinearLayout layoutAnswer;

    // isCorrect default = false
    // When start animation isCorrect = false
    // When click button False or True button - if user choose correct Answer -> isCorrect = true .
    // Event on end Animation
    //     +    if isCorrect = false -> show dialog result of game to User
    //     +    if isCorrect = true -> continue game.
    private boolean isCorrect = false;
    private TrueFalse currentQuestion;
    private long currentPlayTime;
    private ObjectAnimator animationProgressTimer;
    private boolean checkPauseGame = false;
    CountDownAnimation countDownAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);
        initCountDownAnimation();
    }

    private void stopAnimation() {
        if (animationProgressTimer != null) {

            if (Build.VERSION.SDK_INT >= 19) {
                animationProgressTimer.pause();
            } else {
                currentPlayTime = animationProgressTimer.getCurrentPlayTime();
                isCorrect = true;
                checkPauseGame = true;
            }
        }
    }

    private void startAnimation() {
        if (animationProgressTimer != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                animationProgressTimer.resume();
            } else {
                animationProgressTimer.start();
                animationProgressTimer.setCurrentPlayTime(currentPlayTime);
                isCorrect = false;
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        startAnimation();

    }

    private void initCountDownAnimation() {
        countDownAnimation = new CountDownAnimation(tvCountDownStartGame, 3);
        countDownAnimation.setCountDownListener(this);
        Animation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f,
                0.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        countDownAnimation.setAnimation(animationSet);
        // Customizable start count
        countDownAnimation.setStartCount(3);
        countDownAnimation.start();
    }

    private void setUpNewGame() {
        tvScore.setText(String.valueOf(0));
        setFullTimer();
        startProgressTimer(0);
    }

    private void setFullTimer() {
        if (progressTimer != null) {
            progressTimer.setMax(100 * 100);
            progressTimer.setProgress(100 * 100);
        }
    }


    private void startProgressTimer(final int progressTo) {
        if (progressTimer != null) {
            if (animationProgressTimer == null) {
                animationProgressTimer = ObjectAnimator.ofInt(progressTimer, "progress", progressTimer.getProgress(), progressTo * 100);
                animationProgressTimer.setDuration(TIME_OF_GAME);
                animationProgressTimer.setInterpolator(new DecelerateInterpolator());
                animationProgressTimer.addListener(this);
            }
            animationProgressTimer.start();
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

                if (!currentQuestion.isTrueOrFalse()) {
                    setFullTimer();
                    tvScore.setText(String.valueOf(Integer.parseInt(tvScore.getText().toString()) + 1));
                    isCorrect = true;

                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                        startProgressTimer(0);
                    }
                } else {
                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                    }
                    showDialogResultGame(currentQuestion);
                }
                break;
            case R.id.btnTrue:
                if (currentQuestion.isTrueOrFalse()) {
                    setFullTimer();
                    tvScore.setText(String.valueOf(Integer.parseInt(tvScore.getText().toString()) + 1));
                    isCorrect = true;

                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                        startProgressTimer(0);
                    }
                } else {
                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                    }
                    showDialogResultGame(currentQuestion);
                }
                break;
        }
    }

    private android.support.v7.app.AlertDialog alertDialogResultGame;
    private TextView tvQuestionInDialog;
    private TextView tvWrongAnswer;
    private TextView tvCorrectAnswer;
    private TextView tvScoreInDialog;
    private TextView tvBestScore;

    public void showDialogResultGame(TrueFalse currentQuestion) {
        android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_result_game, null);
        dialogBuilder.setView(dialogView);
        if (alertDialogResultGame == null) {
            tvQuestionInDialog = (TextView) dialogView.findViewById(R.id.tvQuestion);
            tvWrongAnswer = (TextView) dialogView.findViewById(R.id.tvWrongAnswer);
            tvCorrectAnswer = (TextView) dialogView.findViewById(R.id.tvCorrectAnswer);
            tvScoreInDialog = (TextView) dialogView.findViewById(R.id.tvScore);
            tvBestScore = (TextView) dialogView.findViewById(R.id.tvBestScore);
            dialogView.findViewById(R.id.btnGoToMenu).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            dialogView.findViewById(R.id.btnRestartGame).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (alertDialogResultGame != null) {
                        if (alertDialogResultGame.isShowing()) {
                            alertDialogResultGame.dismiss();
                        }
                    }
                    setUpNewGame();
                }
            });
            alertDialogResultGame = dialogBuilder.create();
            alertDialogResultGame.setCanceledOnTouchOutside(false);
            if (alertDialogResultGame.getWindow() != null) {
                alertDialogResultGame.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
        }
        String question = getString(R.string.question)
                + " "
                + currentQuestion.getNumberX()
                + " "
                + currentQuestion.getOperator()
                + " "
                + currentQuestion.getNumberY()
                + " = "
                + currentQuestion.getResult();
        String wrongAnswer = getString(R.string.your_answer) + !currentQuestion.isTrueOrFalse();
        String correctAnswer = getString(R.string.correct_answer) + currentQuestion.isTrueOrFalse();

        tvQuestionInDialog.setText(question);
        tvWrongAnswer.setText(wrongAnswer);
        tvCorrectAnswer.setText(correctAnswer);

        tvScoreInDialog.setText(tvScore.getText().toString());
        alertDialogResultGame.show();
    }

    @Override
    public void onPause() {
        if (alertDialogResultGame != null) {
            alertDialogResultGame.dismiss();
        }
        stopAnimation();
        super.onPause();

    }

    @Override
    public void onAnimationStart(Animator animation) {
        if (!checkPauseGame) {
            currentQuestion = ModelTrueFalse.getTrueFalseQuestion(getApplicationContext());
            String question = currentQuestion.getNumberX()
                    + " "
                    + currentQuestion.getOperator()
                    + " "
                    + currentQuestion.getNumberY()
                    + " = "
                    + currentQuestion.getResult();

            tvQuestion.setText(question);
            isCorrect = false;

        }
        checkPauseGame = false;

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (!isCorrect) {
            showDialogResultGame(currentQuestion);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void onCountDownEnd(CountDownAnimation animation) {
        if (tvCountDownStartGame != null && tvQuestion != null && layoutAnswer != null) {
            tvCountDownStartGame.setVisibility(View.GONE);
            tvQuestion.setVisibility(View.VISIBLE);
            layoutAnswer.setVisibility(View.VISIBLE);
        }
        setFullTimer();
        startProgressTimer(0);
    }
}