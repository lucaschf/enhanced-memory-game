package tsi.pdmsf.memorygame.model;

public enum GameLevel {

    EASY(4, 2),
    MEDIUM(6, 2),
    HARD(9, 3);

    private final int blocksCount;
    private final int preferredSpanCount;

    GameLevel(int blocksCount, int preferredSpanCount) {
        this.blocksCount = blocksCount;
        this.preferredSpanCount = preferredSpanCount;
    }

    public int getBlocksCount() {
        return blocksCount;
    }

    public int getPreferredSpanCount() {
        return preferredSpanCount;
    }
}
