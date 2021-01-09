package br.com.yanfalcao.mundialsurf.core.hit.view.hitRecycleView;

import androidx.annotation.NonNull;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.core.hit.HitContract;
import br.com.yanfalcao.mundialsurf.core.hit.view.fragment.ScoreBottomSheetDialog;
import br.com.yanfalcao.mundialsurf.core.hitCreation.presenter.HitCreationPresenter;

public class LineAdapterHit extends RecyclerView.Adapter<LineHolderHit> implements Filterable {
    private HitContract.Presenter presenter;
    private FragmentManager fragmentManager;

    public LineAdapterHit(FragmentManager fragmentManager, HitContract.Presenter presenter){
        this.fragmentManager = fragmentManager;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public LineHolderHit onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LineHolderHit(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.hit_line_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LineHolderHit holder, int i) {
        int count = i + 1;

        holder.idBattery.setText(String.valueOf(count));
        holder.surferOne.setText(presenter.surferOneName(i));
        holder.surferTwo.setText(presenter.surferTwoName(i));

        if(! presenter.isEmptyHits()){
            holder.infoImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScoreBottomSheetDialog bottomSheet = new ScoreBottomSheetDialog(presenter, i);
                    bottomSheet.show(fragmentManager, "scoreBottomSheet");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return presenter.getHitsCount();
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
            presenter.setHitsFilter((List)results.values);
            notifyDataSetChanged();
        }
    };
}
