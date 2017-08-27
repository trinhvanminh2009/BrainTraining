package minh095.braintraining.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import minh095.braintraining.R;
import minh095.braintraining.activity.base.BaseActivity;
import minh095.braintraining.adapter.ScoreAdapter;
import minh095.braintraining.model.ModelScore;
import minh095.braintraining.model.pojo.Score;

public class ListActivity extends BaseActivity implements ScoreAdapter.OnClickItemScore {

    @BindView(R.id.rcvScore)
    RecyclerView rcvScore;

    List<Score> listScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        rcvScore.setLayoutManager(new LinearLayoutManager(this));
        listScore = ModelScore.getAllScore();
        ScoreAdapter scoreAdapter = new ScoreAdapter(this, listScore, this);
        rcvScore.setAdapter(scoreAdapter);

    }

    @Override
    public void onClickButtonItemScore(int position) {
        Toast.makeText(this, String.valueOf(listScore.get(position).getScore()), Toast.LENGTH_SHORT).show();
    }
}
