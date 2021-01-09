package br.com.yanfalcao.mundialsurf.core.surferEdition.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditSurferActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    EditText name, country;
    String idSurfer, nameSurfer, countrySurfer;
    RoomData database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_surfer);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = RoomData.getInstance(this);

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
        Surfer surfer = new Surfer();

        surfer.setName(name.getText().toString());
        surfer.setCountry(country.getText().toString());

        if(database.getSurferDao().update(surfer) <= 0)
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Data Base Error", Toast.LENGTH_SHORT).show();
    }

    public void deleteSurfer(View view) {
        if(database.getSurferDao().delete(Integer.parseInt(idSurfer)) <= 0)
            Toast.makeText(this, "Data Base Error", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        finish();
    }
}
