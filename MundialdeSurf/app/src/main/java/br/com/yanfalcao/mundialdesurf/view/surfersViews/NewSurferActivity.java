package br.com.yanfalcao.mundialdesurf.view.surfersViews;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.yanfalcao.mundialdesurf.R;
import br.com.yanfalcao.mundialdesurf.model.DataBaseHelper;
import br.com.yanfalcao.mundialdesurf.model.DataModel;

public class NewSurferActivity extends AppCompatActivity {

    private DataModel helper;
    private EditText name, country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_surfer);

        name = (EditText) findViewById(R.id.name);
        country = (EditText) findViewById(R.id.country);

        helper = new DataModel(this);
    }

    public void saveSurfer(View v){
        if(helper.insertSurfer(name.getText().toString(), country.getText().toString()) != -1) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            name.setText("");
            country.setText("");
        }
        else
            Toast.makeText(this, "Data Base Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        helper.dataDestroy();
        super.onDestroy();
    }
}
