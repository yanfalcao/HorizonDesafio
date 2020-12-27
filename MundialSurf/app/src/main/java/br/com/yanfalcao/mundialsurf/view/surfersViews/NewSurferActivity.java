package br.com.yanfalcao.mundialsurf.view.surfersViews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.SurferController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewSurferActivity extends AppCompatActivity {

    private DataBaseHelper helper;
    @BindView(R.id.name) EditText name;
    @BindView(R.id.country) EditText country;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_surfer);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new DataBaseHelper(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void saveSurfer(View v){
        if(SurferController.insertSurfer(helper ,name.getText().toString(), country.getText().toString()) != -1) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            name.setText("");
            country.setText("");
        }
        else
            Toast.makeText(this, "Data Base Error", Toast.LENGTH_SHORT).show();
    }

    public void cancelSurfer(View v){
        finish();
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
