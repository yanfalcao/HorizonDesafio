package br.com.yanfalcao.mundialsurf.core.surferEdition.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.core.surferEdition.SurferEditionContract;
import br.com.yanfalcao.mundialsurf.core.surferEdition.presenter.SurferEditionPresenter;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import br.com.yanfalcao.mundialsurf.utils.InfoDialog;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditSurferActivity extends AppCompatActivity implements SurferEditionContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.name) EditText nameEdit;
    @BindView(R.id.country) EditText countryEdit;

    private SurferEditionContract.Presenter presenter;
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
        presenter = new SurferEditionPresenter(
                RoomData.getInstance(this),
                this,
                getIntent().getIntExtra("id", 0));

        presenter.fillFields();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void editSurfer(View v){
        String name = nameEdit.getText().toString();
        String country = countryEdit.getText().toString();

        if(presenter.validateFields(name, country)) {
            if (presenter.update(name, country)) {
                Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.data_error), Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    public void deleteSurfer(View view) {
        if(presenter.delete()) {
            Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, getString(R.string.data_error), Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void setName(String name) {
        nameEdit.setText(name);
    }

    @Override
    public void setCountry(String country) {
        countryEdit.setText(country);
    }

    @Override
    public void setErrorFillField() {
        InfoDialog.show(this, getString(R.string.emptyFields));
    }
}
