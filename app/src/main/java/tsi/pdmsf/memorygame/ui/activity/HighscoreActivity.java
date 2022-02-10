package tsi.pdmsf.memorygame.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import tsi.pdmsf.memorygame.R;
import tsi.pdmsf.memorygame.model.GameLevel;
import tsi.pdmsf.memorygame.model.Score;
import tsi.pdmsf.memorygame.repository.IRepository;
import tsi.pdmsf.memorygame.utils.Store;

public class HighscoreActivity extends AppCompatActivity {
    private ListView  listView;

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

    private void loadHighscore(int level){
        listView = (ListView) findViewById(R.id.lista);

        IRepository db = Store.repository;

        List<Score> players = db.fetch(level);
        String [] array = new String[players.size()];

        for(int i = 0; i < players.size(); i++) {
            array[i] = String.format("%dº - %s - Score: %s ", i+1, players.get(i).getName(), players.get(i).getScore());
        }

        if(array.length == 0) array = new String [] {"Nenhum resultado"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);

        listView.setAdapter(adapter);
    }

    private void initComponent() {
        //parent = findViewById(R.id.parent);
        //pb = findViewById(R.id.pb);
        //recyclerView = findViewById(R.id.rv_blocks);
        //handleHighscore();
    }


}
