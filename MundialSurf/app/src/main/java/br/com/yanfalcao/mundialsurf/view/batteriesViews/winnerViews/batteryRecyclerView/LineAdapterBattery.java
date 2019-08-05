package br.com.yanfalcao.mundialsurf.view.batteriesViews.winnerViews.batteryRecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.controller.BatteryController;
import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import br.com.yanfalcao.mundialsurf.view.batteriesViews.winnerViews.WinnerActivity;

public class LineAdapterBattery extends RecyclerView.Adapter<LineHolderBattery> {

    private final List<Map<String, Object>> mUsers;

    public LineAdapterBattery(List<Map<String, Object>> users){
        mUsers = users;
    }

    @Override
    public LineHolderBattery onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LineHolderBattery(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.batteries_line_view, viewGroup, false));
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    @Override
    public void onBindViewHolder(LineHolderBattery holder, final int i) {
        holder.id.setText(mUsers.get(i).get("id").toString());
        holder.surferOne.setText(mUsers.get(i).get("surferOne").toString());
        holder.surferTwo.setText(mUsers.get(i).get("surferTwo").toString());
        holder.podio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WinnerActivity.class);
                Bundle extras = new Bundle();

                int result = BatteryController.getWinner(new DataBaseHelper(v.getContext()), mUsers.get(i).get("id").toString());

                if(result == -1)
                    Toast.makeText(v.getContext(), "SURFER ONE NEED MORE NOTES", Toast.LENGTH_SHORT).show();
                else if(result == -2)
                    Toast.makeText(v.getContext(), "SURFER TWO NEED MORE NOTES", Toast.LENGTH_SHORT).show();
                else if(result == 1){
                    extras.putString("winner", mUsers.get(i).get("surferOne").toString());
                    intent.putExtras(extras);
                    v.getContext().startActivity(intent);
                }
                else{
                    extras.putString("winner", mUsers.get(i).get("surferTwo").toString());
                    intent.putExtras(extras);
                    v.getContext().startActivity(intent);
                }
            }
        });
    }
}
