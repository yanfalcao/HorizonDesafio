package br.com.yanfalcao.mundialsurf.core.surfer.view.surferRecycleView;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import br.com.yanfalcao.mundialsurf.R;

public class LineHolder extends RecyclerView.ViewHolder{

    public TextView name;
    public TextView country;
    public ImageView setting;
    public ImageView avatarImage;

    public LineHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.namee);
        country = itemView.findViewById(R.id.countryy);
        setting = itemView.findViewById(R.id.setting);
        avatarImage = itemView.findViewById(R.id.avatar_image);
    }
}
