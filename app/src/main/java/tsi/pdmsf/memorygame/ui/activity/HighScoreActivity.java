package tsi.pdmsf.memorygame.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import tsi.pdmsf.memorygame.R;
import tsi.pdmsf.memorygame.model.Scoreboard;
import tsi.pdmsf.memorygame.model.enums.GameLevel;
import tsi.pdmsf.memorygame.repository.AppDatabase;
import tsi.pdmsf.memorygame.repository.ScoreboardRepository;

public class HighScoreActivity extends AppCompatActivity {
    private AppDatabase repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = AppDatabase.getInstance(this);
        setContentView(R.layout.activity_highscore);

        setupRadioGroup();
        loadHighscore(GameLevel.EASY);
    }

    private void setupRadioGroup() {
        RadioGroup gpGameLevel = findViewById(R.id.gp_game_level);
        gpGameLevel.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            GameLevel newLevel;

            if (checkedId == R.id.rd_easy) {
                newLevel = GameLevel.EASY;
            } else if (checkedId == R.id.rd_medium) {
                newLevel = GameLevel.MEDIUM;
            } else {
                newLevel = GameLevel.HARD;
            }

            loadHighscore(newLevel);
        });
    }

    public static void openHighScoreActivity(@NotNull Activity activity) {
        activity.startActivity(new Intent(activity, HighScoreActivity.class));
    }

    @SuppressLint("DefaultLocale")
    private void loadHighscore(GameLevel level) {
        ListView listView = findViewById(R.id.lista);

        List<Scoreboard> scores = repository.SRDao().findAllByDifficulty(level);
        String[] array = new String[scores.size()];

        for (int i = 0; i < scores.size(); i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
            array[i] = String.format("%dº - %s - Score: %s - %s",
                    i + 1,
                    scores.get(i).getNameUser(),
                    scores.get(i).getPunctuation(),
                    formatter.format(scores.get(i).getDateTime()));

        }

        if (array.length == 0) {
            array = new String[]{"Nenhum resultado"};
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);

        listView.setAdapter(adapter);
    }
}
