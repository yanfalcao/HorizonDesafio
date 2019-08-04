package br.com.yanfalcao.mundialsurf.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import br.com.yanfalcao.mundialsurf.R;

public class LineHolder extends RecyclerView.ViewHolder{

    public TextView id;
    public TextView name;
    public TextView country;

    public LineHolder(View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.idd);
        name = itemView.findViewById(R.id.namee);
        country = itemView.findViewById(R.id.countryy);
    }
}
