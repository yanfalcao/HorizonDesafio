package br.com.yanfalcao.mundialsurf.core.scoreCreation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.score.Score;
import br.com.yanfalcao.mundialsurf.model.wave.Wave;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.ArrayList;

public class ScoreCreationActivity extends AppCompatActivity {

    @BindView(R.id.chooseSurfer) Spinner chooseSurfer;
    @BindView(R.id.noteOneEditText) EditText noteOne;
    @BindView(R.id.noteTwoEditText) EditText noteTwo;
    @BindView(R.id.noteThreeEditText) EditText noteThree;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private ArrayList<String> surfers;
    private RoomData database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_wave);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = RoomData.getInstance(this);
        surfers = getSurfers();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                surfers);
        chooseSurfer.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.save)
    public void save(View view) {
        long result, resultNote;
        Wave wave = new Wave();
        Score score = new Score();

        wave.setIdHit(Integer.parseInt(getIntent().getStringExtra("idBattery")));

        if(getIntent().getStringExtra("nameSurferOne").equals(chooseSurfer.getSelectedItem().toString())) {
            wave.setIdSurfer(Integer.parseInt(getIntent().getStringExtra("idSurferOne")));
            result = database.getWaveDao().insert(wave);
        }else {
            wave.setIdSurfer(Integer.parseInt(getIntent().getStringExtra("idSurferTwo")));
            result = database.getWaveDao().insert(wave);
        }

        score.setIdWave(database.getWaveDao().getLastWaveId());
        score.setPartialScoreOne(Double.parseDouble(noteOne.getText().toString()));
        score.setPartialScoreTwo(Double.parseDouble(noteTwo.getText().toString()));
        score.setPartialScoreThree(Double.parseDouble(noteThree.getText().toString()));

        resultNote = database.getScoreDao().insert(score);

        if(result != -1 && resultNote != -1)
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "DataBase Error", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<String> getSurfers(){
        ArrayList<String> names = new ArrayList<>();
        names.add(getIntent().getStringExtra("nameSurferOne"));
        names.add(getIntent().getStringExtra("nameSurferTwo"));

        return names;
    }
}
