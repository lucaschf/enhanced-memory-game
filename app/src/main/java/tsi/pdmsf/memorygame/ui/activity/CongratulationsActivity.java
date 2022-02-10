package tsi.pdmsf.memorygame.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tsi.pdmsf.memorygame.R;

public class CongratulationsActivity extends AppCompatActivity {

    private Button btnRestart = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);

        setUpRestartButton();
        //setUpScoreButton();
    }

    private void setUpRestartButton() {

        btnRestart = findViewById(R.id.btn_go_back_to_game);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpScoreButton() {

    }

}