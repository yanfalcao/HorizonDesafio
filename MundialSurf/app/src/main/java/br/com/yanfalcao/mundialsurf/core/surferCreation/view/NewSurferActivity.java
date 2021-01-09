package br.com.yanfalcao.mundialsurf.core.surferCreation.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewSurferActivity extends AppCompatActivity {

    @BindView(R.id.name) EditText name;
    @BindView(R.id.country) EditText country;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private RoomData database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_surfer);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = RoomData.getInstance(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void saveSurfer(View v){
        Surfer surfer = new Surfer();

        surfer.setName(name.getText().toString());
        surfer.setCountry(country.getText().toString());

        if(database.getSurferDao().insert(surfer) != -1) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            name.setText("");
            country.setText("");
        }
        else {
            Toast.makeText(this, "Data Base Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelSurfer(View v){
        finish();
    }
}
