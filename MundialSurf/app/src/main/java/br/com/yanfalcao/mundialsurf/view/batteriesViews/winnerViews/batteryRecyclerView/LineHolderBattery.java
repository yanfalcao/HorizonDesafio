package br.com.yanfalcao.mundialsurf.view.batteriesViews.winnerViews.batteryRecyclerView;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.yanfalcao.mundialsurf.R;

public class LineHolderBattery extends RecyclerView.ViewHolder{

    public TextView id;
    public TextView surferOne;
    public TextView surferTwo;
    public ImageView podio;

    public LineHolderBattery(View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.BatteryList);
        surferOne = itemView.findViewById(R.id.surferOneList);
        surferTwo = itemView.findViewById(R.id.surferTwoList);
        podio = itemView.findViewById(R.id.podio);
    }
}
