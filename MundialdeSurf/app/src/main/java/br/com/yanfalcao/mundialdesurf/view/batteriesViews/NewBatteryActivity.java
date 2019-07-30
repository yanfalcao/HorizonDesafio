package br.com.yanfalcao.mundialdesurf.view.batteriesViews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import br.com.yanfalcao.mundialdesurf.R;
import br.com.yanfalcao.mundialdesurf.controller.DataHelper;
import br.com.yanfalcao.mundialdesurf.model.DataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NewBatteryActivity extends AppCompatActivity {

    private Spinner surferOne;
    private Spinner surferTwo;
    private ArrayList<Map<String, Object>> surfers;
    String name1, name2;
    private DataModel helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_battery);

        helper = new DataModel(this);
        surfers = (ArrayList) helper.selectSurfers("id", "name", "country");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                                            android.R.layout.simple_spinner_dropdown_item,
                                                            DataHelper.getSurfersName(surfers));
        surferOne = findViewById(R.id.surferOne);
        surferTwo = findViewById(R.id.surferTwo);

        surferOne.setAdapter(adapter);
        surferTwo.setAdapter(adapter);
    }

    public void saveBattery(View v){
        String s1 = DataHelper.getIdSurferByName(surferOne.getSelectedItem().toString(), surfers);
        String s2 = DataHelper.getIdSurferByName(surferTwo.getSelectedItem().toString(), surfers);

        if(! DataHelper.areSame(s1, s2))
            Toast.makeText(this, "ERROR THE SURFERS ARE SAME", Toast.LENGTH_SHORT).show();
        else{
            if(helper.insertBattery(s1, s2) != -1)
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "DataBase Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        helper.dataDestroy();
        super.onDestroy();
    }
}
