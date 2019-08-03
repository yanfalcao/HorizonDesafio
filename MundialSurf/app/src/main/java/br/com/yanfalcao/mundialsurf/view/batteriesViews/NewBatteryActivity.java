package br.com.yanfalcao.mundialsurf.view.batteriesViews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.BatteryController;
import br.com.yanfalcao.mundialsurf.controller.SurferController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;

import java.util.ArrayList;
import java.util.Map;

public class NewBatteryActivity extends AppCompatActivity {

    private Spinner surferOne;
    private Spinner surferTwo;
    private ArrayList<Map<String, Object>> surfers;
    private DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_battery);

        helper = new DataBaseHelper(this);
        surfers = (ArrayList) SurferController.selectSurfers(helper,"id", "name", "country");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                SurferController.getSurfersName(surfers));
        surferOne = findViewById(R.id.surferOne);
        surferTwo = findViewById(R.id.surferTwo);

        surferOne.setAdapter(adapter);
        surferTwo.setAdapter(adapter);
    }

    public void saveBattery(View v){
        String s1 = SurferController.getIdSurferByName(surferOne.getSelectedItem().toString(), surfers);
        String s2 = SurferController.getIdSurferByName(surferTwo.getSelectedItem().toString(), surfers);

        if(s1.equals("Empty") || s2.equals("Empty"))
            Toast.makeText(this, "ERROR: DO NOT HAVE SURFER(S) ", Toast.LENGTH_SHORT).show();
        else if(s1.equals(s2))
            Toast.makeText(this, "ERROR: THE SURFERS ARE SAME", Toast.LENGTH_SHORT).show();
        else{
            if(BatteryController.insertBattery(helper,s1, s2) != -1)
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "DATABASE ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
