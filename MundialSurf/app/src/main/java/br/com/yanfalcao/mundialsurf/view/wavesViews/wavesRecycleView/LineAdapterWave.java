package br.com.yanfalcao.mundialsurf.view.wavesViews.wavesRecycleView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import br.com.yanfalcao.mundialsurf.view.wavesViews.RegisterWaveActivity;

public class LineAdapterWave extends RecyclerView.Adapter<LineHolderWave> implements Filterable {
    private List<Map<String, Object>> mBatteries;
    private List<Map<String, Object>> fullBatteries;

    public LineAdapterWave(List<Map<String, Object>> batteries){
        mBatteries = batteries;
        fullBatteries = new ArrayList<>(batteries);
    }

    @NonNull
    @Override
    public LineHolderWave onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LineHolderWave(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.hit_line_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LineHolderWave holder, int i) {
        int count = i + 1;
        final Map<String, Object> map = mBatteries.get(i);

        holder.idBattery.setText(String.valueOf(count));
        holder.surferOne.setText(map.get("surfer1").toString());
        holder.surferTwo.setText(map.get("surfer2").toString());

        if(! map.get("surfer1").toString().equals("Empty")){
            holder.infoImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(new Intent(v.getContext(), RegisterWaveActivity.class));
                    Bundle extras = new Bundle();

                    extras.putString("idSurferOne", map.get("idSurferOne").toString());
                    extras.putString("idSurferTwo", map.get("idSurferTwo").toString());
                    extras.putString("nameSurferOne", map.get("surfer1").toString());
                    extras.putString("nameSurferTwo", map.get("surfer2").toString());
                    extras.putString("idBattery", map.get("idBattery").toString());

                    intent.putExtras(extras);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mBatteries != null ? mBatteries.size() : 0;
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
                filteredList.addAll(fullBatteries);
            else{
                String filter = constraint.toString().toLowerCase().trim();

                for(Map<String, Object> item : fullBatteries) {
                    if(item.get("surfer1").toString().toLowerCase().contains(filter)) {
                        filteredList.add(item);
                    }
                    if(item.get("surfer2").toString().toLowerCase().contains(filter)) {
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
            mBatteries.clear();
            mBatteries.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
