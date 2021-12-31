package tsi.pdmsf.memorygame.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tsi.pdmsf.memorygame.R;

public class GameEnvironment {

    private ArrayList<Block> blocks = new ArrayList<>();
    private GameLevel level = GameLevel.EASY;
    private Block lastCorrectBlock = null;
    private final ArrayList<Integer> values = new ArrayList<>();

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

    public GameEnvironment() {
        populateValuesList();
    }

    private void populateValuesList() {
        values.clear();

        for (int i = 1; i <= level.getBlocks(); i++) {
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
        return lastCorrectBlock != null && lastCorrectBlock.getOrder() == level.getBlocks();
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

        populateValuesList();

        Collections.shuffle(values);
        Collections.shuffle(colors);

        blocks.clear();

        for (int i = 0; i < level.getBlocks(); i++) {
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

    public interface GuessCallback {
        void onCorrectGuess();

        void onIncorrectGuess();
    }

    public interface OnStartCallback {
        void execute();
    }
}
