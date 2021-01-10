package br.com.yanfalcao.mundialsurf.core.hitCreation.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.core.hitCreation.HitCreationContract;
import br.com.yanfalcao.mundialsurf.core.hitCreation.presenter.HitCreationPresenter;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.utils.InfoDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HitCreationActivity extends AppCompatActivity implements HitCreationContract.view {

    @BindView(R.id.surferOne) Spinner surferOne;
    @BindView(R.id.surferTwo) Spinner surferTwo;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private HitCreationContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hit);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new HitCreationPresenter(this, RoomData.getInstance(this));

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                presenter.getSurfersName()){

            @Override
            public boolean isEnabled(int position) {
                if(position == 0){
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }else {
                    tv.setTextColor(Color.BLACK);
                }

                return view;
            }
        };

        surferOne.setAdapter(adapter);
        surferTwo.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.save_hit)
    public void saveHit(View v){
        int positionOne = surferOne.getSelectedItemPosition();
        int positionTwo = surferTwo.getSelectedItemPosition();

        if(presenter.validateFields(positionOne, positionTwo)) {
            if (presenter.save(positionOne, positionTwo)) {
                Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.data_error), Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    @OnClick(R.id.cancel)
    public void cancelSurfer(View v){
        finish();
    }

    @Override
    public void setErrorWithoutSurfer() {
        InfoDialog.show(this, getString(R.string.withoutSurfer));
    }

    @Override
    public void setErrorSurfersSame() {
        InfoDialog.show(this, getString(R.string.sameSurfers));
    }

    @Override
    public void setErrorSelectSurfer() {
        InfoDialog.show(this, getString(R.string.withoutSurfer));
    }
}
