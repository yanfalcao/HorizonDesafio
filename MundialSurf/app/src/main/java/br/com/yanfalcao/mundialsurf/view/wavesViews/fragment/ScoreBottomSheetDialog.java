package br.com.yanfalcao.mundialsurf.view.wavesViews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Map;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.view.wavesViews.RegisterWaveActivity;

public class ScoreBottomSheetDialog extends BottomSheetDialogFragment {
    private Map<String, Object> map;

    public ScoreBottomSheetDialog(Map<String, Object> map){
        this.map = map;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        Button newScoreButtom = view.findViewById(R.id.new_score_buttom);
        newScoreButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
        return view;
    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }
}
