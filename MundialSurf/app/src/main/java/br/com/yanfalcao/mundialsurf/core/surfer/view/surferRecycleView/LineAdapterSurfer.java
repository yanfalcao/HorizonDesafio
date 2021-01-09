package br.com.yanfalcao.mundialsurf.core.surfer.view.surferRecycleView;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.core.surferEdition.view.EditSurferActivity;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

public class LineAdapterSurfer extends RecyclerView.Adapter<LineHolder> implements Filterable {

    private final List<Surfer> mUsers;
    private  final List<Surfer> fullUsers;

    public LineAdapterSurfer(List<Surfer> users){
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
        holder.name.setText(mUsers.get(i).getName());
        holder.country.setText(mUsers.get(i).getCountry());
        holder.avatarImage.setImageResource(getAvatarImage(i));

        if(mUsers.get(i).getName() != "Empty"){
            holder.setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EditSurferActivity.class);
                    Bundle extras = new Bundle();
                    extras.putInt("id", mUsers.get(i).getId());

                    intent.putExtras(extras);
                    v.getContext().startActivity(intent);
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
            List<Surfer> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0)
                filteredList.addAll(fullUsers);
            else{
                String filter = constraint.toString().toLowerCase().trim();

                for(Surfer surfer : fullUsers) {
                    if(surfer.getCountry().toLowerCase().contains(filter) || surfer.getName().toLowerCase().contains(filter)) {
                        filteredList.add(surfer);
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
