package br.com.yanfalcao.mundialsurf.view.wavesViews.fragment;

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

import java.util.Map;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.BatteryController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import br.com.yanfalcao.mundialsurf.view.wavesViews.RegisterWaveActivity;
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

    private Map<String, Object> map;

    public ScoreBottomSheetDialog(Map<String, Object> map){
        this.map = map;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        ButterKnife.bind(this, view);

        surferOneName.setText(map.get("surfer1").toString());
        surferTwoName.setText(map.get("surfer2").toString());

        int result = BatteryController.getWinner(new DataBaseHelper(view.getContext()), map.get("idBattery").toString());

        if(result == 1){
            crownSurferOne.setVisibility(View.VISIBLE);
        }else if(result == 2){
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
        Intent intent = new Intent(new Intent(view.getContext(), RegisterWaveActivity.class));
        Bundle extras = new Bundle();

        extras.putString("idSurferOne", map.get("idSurferOne").toString());
        extras.putString("idSurferTwo", map.get("idSurferTwo").toString());
        extras.putString("nameSurferOne", map.get("surfer1").toString());
        extras.putString("nameSurferTwo", map.get("surfer2").toString());
        extras.putString("idBattery", map.get("idBattery").toString());

        intent.putExtras(extras);
        view.getContext().startActivity(intent);
    }
}
