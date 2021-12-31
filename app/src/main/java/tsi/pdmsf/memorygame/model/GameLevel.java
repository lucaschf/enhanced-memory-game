package tsi.pdmsf.memorygame.model;

public enum GameLevel {

    EASY(4, 2),
    MEDIUM(6, 2),
    HARD(9, 3);

    private final int blocks;
    private final int preferredSpanCount;

    GameLevel(int blocks, int preferredSpanCount) {
        this.blocks = blocks;
        this.preferredSpanCount = preferredSpanCount;
    }

    public int getBlocks() {
        return blocks;
    }

    public int getPreferredSpanCount() {
        return preferredSpanCount;
    }
}
