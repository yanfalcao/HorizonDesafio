package br.com.yanfalcao.mundialsurf.view.surfersViews;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.SurferController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SurfersActivity extends ListActivity
        implements AdapterView.OnItemClickListener {

    ArrayList<Map<String, Object>> surfers;
    private DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new DataBaseHelper(this);

        String[] in = {"id", "name", "country"};
        int[] to = {R.id.id, R.id.name, R.id.country};

        SimpleAdapter adapter = new SimpleAdapter(this, listSurfers(), R.layout.activity_surfers, in, to);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Map<String, Object> map = surfers.get(position);
        String idSurfer = map.get("id").toString();
        String name = map.get("name").toString();
        String country = map.get("country").toString();

        Toast.makeText(this, "Selected surfer: " + name, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(new Intent(this, EditSurferActivity.class));
        Bundle extras = new Bundle();

        extras.putString("id", idSurfer);
        extras.putString("name", name);
        extras.putString("country", country);

        intent.putExtras(extras);
        startActivity(intent);
    }

    private List<Map<String, Object>> listSurfers() {
        surfers = (ArrayList)SurferController.selectSurfers(helper,"id", "name", "country");

        return surfers;
    }

    @Override
    protected void onRestart(){
        finish();
        super.onRestart();
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
