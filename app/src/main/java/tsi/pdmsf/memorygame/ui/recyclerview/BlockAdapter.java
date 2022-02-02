package tsi.pdmsf.memorygame.ui.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import tsi.pdmsf.memorygame.R;
import tsi.pdmsf.memorygame.model.Block;

public class BlockAdapter extends RecyclerView.Adapter<BlockAdapter.BlockViewHolder> {

    private final ArrayList<Block> blocks = new ArrayList<>();
    private OnItemClickListener<Block> listener;
    private final boolean interactive;


    public BlockAdapter(boolean interactive) {
        this.interactive = interactive;
    }


    public BlockAdapter(Collection<Block> blocks, boolean interactive) {
        this.blocks.addAll(blocks);
        this.interactive = interactive;
    }

    public void setListener(OnItemClickListener<Block> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_block, parent, false);

        return new BlockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlockViewHolder holder, int position) {
        Block block = blocks.get(position);
        holder.bind(block);

        if (interactive) {
            holder.itemView.setOnClickListener(v -> listener.onItemClicked(block));
            holder.itemView.setOnLongClickListener(v -> listener.onLongItemClicked(block));
        }
    }

    @Override
    public int getItemCount() {
        return blocks.size();
    }

    public void updateItems(ArrayList<Block> blocks) {
        this.blocks.clear();
        this.blocks.addAll(blocks);

        notifyDataSetChanged();
    }

    public class BlockViewHolder extends RecyclerView.ViewHolder {
        private final CardView cvBlock;
        private final TextView tvBlock;

        private final Context mContext;

        public BlockViewHolder(@NonNull View itemView) {
            super(itemView);

            mContext = itemView.getContext();

            cvBlock = itemView.findViewById(R.id.cv_block);
            tvBlock = itemView.findViewById(R.id.tv_block_value);
        }

        public void bind(@NotNull Block block) {
            if (interactive) {
                tvBlock.setText(String.valueOf(block.getValue()));
            } else {
                tvBlock.setText(String.valueOf(block.getOrder()));
            }

            cvBlock.setCardBackgroundColor(ContextCompat.getColor(mContext, block.getColorRes()));
            if (interactive)
                cvBlock.setVisibility(block.isVisible() ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
