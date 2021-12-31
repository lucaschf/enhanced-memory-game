package tsi.pdmsf.memorygame.ui.recyclerview;


public interface OnItemClickListener<E> {
    void onItemClicked(E item);

    default boolean onLongItemClicked(E item) {
        return false;
    }
}