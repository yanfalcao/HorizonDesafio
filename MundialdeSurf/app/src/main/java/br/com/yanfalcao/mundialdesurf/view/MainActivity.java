package br.com.yanfalcao.mundialdesurf.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import br.com.yanfalcao.mundialdesurf.R;
import br.com.yanfalcao.mundialdesurf.view.batteriesViews.NewBatteryActivity;
import br.com.yanfalcao.mundialdesurf.view.surfersViews.NewSurferActivity;
import br.com.yanfalcao.mundialdesurf.view.surfersViews.SurfersActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickOnSurfers(View v){
           startActivity(new Intent(this, SurfersActivity.class));
    }

    public void clickOnNewSurfers(View v){
        startActivity(new Intent(this, NewSurferActivity.class));
    }

    public void clickOnNewBattery(View view) { startActivity(new Intent(this, NewBatteryActivity.class)); }
}
