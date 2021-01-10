package br.com.yanfalcao.mundialsurf.core.surferCreation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.core.surfer.view.SurfersListActivity;
import br.com.yanfalcao.mundialsurf.core.surferCreation.SurferCreationContract;
import br.com.yanfalcao.mundialsurf.core.surferCreation.presenter.SurferCreationPresenter;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.utils.InfoDialog;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewSurferActivity extends AppCompatActivity implements SurferCreationContract.View {

    @BindView(R.id.name) EditText nameEdit;
    @BindView(R.id.country) EditText countryEdit;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private SurferCreationContract.Presenter presenter;

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, NewSurferActivity.class));
    }

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
                Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, getString(R.string.data_error), Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    public void cancelSurfer(View v){
        finish();
    }

    @Override
    public void setErrorEmptyFields() {
        InfoDialog.show(this, getString(R.string.emptyFields));
    }
}
