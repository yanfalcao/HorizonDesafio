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
import br.com.yanfalcao.mundialsurf.controller.NoteController;
import br.com.yanfalcao.mundialsurf.controller.WaveController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.ArrayList;

public class RegisterWaveActivity extends AppCompatActivity {

    @BindView(R.id.chooseSurfer) Spinner chooseSurfer;
    @BindView(R.id.noteOneEditText) EditText noteOne;
    @BindView(R.id.noteTwoEditText) EditText noteTwo;
    @BindView(R.id.noteThreeEditText) EditText noteThree;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private ArrayList<String> surfers;
    private DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_wave);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new DataBaseHelper(this);
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

        if(getIntent().getStringExtra("nameSurferOne").equals(chooseSurfer.getSelectedItem().toString()))
            result = WaveController.insertWave(helper, getIntent().getStringExtra("idBattery"), getIntent().getStringExtra("idSurferOne"));
        else
            result = WaveController.insertWave(helper, getIntent().getStringExtra("idBattery"), getIntent().getStringExtra("idSurferTwo"));

        resultNote = NoteController.insertNote(helper,
                Integer.toString(WaveController.selectLastWaveId(helper)),
                noteOne.getText().toString(),
                noteTwo.getText().toString(),
                noteThree.getText().toString());

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
