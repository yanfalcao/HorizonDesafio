package br.com.yanfalcao.mundialsurf.view;

import android.animation.ArgbEvaluator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.yanfalcao.mundialsurf.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenu extends AppCompatActivity {

    @BindView(R.id.viewPager) ViewPager viewPager;
    PagerAdapter adapter;
    List<Map<String, Object>> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);

        modelInicialize();
        colorsInicialize();

        adapter = new MainMenuAdapter(models, this);

        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i < (adapter.getCount() - 1) && i < (colors.length - 1)) {
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    v,
                                    colors[i],
                                    colors[i+1]));
                }else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void modelInicialize(){
        Map<String, Object> item = new HashMap<>();
        models = new ArrayList<>();

        item.put("IMAGE", R.drawable.woman_surfer);
        item.put("TITLE", "Surfers Register");
        models.add(item);

        item = new HashMap<>();
        item.put("IMAGE", R.drawable.batterie);
        item.put("TITLE", "Battery Register");
        models.add(item);

        item = new HashMap<>();
        item.put("IMAGE", R.drawable.wave);
        item.put("TITLE", "Wave Register");
        models.add(item);

        item = new HashMap<>();
        item.put("IMAGE", R.drawable.win);
        item.put("TITLE", "Winner");
        models.add(item);
    }

    private void colorsInicialize(){
        Integer[] colors_helper = {
                getResources().getColor(R.color.surfer),
                getResources().getColor(R.color.battery),
                getResources().getColor(R.color.wave),
                getResources().getColor(R.color.win)
        };

        colors = colors_helper;
    }
}
