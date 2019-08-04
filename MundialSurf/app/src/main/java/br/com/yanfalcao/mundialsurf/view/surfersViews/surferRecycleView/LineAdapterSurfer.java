package br.com.yanfalcao.mundialsurf.view.surfersViews.surferRecycleView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.SurferController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import br.com.yanfalcao.mundialsurf.view.surfersViews.EditSurferActivity;

public class LineAdapterSurfer extends RecyclerView.Adapter<LineHolder> {

    private final List<Map<String, Object>> mUsers;

    public LineAdapterSurfer(List<Map<String, Object>> users){
        mUsers = users;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LineHolder(LayoutInflater.from(viewGroup.getContext())
                                .inflate(R.layout.surfers_line_view, viewGroup, false));
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    @Override
    public void onBindViewHolder(LineHolder holder, final int i) {
        holder.id.setText(mUsers.get(i).get("id").toString());
        holder.name.setText(mUsers.get(i).get("name").toString());
        holder.country.setText(mUsers.get(i).get("country").toString());
        holder.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditSurferActivity.class);
                Bundle extras = new Bundle();
                extras.putString("id", mUsers.get(i).get("id").toString());
                extras.putString("name", mUsers.get(i).get("name").toString());
                extras.putString("country", mUsers.get(i).get("country").toString());

                intent.putExtras(extras);
                v.getContext().startActivity(intent);
            }
        });
        holder.trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SurferController.deleteSurfer(new DataBaseHelper(v.getContext()), mUsers.get(i).get("id").toString());
                mUsers.remove(i);
                notifyItemRemoved(i);
                notifyItemChanged(i, mUsers.size());
            }
        });
    }
}
