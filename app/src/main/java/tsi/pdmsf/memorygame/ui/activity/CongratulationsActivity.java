package tsi.pdmsf.memorygame.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import org.jetbrains.annotations.NotNull;

import tsi.pdmsf.memorygame.R;
import tsi.pdmsf.memorygame.model.PointControl;
import tsi.pdmsf.memorygame.model.Scoreboard;
import tsi.pdmsf.memorygame.model.enums.GameLevel;
import tsi.pdmsf.memorygame.repository.AppDatabase;

public class CongratulationsActivity extends AppCompatActivity {

    public static final String ARG_LEVEL = "arg.level";
    public static final String ARG_CONTROL = "arg.control";
    private EditText edName;
    private GameLevel gameLevel;
    private PointControl pointControl;

    public static void openCongratulationsActivity(@NotNull Activity activity,
                                                   @NotNull GameLevel gameLevel,
                                                   @NotNull PointControl pointControl) {
        Intent intent = new Intent(activity, CongratulationsActivity.class);
        intent.putExtra(ARG_LEVEL, gameLevel);
        intent.putExtra(ARG_CONTROL, pointControl);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);

        Bundle extras = getIntent().getExtras();
        gameLevel = (GameLevel) extras.get(ARG_LEVEL);
        pointControl = (PointControl) extras.get(ARG_CONTROL);

        initComponent();
        displayData();
    }

    private void initComponent() {
        edName = findViewById(R.id.nameScore);

        findViewById(R.id.btn_go_back_to_game)
                .setOnClickListener(v -> NavUtils.navigateUpFromSameTask(CongratulationsActivity.this));
        findViewById(R.id.buttonScore).setOnClickListener(v -> persist());
    }

    private void displayData() {
        ((TextView) findViewById(R.id.score)).setText(String.valueOf(pointControl.calcularPontos()));
    }

    private void persist() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.setDifficulty(gameLevel);
        scoreboard.setErrors(pointControl.getErrors());
        scoreboard.setPunctuation((double) pointControl.calcularPontos());
        scoreboard.setTime(pointControl.gameTimeInSeconds());

        String informedName = edName.getText().toString();
        scoreboard.setNameUser(informedName.isEmpty() ? "Player" : informedName);

        AppDatabase.getInstance(this).SRDao().insert(scoreboard);
        HighScoreActivity.openHighScoreActivity(this);
    }
}