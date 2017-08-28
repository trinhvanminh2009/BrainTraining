package minh095.braintraining.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivity;
import minh095.braintraining.model.ModelTrueFalse;
import minh095.braintraining.model.pojo.TrueFalse;

public class TrueFalseActivity extends BaseActivity {

    @BindView(R.id.btnTest)
    Button btnTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);
        enableBackButton();
        setTitle("True/False");

    }

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
