package minh095.braintraining.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivityNoToolbar;
import minh095.braintraining.animation.CountDownAnimation;
import minh095.braintraining.model.ModelBalanceQuestion;
import minh095.braintraining.model.ModelTrueFalse;
import minh095.braintraining.model.database.Game;
import minh095.braintraining.model.pojo.BalanceQuestion;
import minh095.braintraining.model.pojo.TrueFalseQuestion;

public class BalanceActivity extends BaseActivityNoToolbar implements Animator.AnimatorListener,
        CountDownAnimation.CountDownListener {

    public static final int TIME_OF_GAME = 6 * 1000;

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

    @BindView(R.id.btnAnswerOne)
    TextView btnAnswerOne;

    @BindView(R.id.btnAnswerTwo)
    TextView btnAnswerTwo;

    private long currentPlayTime;
    private ObjectAnimator animationProgressTimer;
    private boolean checkPauseGame = false;
    CountDownAnimation countDownAnimation;
    private boolean checkDialogResultGameIsShowing = false;
    private Realm realm;
    private BalanceQuestion currentQuestion;
    private boolean isCorrect = false;
    int randomPositionCorrectAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        initRealm();
        initCountDownAnimation();
    }

    //Init database of realm object
    private void initRealm() {
        Realm.init(this);
        realm = null;
        realm = Realm.getDefaultInstance();
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
    public void onPause() {
        if (alertDialogResultGame != null) {
            if (!checkDialogResultGameIsShowing) {
                alertDialogResultGame.dismiss();
            }
        }
        stopAnimation();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAnimation();

    }

    @Override
    protected void onDestroy() {
        if (alertDialogResultGame != null && alertDialogResultGame.isShowing()) {
            alertDialogResultGame.cancel();
        }
        super.onDestroy();
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
    public void onAnimationStart(Animator animation) {
        if (!checkPauseGame) {
            Random random = new Random();
            randomPositionCorrectAnswer = random.nextInt(2) + 1;
            currentQuestion = ModelBalanceQuestion.getBalanceQuestion(getApplicationContext());
            String question = currentQuestion.getNumberX()
                    + " ? "
                    + currentQuestion.getNumberY();

            switch (randomPositionCorrectAnswer) {
                case 1:
                    btnAnswerOne.setText(currentQuestion.getCorrectAnswer());
                    btnAnswerTwo.setText(currentQuestion.getWrongAnswer());
                    break;
                case 2:
                    btnAnswerOne.setText(currentQuestion.getWrongAnswer());
                    btnAnswerTwo.setText(currentQuestion.getCorrectAnswer());
                    break;
            }

            tvQuestion.setText(question);
            isCorrect = false;
        }
        checkPauseGame = false;
        checkDialogResultGameIsShowing = false;
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

    @OnClick({R.id.btnAnswerOne, R.id.btnAnswerTwo})
    public void eventClick(View v) {
        switch (v.getId()) {
            case R.id.btnAnswerOne:
                if (randomPositionCorrectAnswer == 1) {
                    setFullTimer();
                    tvScore.setText(String.valueOf(Integer.parseInt(tvScore.getText().toString()) + 1));
                    isCorrect = true;

                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                        startProgressTimer(0);
                    }
                } else {
                    btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_correct);
                    btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_wrong);
                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                    }
                    showDialogResultGame(currentQuestion);
                }
                break;
            case R.id.btnAnswerTwo:
                if (randomPositionCorrectAnswer == 2) {
                    setFullTimer();
                    tvScore.setText(String.valueOf(Integer.parseInt(tvScore.getText().toString()) + 1));
                    isCorrect = true;

                    if (animationProgressTimer != null) {
                        animationProgressTimer.cancel();
                        startProgressTimer(0);
                    }
                } else {
                    btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_wrong);
                    btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_correct);
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

    public void showDialogResultGame(BalanceQuestion currentQuestion) {
        android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_result_true_false_game, null);
        dialogBuilder.setView(dialogView);
        if (alertDialogResultGame == null) {
            tvQuestionInDialog = (TextView) dialogView.findViewById(R.id.tvQuestion);
            tvWrongAnswer = (TextView) dialogView.findViewById(R.id.tvWrongAnswer);
            tvCorrectAnswer = (TextView) dialogView.findViewById(R.id.tvCorrectAnswer);
            tvScoreInDialog = (TextView) dialogView.findViewById(R.id.tvScore);
            tvBestScore = (TextView) dialogView.findViewById(R.id.tvBestScore);
        }
        String question = currentQuestion.getNumberX()
                + " ? "
                + currentQuestion.getNumberY();
        String wrongAnswer = getString(R.string.your_answer) + currentQuestion.getWrongAnswer();
        String correctAnswer = getString(R.string.correct_answer) + currentQuestion.getCorrectAnswer();
        String currentQuestionResult = getString(R.string.question) + question;
        tvQuestionInDialog.setText(currentQuestionResult);
        tvWrongAnswer.setText(wrongAnswer);
        tvCorrectAnswer.setText(correctAnswer);
        tvScoreInDialog.setText(tvScore.getText().toString());
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
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Game trueFalseGame = realm.where(Game.class).contains("nameGame", "balanceGame").findFirst();
                if (trueFalseGame.getBestScore() < Integer.parseInt(tvScore.getText().toString())) {
                    trueFalseGame.setBestScore(Integer.parseInt(tvScore.getText().toString()));
                }
                String bestScore = getString(R.string.best_score) + trueFalseGame.getBestScore();
                tvBestScore.setText(bestScore);
            }
        });

        alertDialogResultGame.show();
        checkDialogResultGameIsShowing = true;
        //Prevent user using back button of android devices to play game again
        alertDialogResultGame.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    alertDialogResultGame.dismiss();
                    finish();
                }
                return true;
            }
        });

    }

}
