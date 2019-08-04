package br.com.yanfalcao.mundialsurf.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import br.com.yanfalcao.mundialsurf.R;
import br.com.yanfalcao.mundialsurf.view.batteriesViews.NewBatteryActivity;
import br.com.yanfalcao.mundialsurf.view.notesViews.NewPunctuacionActivity;
import br.com.yanfalcao.mundialsurf.view.surfersViews.NewSurferActivity;
import br.com.yanfalcao.mundialsurf.view.surfersViews.SurfersListActivity;
import br.com.yanfalcao.mundialsurf.view.wavesViews.NewWaveActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickOnNewBattery(View view) { startActivity(new Intent(this, NewBatteryActivity.class)); }

    public void clickOnNewWave(View view) { startActivity(new Intent(this, NewWaveActivity.class)); }

    public void clickOnNewPunctuation(View view) { startActivity(new Intent(this, NewPunctuacionActivity.class)); }

    public void clickOnSurfers(View view) { startActivity(new Intent(this, SurfersListActivity.class)); }
}
