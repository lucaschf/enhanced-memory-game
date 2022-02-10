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

import java.util.List;

import tsi.pdmsf.memorygame.R;
import tsi.pdmsf.memorygame.model.Scoreboard;
import tsi.pdmsf.memorygame.repository.AppDatabase;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        setupRadioGroup();
        loadHighscore(0);
    }

    private void setupRadioGroup() {
        RadioGroup gpGameLevel = findViewById(R.id.gp_game_level);
        gpGameLevel.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            int newLevel;

            if (checkedId == R.id.rd_easy) {
                newLevel = 0;
            } else if (checkedId == R.id.rd_medium) {
                newLevel = 1;
            } else {
                newLevel = 2;
            }

            loadHighscore(newLevel);
        });
    }

    public static void openHighScoreActivity(@NotNull Activity activity) {
        activity.startActivity(new Intent(activity, HighScoreActivity.class));
    }

    @SuppressLint("DefaultLocale")
    private void loadHighscore(int level) {
        ListView listView = findViewById(R.id.lista);

        List<Scoreboard> scores = AppDatabase.getInstance(this).SRDao().findAll();
        String[] array = new String[scores.size()];

        for (int i = 0; i < scores.size(); i++) {
            array[i] = String.format("%dº - %s - Score: %s ",
                    i + 1,
                    scores.get(i).getNameUser(),
                    scores.get(i).getPunctuation());
        }

        if (array.length == 0) {
            array = new String[]{"Nenhum resultado"};
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);

        listView.setAdapter(adapter);
    }
}
