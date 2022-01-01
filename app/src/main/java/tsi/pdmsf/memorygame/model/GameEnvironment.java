package tsi.pdmsf.memorygame.model;

import android.content.Context;
import android.content.res.TypedArray;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

import tsi.pdmsf.memorygame.ColorSchemePreferencePersistence;
import tsi.pdmsf.memorygame.R;

public class GameEnvironment {

    private ArrayList<Block> blocks = new ArrayList<>();
    private GameLevel level = GameLevel.EASY;
    private Block lastCorrectBlock = null;
    private final ArrayList<Integer> values = new ArrayList<>();
    private final ArrayList<Integer> colors = new ArrayList<>();
    ColorSchemePreferencePersistence colorSchemePersistence;
    private final Context context;

    public GameEnvironment(@NotNull Context context) {
        this.context = context;
        populateValuesList();
        colorSchemePersistence = new ColorSchemePreferencePersistence(context);
    }

    private void populateBlockColorList() {
        TypedArray colorsArray;
        colors.clear();

        if (colorSchemePersistence.getCurrentColorScheme().equals(context.getString(R.string.default_color_scheme_value))) {
            colorsArray = context.getResources().obtainTypedArray(R.array.default_blocks_color);
        } else
            colorsArray = context.getResources().obtainTypedArray(R.array.accessible_blocks_color);

        for (int i = 0; i < colorsArray.length(); i++) {
            colors.add(colorsArray.getResourceId(i, 0));
        }

        colorsArray.recycle();
    }

    private void populateValuesList() {
        values.clear();

        for (int i = 1; i <= level.getBlocksCount(); i++) {
            values.add(i);
        }
    }

    public void takeGuess(@NotNull Block block, @NotNull GuessCallback callback) {
        if (isTheCorrectBlock(block)) {
            lastCorrectBlock = block;
            callback.onCorrectGuess();
        } else {
            restart();
            callback.onIncorrectGuess();
        }
    }

    private boolean isTheCorrectBlock(@NotNull Block block) {
        return block.getOrder() == 1 || (lastCorrectBlock != null &&
                block.getOrder() == lastCorrectBlock.getOrder() + 1);
    }

    public boolean won() {
        return lastCorrectBlock != null && lastCorrectBlock.getOrder() == level.getBlocksCount();
    }

    public void changeLevel(GameLevel level, @NotNull OnStartCallback startCallback) {
        this.level = level;
        startNew(startCallback);
    }

    public int getSpanCount() {
        return level.getPreferredSpanCount();
    }

    public void startNew(@NotNull OnStartCallback onStartCallback) {
        lastCorrectBlock = null;
        blocks = new ArrayList<>();

        populateBlockColorList();
        populateValuesList();

        Collections.shuffle(values);
        Collections.shuffle(colors);

        blocks.clear();

        for (int i = 0; i < level.getBlocksCount(); i++) {
            blocks.add(new Block(i + 1, values.get(i), colors.get(i)));
        }

        onStartCallback.execute();
    }

    public void restart() {
        lastCorrectBlock = null;

        for (Block b : blocks) {
            b.setVisible(true);
        }
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public int getBlocksCount() {
        return level.getBlocksCount();
    }

    public interface GuessCallback {
        void onCorrectGuess();

        void onIncorrectGuess();
    }

    public interface OnStartCallback {
        void execute();
    }
}
