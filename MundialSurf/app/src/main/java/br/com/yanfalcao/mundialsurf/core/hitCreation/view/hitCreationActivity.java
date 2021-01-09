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
import br.com.yanfalcao.mundialsurf.controller.SurferController;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.hit.Hit;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.List;

public class hitCreationActivity extends AppCompatActivity {

    @BindView(R.id.surferOne) Spinner surferOne;
    @BindView(R.id.surferTwo) Spinner surferTwo;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private List<Surfer> surfers;
    private RoomData database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_battery);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = RoomData.getInstance(this);
        surfers = database.getSurferDao().getAll();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                SurferController.getSurfersName(surfers)){

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
    public void saveBattery(View v){
        Hit hit = new Hit();

        String s1 = SurferController.getIdSurferByName(surferOne.getSelectedItem().toString(), surfers);
        String s2 = SurferController.getIdSurferByName(surferTwo.getSelectedItem().toString(), surfers);

        if(s1 == null || s1.equals("Empty") || s2 == null || s2.equals("Empty"))
            Toast.makeText(this, "ERROR: DO NOT HAVE SURFER(S) ", Toast.LENGTH_SHORT).show();
        else if(s1.equals(s2))
            Toast.makeText(this, "ERROR: THE SURFERS ARE SAME", Toast.LENGTH_SHORT).show();
        else{
            hit.setIdSurferOne(Integer.parseInt(s1));
            hit.setIdSurferTwo(Integer.parseInt(s2));

            if(database.getHitDao().insert(hit) != -1)
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "DATABASE ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.cancel)
    public void cancelSurfer(View v){
        finish();
    }
}
