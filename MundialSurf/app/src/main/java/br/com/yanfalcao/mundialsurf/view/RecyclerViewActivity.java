package br.com.yanfalcao.mundialsurf.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.SurferController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import br.com.yanfalcao.mundialsurf.view.surfersViews.NewSurferActivity;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private LineAdapterSurfer mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mRecyclerView = findViewById(R.id.recycler_view_layout);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new LineAdapterSurfer(SurferController.selectSurfers(new DataBaseHelper(this), "id", "name", "country"));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    public void clickOnNewSurfers(View view) { startActivity(new Intent(this, NewSurferActivity.class)); }

    @Override
    protected void onRestart(){
        mAdapter = new LineAdapterSurfer(SurferController.selectSurfers(new DataBaseHelper(this), "id", "name", "country"));
        mRecyclerView.invalidate();
        mRecyclerView.setAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        super.onRestart();
    }
}
