package br.com.yanfalcao.mundialsurf.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.view.batteriesViews.NewBatteryActivity;
import br.com.yanfalcao.mundialsurf.view.notesViews.WavesChooseActivity;
import br.com.yanfalcao.mundialsurf.view.surfersViews.NewSurferActivity;
import br.com.yanfalcao.mundialsurf.view.surfersViews.SurfersActivity;
import br.com.yanfalcao.mundialsurf.view.wavesViews.NewWaveActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickOnSurfers(View v){
        startActivity(new Intent(this, SurfersActivity.class));
    }

    public void clickOnNewSurfers(View v){ startActivity(new Intent(this, NewSurferActivity.class)); }

    public void clickOnNewBattery(View view) { startActivity(new Intent(this, NewBatteryActivity.class)); }

    public void clickOnNewWave(View view) { startActivity(new Intent(this, NewWaveActivity.class)); }

    public void clickOnNewPunctuation(View view) { startActivity(new Intent(this, WavesChooseActivity.class)); }
}
