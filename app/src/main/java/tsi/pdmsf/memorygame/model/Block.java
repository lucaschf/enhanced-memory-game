package tsi.pdmsf.memorygame.model;

import androidx.annotation.ColorRes;

public class Block {

    private final int value;
    private final int order;
    private boolean visible = true;

    @ColorRes
    private final int colorRes;

    public Block(int value, int order, int colorRes) {
        this.value = value;
        this.order = order;
        this.colorRes = colorRes;
    }

    public int getValue() {
        return value;
    }

    public int getOrder() {
        return order;
    }

    public int getColorRes() {
        return colorRes;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
