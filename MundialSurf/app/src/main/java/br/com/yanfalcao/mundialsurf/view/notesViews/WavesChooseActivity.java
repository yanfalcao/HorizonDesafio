package br.com.yanfalcao.mundialsurf.view.notesViews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.model.DataModel;

public class WavesChooseActivity extends AppCompatActivity {

    private Spinner wave;
    private ArrayList<String> waves;
    private EditText n1, n2, n3;
    private DataModel helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waves_choose);

        helper = new DataModel(this);
        waves = (ArrayList) helper.selectWaves();
        n1 = (EditText) findViewById(R.id.note1);
        n2 = (EditText) findViewById(R.id.note2);
        n3 = (EditText) findViewById(R.id.note3);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                waves);
        wave = findViewById(R.id.Waves);

        wave.setAdapter(adapter);
    }

    public void saveNote(View v){
        String w = wave.getSelectedItem().toString();
        Double note1 = Double.parseDouble(n1.getText().toString());
        Double note2 = Double.parseDouble(n2.getText().toString());
        Double note3 = Double.parseDouble(n3.getText().toString());

        if(helper.insertNote(w, note1, note2, note3) != -1)
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "DataBase Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        helper.dataDestroy();
        super.onDestroy();
    }
}
