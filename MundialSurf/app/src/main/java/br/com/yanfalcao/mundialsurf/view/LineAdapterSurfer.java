package br.com.yanfalcao.mundialsurf.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import br.com.yanfalcao.mundialsurf.R;

public class LineAdapterSurfer extends RecyclerView.Adapter<LineHolder> {

    private final List<Map<String, Object>> mUsers;

    public LineAdapterSurfer(List<Map<String, Object>> users){
        mUsers = users;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LineHolder(LayoutInflater.from(viewGroup.getContext())
                                .inflate(R.layout.main_line_view, viewGroup, false));
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    @Override
    public void onBindViewHolder(LineHolder holder, int i) {
        holder.id.setText(mUsers.get(i).get("id").toString());
        holder.name.setText(mUsers.get(i).get("name").toString());
        holder.country.setText(mUsers.get(i).get("country").toString());
    }
}
