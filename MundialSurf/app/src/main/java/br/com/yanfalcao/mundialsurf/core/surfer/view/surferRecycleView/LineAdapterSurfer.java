package br.com.yanfalcao.mundialsurf.core.surfer.view.surferRecycleView;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import java.util.List;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.core.surfer.SurferContract;
import br.com.yanfalcao.mundialsurf.core.surferEdition.view.EditSurferActivity;

public class LineAdapterSurfer extends RecyclerView.Adapter<LineHolder> implements Filterable {

    private SurferContract.Presenter presenter;

    public LineAdapterSurfer(SurferContract.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LineHolder(LayoutInflater.from(viewGroup.getContext())
                                .inflate(R.layout.surfers_line_view, viewGroup, false));
    }

    @Override
    public int getItemCount() {
        return presenter.surfersCount();
    }

    @Override
    public void onBindViewHolder(LineHolder holder, final int i) {
        holder.name.setText(presenter.getSurferNameAt(i));
        holder.country.setText(presenter.getSurferCountryAt(i));
        holder.avatarImage.setImageResource(getAvatarImage(i));

        if(!presenter.isEmpty()){
            holder.setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EditSurferActivity.class);
                    Bundle extras = new Bundle();
                    extras.putInt("id", presenter.getSurferIdAt(i));

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
            FilterResults results = new FilterResults();
            results.values = presenter.filter(constraint);

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            presenter.setFilter((List)results.values);
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
