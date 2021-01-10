package br.com.yanfalcao.mundialsurf.core.hit.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.constants.SurferEnum;
import br.com.yanfalcao.mundialsurf.core.hit.HitContract;
import br.com.yanfalcao.mundialsurf.core.scoreCreation.view.ScoreCreationActivity;
import br.com.yanfalcao.mundialsurf.model.score.Score;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScoreBottomSheetDialog extends BottomSheetDialogFragment {

    @BindView(R.id.surfer_one_name) protected TextView surferOneName;
    @BindView(R.id.surfer_two_name) protected TextView surferTwoName;

    @BindView(R.id.partial_one_surfer_one) protected TextView partialOneNoteOneSurferOne;
    @BindView(R.id.partial_two_surfer_one) protected TextView partialTwoNoteOneSurferOne;
    @BindView(R.id.partial_three_surfer_one) protected TextView partialThreeNoteOneSurferOne;
    @BindView(R.id.average_surfer_one) protected TextView averageNoteOneSurferOne;

    @BindView(R.id.partial2_one_surfer_one) protected TextView partialOneNoteTwoSurferOne;
    @BindView(R.id.partial2_two_surfer_one) protected TextView partialTwoNoteTwoSurferOne;
    @BindView(R.id.partial2_three_surfer_one) protected TextView partialThreeNoteTwoSurferOne;
    @BindView(R.id.average2_surfer_one) protected TextView averageNoteTwoSurferOne;

    @BindView(R.id.partial_one_surfer_two) protected TextView partialOneNoteOneSurferTwo;
    @BindView(R.id.partial_two_surfer_two) protected TextView partialTwoNoteOneSurferTwo;
    @BindView(R.id.partial_three_surfer_two) protected TextView partialThreeNoteOneSurferTwo;
    @BindView(R.id.average_surfer_two) protected TextView averageNoteOneSurferTwo;

    @BindView(R.id.partial2_one_surfer_two) protected TextView partialOneNoteTwoSurferTwo;
    @BindView(R.id.partial2_two_surfer_two) protected TextView partialTwoNoteTwoSurferTwo;
    @BindView(R.id.partial2_three_surfer_two) protected TextView partialThreeNoteTwoSurferTwo;
    @BindView(R.id.average2_surfer_two) protected TextView averageNoteTwoSurferTwo;

    @BindView(R.id.crown_surfer_one) protected ImageView crownSurferOne;
    @BindView(R.id.crown_surfer_two) protected ImageView crownSurferTwo;

    private HitContract.Presenter presenter;
    private int position;

    public ScoreBottomSheetDialog(HitContract.Presenter presenter, int position){
        this.presenter = presenter;
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        ButterKnife.bind(this, view);

        surferOneName.setText(presenter.surferOneName(this.position));
        surferTwoName.setText(presenter.surferTwoName(this.position));

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        List<Score> scoreList = presenter.getScoreSurferOne(this.position);

        if(scoreList != null && !scoreList.isEmpty()){
            Score score = scoreList.get(0);
            partialOneNoteOneSurferOne.setText(decimalFormat.format(score.getPartialScoreOne()));
            partialTwoNoteOneSurferOne.setText(decimalFormat.format(score.getPartialScoreTwo()));
            partialThreeNoteOneSurferOne.setText(decimalFormat.format(score.getPartialScoreTwo()));
            averageNoteOneSurferOne.setText(decimalFormat.format(score.getAverage()));

            if(scoreList.size() > 1){
                score = scoreList.get(1);

                partialOneNoteTwoSurferOne.setText(decimalFormat.format(score.getPartialScoreOne()));
                partialTwoNoteTwoSurferOne.setText(decimalFormat.format(score.getPartialScoreTwo()));
                partialThreeNoteTwoSurferOne.setText(decimalFormat.format(score.getPartialScoreThree()));
                averageNoteTwoSurferOne.setText(decimalFormat.format(score.getAverage()));
            }
        }

        scoreList = presenter.getScoreSurferTwo(this.position);

        if(scoreList != null && !scoreList.isEmpty()){
            Score score = scoreList.get(0);
            partialOneNoteOneSurferTwo.setText(decimalFormat.format(score.getPartialScoreOne()));
            partialTwoNoteOneSurferTwo.setText(decimalFormat.format(score.getPartialScoreTwo()));
            partialThreeNoteOneSurferTwo.setText(decimalFormat.format(score.getPartialScoreTwo()));
            averageNoteOneSurferTwo.setText(decimalFormat.format(score.getAverage()));

            if(scoreList.size() > 1){
                score = scoreList.get(1);

                partialOneNoteTwoSurferTwo.setText(decimalFormat.format(score.getPartialScoreOne()));
                partialTwoNoteTwoSurferTwo.setText(decimalFormat.format(score.getPartialScoreTwo()));
                partialThreeNoteTwoSurferTwo.setText(decimalFormat.format(score.getPartialScoreThree()));
                averageNoteTwoSurferTwo.setText(decimalFormat.format(score.getAverage()));
            }
        }

        SurferEnum surferEnum = presenter.getWinner(this.position);

        if(surferEnum == SurferEnum.SURFER_ONE){
            crownSurferOne.setVisibility(View.VISIBLE);
        }else if(surferEnum == SurferEnum.SURFER_TWO){
            crownSurferTwo.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @OnClick(R.id.new_score_buttom)
    public void onClickNewScore(View view){
        ScoreCreationActivity.startActivity(
                view.getContext(),
                presenter.getHitAt(position).getIdSurferOne(),
                presenter.getHitAt(position).getIdSurferTwo(),
                presenter.getHitAt(position).getId()
        );
    }
}
