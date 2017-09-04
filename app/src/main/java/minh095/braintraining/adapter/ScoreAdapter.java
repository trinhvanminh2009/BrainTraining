package minh095.braintraining.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import minh095.braintraining.R;
import minh095.braintraining.model.pojo.Score;

public class ScoreAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Score> listScore;
    private OnClickItemScore onClickItemScore;

    public ScoreAdapter(Context context, List<Score> listScore, OnClickItemScore onClickItemScore) {
        this.context = context;
        this.listScore = listScore;
        this.onClickItemScore = onClickItemScore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ScoreViewHolder scoreViewHolder = (ScoreViewHolder) holder;
        scoreViewHolder.tvScore.setText(String.valueOf(listScore.get(position).getScore()));


    }

    @Override
    public int getItemCount() {
        return listScore.size();
    }

    private class ScoreViewHolder extends RecyclerView.ViewHolder {
        private TextView tvScore;

        ScoreViewHolder(View itemView) {
            super(itemView);
            tvScore = (TextView) itemView.findViewById(R.id.tvScore);
            tvScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickItemScore != null) {
                        onClickItemScore.onClickButtonItemScore(getAdapterPosition());
                    }
                }
            });
        }


    }

    public interface OnClickItemScore {
        void onClickButtonItemScore(int position);
    }
}
