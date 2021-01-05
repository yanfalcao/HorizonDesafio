package br.com.yanfalcao.mundialsurf.view.batteriesViews.winnerViews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.BatteryController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import br.com.yanfalcao.mundialsurf.view.batteriesViews.winnerViews.batteryRecyclerView.LineAdapterBattery;

public class BatteryListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private LineAdapterBattery mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_list);

        setupRecycler();
    }

    private void setupRecycler(){
        mRecyclerView = findViewById(R.id.recycler_view_batteries);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new LineAdapterBattery(BatteryController.selectBatteries(new DataBaseHelper(this), "id", "surferOne", "surferTwo"));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
