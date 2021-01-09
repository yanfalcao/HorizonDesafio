package br.com.yanfalcao.mundialsurf.core.main.view;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.core.surfer.view.SurfersListActivity;
import br.com.yanfalcao.mundialsurf.core.hit.view.HitActivity;

public class MainMenuAdapter extends PagerAdapter {

    private List<Map<String, Object>> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public MainMenuAdapter(List<Map<String, Object>> models, Context context){
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_main_menu, container, false);

        ImageView image = view.findViewById(R.id.image_main_menu);
        TextView title = view.findViewById(R.id.title_main_menu);

        image.setImageResource((int) models.get(position).get("IMAGE"));
        title.setText((String) models.get(position).get("TITLE"));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(position){
                    case 0:
                        context.startActivity(new Intent(context, SurfersListActivity.class));
                        break;

                    case 1:
                        context.startActivity(new Intent(context, HitActivity.class));
                        break;
                }
            }
        });

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
