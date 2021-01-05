package br.com.yanfalcao.mundialsurf.view.surfersViews;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.SurferController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditSurferActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    EditText name, country;
    String idSurfer, nameSurfer, countrySurfer;
    DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_surfer);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new DataBaseHelper(this);

        idSurfer = getIntent().getStringExtra("id");
        countrySurfer = getIntent().getStringExtra("country");
        nameSurfer = getIntent().getStringExtra("name");

        name = findViewById(R.id.name);
        country = findViewById(R.id.country);

        name.setText(nameSurfer);
        country.setText(countrySurfer);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
