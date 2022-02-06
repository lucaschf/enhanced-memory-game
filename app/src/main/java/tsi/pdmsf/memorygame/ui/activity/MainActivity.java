package tsi.pdmsf.memorygame.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import tsi.pdmsf.memorygame.R;
import tsi.pdmsf.memorygame.model.GameEnvironment;
import tsi.pdmsf.memorygame.model.GameEnvironment.GuessCallback;
import tsi.pdmsf.memorygame.model.GameLevel;
import tsi.pdmsf.memorygame.ui.recyclerview.BlockAdapter;
import tsi.pdmsf.memorygame.ui.recyclerview.ItemOffsetDecoration;
import tsi.pdmsf.memorygame.ui.activity.HighscoreActivity;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout parent;
    private ProgressBar pb;
    private RecyclerView recyclerView;
    private Button btnHighscore;

    private GameEnvironment gameEnvironment;

    private GridLayoutManager layoutManager;
    private final BlockAdapter adapter = new BlockAdapter(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameEnvironment = new GameEnvironment(this);

        setupActionBar();

        initComponent();
        setupRecyclerView();
        setUpRestartButton();
        setupRadioGroup();

        pb.setOnLongClickListener(view -> easterEgg());
        gameEnvironment.startNew(this::resetProgress);
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
        recyclerView = findViewById(R.id.rv_blocks);
    }

    private void setupRecyclerView() {
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(this, R.dimen.item_margin);

        layoutManager = new GridLayoutManager(this, gameEnvironment.getSpanCount());

        recyclerView.addItemDecoration(itemOffsetDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setListener(block -> gameEnvironment.takeGuess(block, new GuessCallback() {
            @Override
            public void onCorrectGuess() {
                block.setVisible(false);
                parent.setBackgroundResource(block.getColorRes());
                adapter.notifyItemChanged(block.getValue() - 1);

                pb.setProgress(block.getOrder());

                if (gameEnvironment.won()) {
                    congratulations();
                }
            }

            @Override
            public void onIncorrectGuess() {
                resetProgress();
            }
        }));
    }

    private void setUpRestartButton() {
        Button btnRestart = findViewById(R.id.btn_restart);
        btnRestart.setOnClickListener(btn -> gameEnvironment.startNew(this::resetProgress));
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

            gameEnvironment.changeLevel(newLevel, this::resetProgress);
        });
    }

    private void resetProgress() {
        parent.setBackgroundResource(R.color.white);
        layoutManager.setSpanCount(gameEnvironment.getSpanCount());
        pb.setProgress(0);
        pb.setMax(gameEnvironment.getBlocksCount());
        adapter.updateItems(gameEnvironment.getBlocks());
    }

    private void congratulations() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_congratulations, viewGroup, false);
        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.setOnDismissListener(dialogInterface -> findViewById(R.id.btn_restart).performClick());

        dialogView.findViewById(R.id.btn_go_back_to_game).setOnClickListener(view -> alertDialog.dismiss());
        alertDialog.show();
    }

    private boolean easterEgg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_easter_egg, viewGroup, false);
        builder.setView(dialogView);

        builder.setPositiveButton(android.R.string.ok, null);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.setTitle(R.string.blocks_sequence);

        RecyclerView rvEasterEgg = dialogView.findViewById(R.id.rv_easter_egg);
        rvEasterEgg.setHasFixedSize(true);

        BlockAdapter adapter = new BlockAdapter(gameEnvironment.getBlocks(), false);
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(this, R.dimen.item_margin);
        GridLayoutManager layoutManager = new GridLayoutManager(this, gameEnvironment.getSpanCount());

        rvEasterEgg.addItemDecoration(itemOffsetDecoration);
        rvEasterEgg.setLayoutManager(layoutManager);
        rvEasterEgg.setAdapter(adapter);
        rvEasterEgg.setEnabled(false);

        alertDialog.show();

        return false;
    }
}