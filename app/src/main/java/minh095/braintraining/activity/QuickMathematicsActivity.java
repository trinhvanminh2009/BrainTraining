package minh095.braintraining.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
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
import minh095.braintraining.model.ModelQuickMathematics;
import minh095.braintraining.model.pojo.QuickMathematics;

public class QuickMathematicsActivity extends BaseActivityNoToolbar implements Animator.AnimatorListener,
        CountDownAnimation.CountDownListener {

    /**
     * currentAnswer to know which value player clicked
     */
    public static final int TIME_OF_GAME = 10 * 1000;

    @BindView(R.id.progressTimer)
    ProgressBar progressTimer;

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;

    @BindView(R.id.tvScore)
    TextView tvScore;

    @BindView(R.id.btnAnswerOne)
    TextView btnAnswerOne;

    @BindView(R.id.btnAnswerTwo)
    TextView btnAnswerTwo;

    @BindView(R.id.btnAnswerThree)
    TextView btnAnswerThree;

    @BindView(R.id.btnAnswerFour)
    TextView btnAnswerFour;

    @BindView(R.id.tvCountDownStartGame)
    TextView tvCountDownStartGame;

    @BindView(R.id.layoutAnswer)
    LinearLayout layoutAnswer;

    private boolean isCorrect = false;
    private int finalScore = 0;
    private QuickMathematics currentQuestion;
    int randomPositionCorrectAnswer = 0;
    int currentAnswer = 0;
    private CountDownAnimation countDownAnimation;
    private ObjectAnimator animationProgressTimer;
    String question = "";
    private long currentPlayTime;
    private boolean checkPauseGame = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_mathematic);
        initCountDownAnimation();
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

    private void stopAnimation() {
        if (animationProgressTimer != null) {

            if(Build.VERSION.SDK_INT >= 19) {
                animationProgressTimer.pause();
            }
            else {
                currentPlayTime = animationProgressTimer.getCurrentPlayTime();
                isCorrect = true;
                checkPauseGame = true;
            }
        }
    }

    private void startAnimation() {
        if (animationProgressTimer != null) {
            if(Build.VERSION.SDK_INT >= 19) {
                animationProgressTimer.resume();
            }
            else {
                animationProgressTimer.start();
                animationProgressTimer.setCurrentPlayTime(currentPlayTime);
                isCorrect = false;

            }

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                if (animationProgressTimer != null) {
                    animationProgressTimer.cancel();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
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
    protected void onResume() {
        startAnimation();
        super.onResume();
    }

    @OnClick({R.id.btnAnswerOne, R.id.btnAnswerTwo, R.id.btnAnswerThree, R.id.btnAnswerFour})
    public void eventClick(View v) {

        switch (v.getId()) {
            case R.id.btnAnswerOne:
                currentAnswer = Integer.parseInt(btnAnswerOne.getText().toString());
                if (randomPositionCorrectAnswer == 1) {
                    setFullTimer();
                    finalScore++;
                    tvScore.setText(String.valueOf(finalScore));
                    isCorrect = true;

                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                        startProgressTimer(0);
                    }
                } else {
                    if (randomPositionCorrectAnswer == 2) {
                        btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_wrong);
                        btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_correct);
                    }
                    if (randomPositionCorrectAnswer == 3) {
                        btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_wrong);
                        btnAnswerThree.setBackgroundResource(R.drawable.background_button_check_correct);
                    }
                    if (randomPositionCorrectAnswer == 4) {
                        btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_wrong);
                        btnAnswerFour.setBackgroundResource(R.drawable.background_button_check_correct);

                    }
                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                    }
                    showDialogResultGame();
                }
                break;
            case R.id.btnAnswerTwo:
                currentAnswer = Integer.parseInt(btnAnswerTwo.getText().toString());
                if (randomPositionCorrectAnswer == 2) {
                    setFullTimer();
                    finalScore++;
                    tvScore.setText(String.valueOf(finalScore));
                    isCorrect = true;

                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                        startProgressTimer(0);
                    }
                } else {
                    if (randomPositionCorrectAnswer == 1) {
                        btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_correct);
                        btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_wrong);
                    }
                    if (randomPositionCorrectAnswer == 3) {
                        btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_wrong);
                        btnAnswerThree.setBackgroundResource(R.drawable.background_button_check_correct);
                    }
                    if (randomPositionCorrectAnswer == 4) {
                        btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_wrong);
                        btnAnswerFour.setBackgroundResource(R.drawable.background_button_check_correct);
                    }
                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                    }
                    showDialogResultGame();
                }
                break;
            case R.id.btnAnswerThree:
                currentAnswer = Integer.parseInt(btnAnswerThree.getText().toString());
                if (randomPositionCorrectAnswer == 3) {
                    setFullTimer();
                    finalScore++;
                    tvScore.setText(String.valueOf(finalScore));
                    isCorrect = true;

                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                        startProgressTimer(0);
                    }
                } else {
                    if (randomPositionCorrectAnswer == 1) {
                        btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_correct);
                        btnAnswerThree.setBackgroundResource(R.drawable.background_button_check_wrong);
                    }
                    if (randomPositionCorrectAnswer == 2) {
                        btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_correct);
                        btnAnswerThree.setBackgroundResource(R.drawable.background_button_check_wrong);
                    }
                    if (randomPositionCorrectAnswer == 4) {
                        btnAnswerThree.setBackgroundResource(R.drawable.background_button_check_wrong);
                        btnAnswerFour.setBackgroundResource(R.drawable.background_button_check_correct);
                    }
                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                    }
                    showDialogResultGame();
                }
                break;
            case R.id.btnAnswerFour:
                currentAnswer = Integer.parseInt(btnAnswerFour.getText().toString());
                if (randomPositionCorrectAnswer == 4) {
                    setFullTimer();
                    finalScore++;
                    tvScore.setText(String.valueOf(finalScore));
                    isCorrect = true;

                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                        startProgressTimer(0);
                    }
                } else {
                    if (randomPositionCorrectAnswer == 1) {
                        btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_correct);
                        btnAnswerFour.setBackgroundResource(R.drawable.background_button_check_wrong);
                    }
                    if (randomPositionCorrectAnswer == 2) {
                        btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_correct);
                        btnAnswerFour.setBackgroundResource(R.drawable.background_button_check_wrong);
                    }
                    if (randomPositionCorrectAnswer == 3) {
                        btnAnswerThree.setBackgroundResource(R.drawable.background_button_check_correct);
                        btnAnswerFour.setBackgroundResource(R.drawable.background_button_check_wrong);
                    }
                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                    }
                    showDialogResultGame();
                }
                break;
        }
    }



    android.support.v7.app.AlertDialog alertDialogResultGame;
    TextView tvWrongAnswer;
    TextView tvCorrectAnswer;
    TextView tvCurrentScore;
    TextView tvBestScore;
    TextView tvQuestionDialog;

    public void showDialogResultGame() {
        android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_result_game, null);
        dialogBuilder.setView(dialogView);
        if (alertDialogResultGame == null) {
            tvWrongAnswer = (TextView) dialogView.findViewById(R.id.tvWrongAnswer);
            tvCorrectAnswer = (TextView) dialogView.findViewById(R.id.tvCorrectAnswer);
            tvCurrentScore = (TextView) dialogView.findViewById(R.id.tvScore);
            tvBestScore = (TextView) dialogView.findViewById(R.id.tvBestScore);
            tvQuestionDialog = (TextView) dialogView.findViewById(R.id.tvQuestion);
        }
        switch (currentQuestion.getUnknownPosition()) {
            case 0:
                tvWrongAnswer.setText(this.getResources().getText(R.string.your_answer));
                break;
            case 1:
                tvWrongAnswer.setText(this.getResources().getText(R.string.your_answer)
                        + " "
                        + currentAnswer
                        + " "
                        + currentQuestion.getOperator()
                        + " "
                        + currentQuestion.getNumberY()
                        + " = "
                        + currentQuestion.getResult());
                break;
            case 2:
                tvWrongAnswer.setText(this.getResources().getText(R.string.your_answer)
                        + " "
                        + currentQuestion.getNumberX()
                        + " "
                        + currentQuestion.getOperator()
                        + " "
                        + currentAnswer
                        + " = "
                        + currentQuestion.getResult());
                break;
            case 3:
                tvWrongAnswer.setText(this.getResources().getText(R.string.your_answer)
                        + " "
                        + currentQuestion.getNumberX()
                        + " "
                        + currentQuestion.getOperator()
                        + " "
                        + currentQuestion.getNumberX()
                        + " = "
                        + currentAnswer);
                break;
        }


        tvCorrectAnswer.setText(this.getResources().getText(R.string.correct_answer)
                + " "
                + currentQuestion.getNumberX()
                + " "
                + currentQuestion.getOperator()
                + " "
                + currentQuestion.getNumberY()
                + " = "
                + currentQuestion.getResult());

        String currentQuestion = getString(R.string.question) + question;
        tvCurrentScore.setText(String.valueOf(finalScore));
        tvQuestionDialog.setText(currentQuestion);
        if (alertDialogResultGame == null) {
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
                            btnAnswerOne.setBackgroundResource(R.drawable.background_button_answer);
                            btnAnswerTwo.setBackgroundResource(R.drawable.background_button_answer);
                            btnAnswerThree.setBackgroundResource(R.drawable.background_button_answer);
                            btnAnswerFour.setBackgroundResource(R.drawable.background_button_answer);
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
        alertDialogResultGame.show();
        //Prevent user using back button of android devices to play game again
        alertDialogResultGame.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK)
                {
                    alertDialogResultGame.dismiss();
                    finish();
                }
                return true;
            }
        });


    }


    @Override
    public void onAnimationStart(Animator animation) {
        if (!checkPauseGame) {
            currentQuestion = ModelQuickMathematics.getQuestionMathematics(getApplicationContext());
            Random random = new Random();
            randomPositionCorrectAnswer = random.nextInt(4) + 1;
            //Switch to set text for question
            switch (currentQuestion.getUnknownPosition()) {
                case 1:
                    question = "?"
                            + " "
                            + currentQuestion.getOperator()
                            + " "
                            + currentQuestion.getNumberY()
                            + " = "
                            + currentQuestion.getResult();
                    switch (randomPositionCorrectAnswer) {
                        case 1:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getNumberX()));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getListWrongResult().get(2)));
                            break;
                        case 2:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getNumberX()));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getListWrongResult().get(2)));
                            break;
                        case 3:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getNumberX()));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getListWrongResult().get(2)));
                            break;
                        case 4:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getNumberX()));
                            break;
                    }
                    break;
                case 2:
                    question = currentQuestion.getNumberX()
                            + " "
                            + currentQuestion.getOperator()
                            + " "
                            + "?"
                            + " = "
                            + currentQuestion.getResult();
                    switch (randomPositionCorrectAnswer) {
                        case 1:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getNumberY()));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getListWrongResult().get(2)));
                            break;
                        case 2:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getNumberY()));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getListWrongResult().get(2)));
                            break;
                        case 3:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getNumberY()));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getListWrongResult().get(2)));
                            break;
                        case 4:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getNumberY()));
                            break;
                    }
                    break;
                case 3:
                    question = currentQuestion.getNumberX()
                            + " "
                            + currentQuestion.getOperator()
                            + " "
                            + currentQuestion.getNumberY()
                            + " = "
                            + "?";
                    //Switch to set text for buttons
                    switch (randomPositionCorrectAnswer) {
                        case 1:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getResult()));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getListWrongResult().get(2)));
                            break;
                        case 2:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getResult()));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getListWrongResult().get(2)));
                            break;
                        case 3:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getResult()));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getListWrongResult().get(2)));
                            break;
                        case 4:
                            btnAnswerOne.setText(String.valueOf(currentQuestion.getListWrongResult().get(0)));
                            btnAnswerTwo.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerThree.setText(String.valueOf(currentQuestion.getListWrongResult().get(1)));
                            btnAnswerFour.setText(String.valueOf(currentQuestion.getResult()));
                            break;
                    }
                    break;
            }
            tvQuestion.setText(question);
            isCorrect = false;
        }
        checkPauseGame = false;

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