package br.com.yanfalcao.mundialsurf.core.surfer.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.core.surfer.view.surferRecycleView.LineAdapterSurfer;
import br.com.yanfalcao.mundialsurf.core.surferCreation.view.NewSurferActivity;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SurfersListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_layout) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private LineAdapterSurfer mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RoomData database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfers_list);
        ButterKnife.bind(this);

        database = RoomData.getInstance(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Surfer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupRecycler();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupRecycler(){
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new LineAdapterSurfer(database.getSurferDao().getAll());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void clickOnNewSurfers(View view) { startActivity(new Intent(this, NewSurferActivity.class)); }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.surfer_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
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
        mAdapter = new LineAdapterSurfer(database.getSurferDao().getAll());
        mRecyclerView.invalidate();
        mRecyclerView.setAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        super.onRestart();
    }
}
