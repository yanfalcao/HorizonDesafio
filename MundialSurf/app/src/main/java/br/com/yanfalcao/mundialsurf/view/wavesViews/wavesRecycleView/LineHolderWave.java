package br.com.yanfalcao.mundialsurf.view.wavesViews.wavesRecycleView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.yanfalcao.mundialsurf.R;

public class LineHolderWave extends RecyclerView.ViewHolder{

    public TextView idBattery;
    public TextView surferOne;
    public TextView surferTwo;
    public ImageView infoImage;

    public LineHolderWave(@NonNull View itemView) {
        super(itemView);
        idBattery = itemView.findViewById(R.id.idBattery);
        surferOne = itemView.findViewById(R.id.surferOne);
        surferTwo = itemView.findViewById(R.id.surferTwo);
        infoImage = itemView.findViewById(R.id.info_icon);
    }
}
