package br.com.yanfalcao.mundialsurf.view.wavesViews;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.BatteryController;
import br.com.yanfalcao.mundialsurf.controller.SurferController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import br.com.yanfalcao.mundialsurf.view.batteriesViews.NewBatteryActivity;
import br.com.yanfalcao.mundialsurf.view.surfersViews.surferRecycleView.LineAdapterSurfer;
import br.com.yanfalcao.mundialsurf.view.wavesViews.wavesRecycleView.LineAdapterWave;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.List;
import java.util.Map;

public class NewWaveActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_layout) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private DataBaseHelper helper;

    private LineAdapterWave mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wave);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Battery");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new DataBaseHelper(this);

        setupRecycler();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.floatingActionButton)
    public void toolbarClick(){
        startActivity(new Intent(this, NewBatteryActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.surfer_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onRestart(){
        mAdapter = new LineAdapterWave(getListBatteries());
        mRecyclerView.invalidate();
        mRecyclerView.setAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        super.onRestart();
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

    private void setupRecycler(){
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new LineAdapterWave(getListBatteries());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Map<String, Object>> getListBatteries() {
        return BatteryController.selectBatteries(helper,"idBattery", "surfer1", "surfer2");
    }
}
