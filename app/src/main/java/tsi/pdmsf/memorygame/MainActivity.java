package tsi.pdmsf.memorygame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Button> buttons = new ArrayList<>();

    private ConstraintLayout parent;
    private ProgressBar pb;
    private View congratulationsView;
    private TextView tvProgress;

    private int guessCount = 0;

    // Must have the same amount as the amount of buttons
    private final List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);

    // Must have at least the same amount as the amount of buttons
    private final List<Integer> colors = Arrays.asList(
            R.color.green_900,
            R.color.materialBlue,
            R.color.materialRed,
            R.color.deep_purple,
            R.color.deep_pink,
            R.color.deep_pink_1,
            R.color.deep_orange,
            R.color.deep_orange_1,
            R.color.gold,
            R.color.green_300
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupActionBar();

        initComponent();
        setUpButtons();
        start(true);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        return true;
    }

    private void initComponent() {
        parent = findViewById(R.id.parent);
        pb = findViewById(R.id.pb);
        congratulationsView = findViewById(R.id.congratulations_view);
        tvProgress = findViewById(R.id.tv_progress);

        buttons.add(findViewById(R.id.btn_one));
        buttons.add(findViewById(R.id.btn_two));
        buttons.add(findViewById(R.id.btn_three));
        buttons.add(findViewById(R.id.btn_four));
        buttons.add(findViewById(R.id.btn_five));
        buttons.add(findViewById(R.id.btn_six));
    }

    private void setUpButtons() {
        Button btnRestart = findViewById(R.id.btn_restart);
        btnRestart.setOnClickListener(btn -> start(true));
    }

    private void setUpGuessButtons() {
        if (colors.size() < buttons.size())
            throw new RuntimeException("Insufficient colors count");

        Collections.shuffle(colors);
        Collections.shuffle(buttons);
        Collections.shuffle(values);

        for (int i = 0; i < buttons.size(); i++) {
            Button btn = buttons.get(i);
            int color = colors.get(i);
            int guess = values.get(i);

            btn.setBackgroundColor(ContextCompat.getColor(this, color));
            btn.setOnClickListener(b -> takeGuess((Button) b, color, guess));
        }
    }

    private void takeGuess(@NotNull Button guessButton, @ColorRes int color, int guess) {

        if (!values.get(guessCount).equals(guess)) {
            start(false);
            return;
        }

        guessCount++;
        pb.setProgress(guessCount);
        guessButton.setVisibility(View.INVISIBLE);
        parent.setBackgroundResource(color);

        if (guessCount == values.size())
            congratulations();
    }

    private void start(boolean regenerateSequence) {
        parent.setBackgroundColor(Color.WHITE);
        congratulationsView.setVisibility(View.GONE);

        pb.setProgress(0);
        pb.setVisibility(View.VISIBLE);

        tvProgress.setVisibility(View.VISIBLE);

        guessCount = 0;
        if (regenerateSequence) {
            setUpGuessButtons();
        }

        for (Button button : buttons) {
            button.setVisibility(View.VISIBLE);
        }
    }

    private void congratulations() {
        congratulationsView.setVisibility(View.VISIBLE);
        pb.setVisibility(View.GONE);
        tvProgress.setVisibility(View.GONE);
    }
}