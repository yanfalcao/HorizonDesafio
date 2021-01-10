package br.com.yanfalcao.mundialsurf.core.scoreCreation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

    private static String ID_SURFER_ONE = "idSurferOne";
    private static String ID_SURFER_TWO = "idSurferTwo";
    private static String ID_HIT = "idHit";

    @BindView(R.id.chooseSurfer) Spinner chooseSurfer;
    @BindView(R.id.noteOneEditText) EditText noteOne;
    @BindView(R.id.noteTwoEditText) EditText noteTwo;
    @BindView(R.id.noteThreeEditText) EditText noteThree;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private ScoreCreationContract.Presenter presenter;

    public static void startActivity(Context context, int idSurferOne, int idSurferTwo, int idHit){
        Intent intent = new Intent(new Intent(context, ScoreCreationActivity.class));
        Bundle extras = new Bundle();

        extras.putInt(ID_SURFER_ONE, idSurferOne);
        extras.putInt(ID_SURFER_TWO, idSurferTwo);
        extras.putInt(ID_HIT, idHit);

        intent.putExtras(extras);
        context.startActivity(intent);
    }

    public static int getExtraIdSurferOne(Activity activity){
        return activity.getIntent().getIntExtra(ID_SURFER_ONE, -1);
    }

    public static int getExtraIdSurferTwo(Activity activity){
        return activity.getIntent().getIntExtra(ID_SURFER_TWO, -1);
    }

    public static int getExtraIdHit(Activity activity){
        return activity.getIntent().getIntExtra(ID_HIT, -1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_wave);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new ScoreCreationPresenter(
                RoomData.getInstance(this),
                this,
                getExtraIdSurferOne(this),
                getExtraIdSurferTwo(this)
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
        int idHit = getExtraIdHit(this);

        if(presenter.saveWave(idHit, chooseSurfer.getSelectedItemPosition())){
            String partial1 = noteOne.getText().toString();
            String partial2 = noteTwo.getText().toString();
            String partial3 = noteThree.getText().toString();

            if(presenter.validateFields(partial1, partial2, partial3)){
                if(presenter.saveScore(partial1, partial2, partial3)){
                    Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    InfoDialog.show(this, getString(R.string.unexpected));
                }
            }
        }else {
            InfoDialog.show(this, getString(R.string.unexpected));
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
