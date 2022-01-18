package tsi.pdmsf.memorygame.ui.recyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {


    private final int margin;

    public ItemOffsetDecoration(@NotNull Context context, @DimenRes int margin) {
        this.margin = context.getResources().getDimensionPixelSize(margin);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect,
                               @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        outRect.set(margin, margin, margin, margin);
    }
}
