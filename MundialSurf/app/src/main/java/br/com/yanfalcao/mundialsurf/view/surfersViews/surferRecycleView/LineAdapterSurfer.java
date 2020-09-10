package br.com.yanfalcao.mundialsurf.view.surfersViews.surferRecycleView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.SurferController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import br.com.yanfalcao.mundialsurf.view.surfersViews.EditSurferActivity;

public class LineAdapterSurfer extends RecyclerView.Adapter<LineHolder> implements Filterable {

    private final List<Map<String, Object>> mUsers;
    private  final List<Map<String, Object>> fullUsers;

    public LineAdapterSurfer(List<Map<String, Object>> users){
        mUsers = users;
        fullUsers = new ArrayList<>(users);
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
        holder.name.setText(mUsers.get(i).get("name").toString());
        holder.country.setText(mUsers.get(i).get("country").toString());
        holder.avatarImage.setImageResource(getAvatarImage(i));

        if(mUsers.get(i).get("name").toString() != "Empty"){
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

    @Override
    public Filter getFilter() {
        return itemFilter;
    }

    private Filter itemFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Map<String, Object>> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0)
                filteredList.addAll(fullUsers);
            else{
                String filter = constraint.toString().toLowerCase().trim();

                for(Map<String, Object> item : fullUsers) {
                    if(item.get("country").toString().toLowerCase().contains(filter)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mUsers.clear();
            mUsers.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    private int getAvatarImage(int position){
        int rest = (position + 1) % 4;

        if(rest == 1){
            return R.drawable.ic_flowers;
        }else if(rest == 2){
            return R.drawable.ic_mini_van;
        }else if(rest == 3){
            return R.drawable.ic_hand;
        }else {
            return R.drawable.ic_surf_board;
        }
    }
}
