package br.com.yanfalcao.mundialsurf.view.surfersViews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.SurferController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;

public class EditSurferActivity extends AppCompatActivity {

    EditText name, country;
    String idSurfer, nameSurfer, countrySurfer;
    DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_surfer);

        helper = new DataBaseHelper(this);

        idSurfer = getIntent().getStringExtra("id");
        countrySurfer = getIntent().getStringExtra("country");
        nameSurfer = getIntent().getStringExtra("name");

        name = findViewById(R.id.name);
        country = findViewById(R.id.country);

        name.setText(nameSurfer);
        country.setText(countrySurfer);
    }

    public void editSurfer(View v){
        nameSurfer = name.getText().toString();
        countrySurfer = country.getText().toString();

        if(SurferController.editSurfer(helper, idSurfer, nameSurfer, countrySurfer))
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Data Base Error", Toast.LENGTH_SHORT).show();
    }

    public void deleteSurfer(View view) {
        if(! SurferController.deleteSurfer(helper, idSurfer))
            Toast.makeText(this, "Data Base Error", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
