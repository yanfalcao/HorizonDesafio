package br.com.yanfalcao.mundialsurf.core.scoreCreation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.core.scoreCreation.ScoreCreationContract;
import br.com.yanfalcao.mundialsurf.core.scoreCreation.presenter.ScoreCreationPresenter;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.utils.InfoDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScoreCreationActivity extends AppCompatActivity implements ScoreCreationContract.View {

    @BindView(R.id.chooseSurfer) Spinner chooseSurfer;
    @BindView(R.id.noteOneEditText) EditText noteOne;
    @BindView(R.id.noteTwoEditText) EditText noteTwo;
    @BindView(R.id.noteThreeEditText) EditText noteThree;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private RoomData database;
    private ScoreCreationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_wave);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = RoomData.getInstance(this);
        presenter = new ScoreCreationPresenter(
                RoomData.getInstance(this),
                this,
                getIntent().getIntExtra("idSurferOne", -1),
                getIntent().getIntExtra("idSurferTwo", -1)
        );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                presenter.getSurfersList()
        );

        chooseSurfer.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.save)
    public void save(View view) {
        int idHit = getIntent().getIntExtra("idBattery", -1);

        if(presenter.saveWave(idHit, chooseSurfer.getSelectedItemPosition())){
            String partial1 = noteOne.getText().toString();
            String partial2 = noteTwo.getText().toString();
            String partial3 = noteThree.getText().toString();

            if(presenter.validateFields(partial1, partial2, partial3)){
                if(presenter.saveScore(partial1, partial2, partial3)){
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(this, "ERROR: unexpected error while processing the request", Toast.LENGTH_SHORT).show();
                }
            }
        }else {
            Toast.makeText(this, "ERROR: unexpected error while processing the request", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setErrorPartialScoreEmpty() {
        InfoDialog.show(this, getString(R.string.emptyFields));
    }

    @Override
    public void setErrorPartialScoreType() {
        InfoDialog.show(this, getString(R.string.incorrectType));
    }
}
