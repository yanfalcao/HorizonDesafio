package br.com.yanfalcao.mundialsurf.view.wavesViews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.WaveController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;

public class RegisterWaveActivity extends AppCompatActivity {

    @BindView(R.id.chooseSurfer) Spinner chooseSurfer;
    private ArrayList<String> surfers;
    private DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_wave);
        ButterKnife.bind(this);

        helper = new DataBaseHelper(this);
        surfers = getSurfers();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                surfers);
        chooseSurfer.setAdapter(adapter);
    }

    private ArrayList<String> getSurfers(){
        ArrayList<String> names = new ArrayList<>();
        names.add(getIntent().getStringExtra("nameSurferOne"));
        names.add(getIntent().getStringExtra("nameSurferTwo"));

        return names;
    }

    public void saveWave(View view) {
        long result;

        if(getIntent().getStringExtra("nameSurferOne").equals(chooseSurfer.getSelectedItem().toString()))
            result = WaveController.insertWave(helper, getIntent().getStringExtra("idBattery"), getIntent().getStringExtra("idSurferOne"));
        else
            result = WaveController.insertWave(helper, getIntent().getStringExtra("idBattery"), getIntent().getStringExtra("idSurferTwo"));

        if(result != -1)
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "DataBase Error", Toast.LENGTH_SHORT).show();
    }
}
