package minh095.braintraining.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivityNoToolbar;
import minh095.braintraining.model.ModelQuickMathematics;
import minh095.braintraining.model.pojo.QuickMathematics;

public class QuickMathematicsActivity extends BaseActivityNoToolbar implements Animator.AnimatorListener {

    /**currentAnswer to know which value player clicked
     *
     * */
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

    private boolean isCorrect = false;
    private int finalScore = 0;
    private QuickMathematics currentQuestion;
    int randomPositionCorrectAnswer = 0;
    int currentAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_mathematic);
        setFullTimer();
        startProgressTimer(0);
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

    ObjectAnimator animationProgressTimer;

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
                if (animationProgressTimer != null) {
                    animationProgressTimer.cancel();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (animationProgressTimer != null) {
            animationProgressTimer.cancel();
        }
        finish();
    }

    @OnClick({R.id.btnAnswerOne,R.id.btnAnswerTwo,R.id.btnAnswerThree,R.id.btnAnswerFour})
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
                    if(randomPositionCorrectAnswer == 2) {
                        btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_wrong);
                        btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_correct);
                    }
                    if(randomPositionCorrectAnswer == 3) {
                        btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_wrong);
                        btnAnswerThree.setBackgroundResource(R.drawable.background_button_check_correct);
                    }
                    if(randomPositionCorrectAnswer == 4) {
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
                    if(randomPositionCorrectAnswer == 1) {
                        btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_correct);
                        btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_wrong);
                    }
                    if(randomPositionCorrectAnswer == 3) {
                        btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_wrong);
                        btnAnswerThree.setBackgroundResource(R.drawable.background_button_check_correct);
                    }
                    if(randomPositionCorrectAnswer == 4) {
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
                    if(randomPositionCorrectAnswer == 1) {
                        btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_correct);
                        btnAnswerThree.setBackgroundResource(R.drawable.background_button_check_wrong);
                    }
                    if(randomPositionCorrectAnswer == 2) {
                        btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_correct);
                        btnAnswerThree.setBackgroundResource(R.drawable.background_button_check_wrong);
                    }
                    if(randomPositionCorrectAnswer == 4) {
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
                    if(randomPositionCorrectAnswer == 1) {
                        btnAnswerOne.setBackgroundResource(R.drawable.background_button_check_correct);
                        btnAnswerFour.setBackgroundResource(R.drawable.background_button_check_wrong);
                    }
                    if(randomPositionCorrectAnswer == 2) {
                        btnAnswerTwo.setBackgroundResource(R.drawable.background_button_check_correct);
                        btnAnswerFour.setBackgroundResource(R.drawable.background_button_check_wrong);
                    }
                    if(randomPositionCorrectAnswer == 3) {
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

    public void showDialogResultGame() {
        android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_result_game, null);
        dialogBuilder.setView(dialogView);
        TextView tvWrongAnswer = (TextView) dialogView.findViewById(R.id.tvWrongAnswer);
        TextView tvCorrectAnswer = (TextView) dialogView.findViewById(R.id.tvCorrectAnswer);
        TextView tvCurrentScore = (TextView) dialogView.findViewById(R.id.tvScore);
        TextView tvBestScore = (TextView) dialogView.findViewById(R.id.tvBestScore);


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


        tvCurrentScore.setText(String.valueOf(finalScore));

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
    }


    @Override
    public void onAnimationStart(Animator animation) {

        List<QuickMathematics> quickMathematicsList = ModelQuickMathematics.randomQuickMathematics(getApplicationContext(), 30);
        int index = new Random().nextInt(quickMathematicsList.size());
        currentQuestion = quickMathematicsList.get(index);
        String question = "";
        Random random = new Random();
        randomPositionCorrectAnswer = random.nextInt(4)+1;

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
                switch (randomPositionCorrectAnswer)
                {
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
                switch (randomPositionCorrectAnswer)
                {
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
                switch (randomPositionCorrectAnswer)
                {
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
