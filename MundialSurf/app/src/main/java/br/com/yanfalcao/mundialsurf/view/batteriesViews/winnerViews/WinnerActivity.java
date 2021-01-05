package br.com.yanfalcao.mundialsurf.view.batteriesViews.winnerViews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.yanfalcao.mundialsurf.R;

public class WinnerActivity extends AppCompatActivity {

    private TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        winner = findViewById(R.id.winner);
        winner.setText(getIntent().getStringExtra("winner"));
    }
}
