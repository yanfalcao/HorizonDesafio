package br.com.yanfalcao.mundialsurf.core.surferCreation.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.core.surferCreation.SurferCreationContract;
import br.com.yanfalcao.mundialsurf.core.surferCreation.presenter.SurferCreationPresenter;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewSurferActivity extends AppCompatActivity implements SurferCreationContract.View {

    @BindView(R.id.name) EditText nameEdit;
    @BindView(R.id.country) EditText countryEdit;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private SurferCreationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_surfer);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new SurferCreationPresenter(RoomData.getInstance(this), this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void saveSurfer(View v){
        String name = nameEdit.getText().toString();
        String country = countryEdit.getText().toString();

        if(presenter.validateFields(name, country)){
            if(presenter.save(name, country)) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Data Base Error", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    public void cancelSurfer(View v){
        finish();
    }

    @Override
    public void setErrorEmptyFields() {
        Toast.makeText(this, "ERROR: Exist empty fields.", Toast.LENGTH_SHORT).show();
    }
}
