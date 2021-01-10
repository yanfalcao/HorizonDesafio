package br.com.yanfalcao.mundialsurf.core.hit.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.core.hit.HitContract;
import br.com.yanfalcao.mundialsurf.core.hit.presenter.HitPresenter;
import br.com.yanfalcao.mundialsurf.core.hitCreation.view.HitCreationActivity;
import br.com.yanfalcao.mundialsurf.core.hit.view.hitRecycleView.LineAdapterHit;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HitActivity extends AppCompatActivity implements HitContract.View {

    @BindView(R.id.recycler_view_layout) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private LineAdapterHit mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private HitContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wave);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.hit));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new HitPresenter(RoomData.getInstance(this), this);

        setupRecycler();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.floatingActionButton)
    public void toolbarClick(){
        startActivity(new Intent(this, HitCreationActivity.class));
    }

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
        presenter.restartHitList();
        mAdapter = new LineAdapterHit(getSupportFragmentManager(), presenter);
        mRecyclerView.invalidate();
        mRecyclerView.setAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        super.onRestart();
    }

    private void setupRecycler(){
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new LineAdapterHit(getSupportFragmentManager(), presenter);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
