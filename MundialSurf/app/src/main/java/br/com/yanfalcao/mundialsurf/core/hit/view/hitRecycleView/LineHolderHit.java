package br.com.yanfalcao.mundialsurf.core.hit.view.hitRecycleView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.yanfalcao.mundialsurf.R;

public class LineHolderHit extends RecyclerView.ViewHolder{

    public TextView idHit;
    public TextView surferOne;
    public TextView surferTwo;
    public ImageView infoImage;

    public LineHolderHit(@NonNull View itemView) {
        super(itemView);
        idHit = itemView.findViewById(R.id.idHit);
        surferOne = itemView.findViewById(R.id.surferOne);
        surferTwo = itemView.findViewById(R.id.surferTwo);
        infoImage = itemView.findViewById(R.id.info_icon);
    }
}
