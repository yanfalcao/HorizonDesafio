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
import br.com.yanfalcao.mundialsurf.controller.NoteController;
import br.com.yanfalcao.mundialsurf.controller.WaveController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewPunctuacionActivity extends AppCompatActivity {

    @BindView(R.id.Waves) Spinner wave;
    private ArrayList<String> waves;
    @BindView(R.id.note1) EditText n1;
    @BindView(R.id.note2) EditText n2;
    @BindView(R.id.note3) EditText n3;
    private DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_punctuation);
        ButterKnife.bind(this);

        helper = new DataBaseHelper(this);
        waves = (ArrayList) WaveController.selectWavesWithSurferName(helper);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                waves);

        wave.setAdapter(adapter);
    }

    public void saveNote(View v){
        String w = wave.getSelectedItem().toString();
        String note1 = n1.getText().toString();
        String note2 = n2.getText().toString();
        String note3 = n3.getText().toString();

        Toast.makeText(this, "Data " + w + " notes" + n1 + " " + n2 + " " + n3, Toast.LENGTH_SHORT).show();

        if(NoteController.insertNote(helper, w.split("-")[0], note1, note2, note3) != -1)
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "DataBase Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
