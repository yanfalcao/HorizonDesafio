package br.com.yanfalcao.mundialsurf.view.wavesViews;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.model.DataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewWaveActivity extends ListActivity
        implements AdapterView.OnItemClickListener {

    private ArrayList<Map<String, Object>> batteries;
    private DataModel helper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        batteries = new ArrayList<Map<String, Object>>();
        helper = new DataModel(this);

        String[] in = {"idBattery", "surfer1", "surfer2"};
        int[] to = {R.id.idBattery, R.id.surfer1, R.id.surfer2};

        SimpleAdapter adapter = new SimpleAdapter(this, listBatteries(), R.layout.activity_new_wave, in, to);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> map = batteries.get(position);
        String idBattery = map.get("idBattery").toString();

        if(idBattery.equals("Empty"))
            Toast.makeText(this, "ADD A NEW BATTERY TO ADD A NEW WAVE" + idBattery, Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(this, "Selected battery: " + idBattery, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(new Intent(this, RegisterWaveActivity.class));
            Bundle extras = new Bundle();

            extras.putString("idSurferOne", map.get("idSurferOne").toString());
            extras.putString("idSurferTwo", map.get("idSurferTwo").toString());
            extras.putString("nameSurferOne", map.get("surfer1").toString());
            extras.putString("nameSurferTwo", map.get("surfer2").toString());
            extras.putString("idBattery", idBattery);

            intent.putExtras(extras);
            startActivity(intent);
        }
    }

    private List<Map<String, Object>> listBatteries() {
        batteries = (ArrayList)helper.selectBatteries("idBattery", "surfer1", "surfer2");

        return batteries;
    }

    @Override
    protected void onRestart(){
        finish();
        super.onRestart();
    }

    @Override
    protected void onDestroy(){
        helper.dataDestroy();
        super.onDestroy();
    }
}
