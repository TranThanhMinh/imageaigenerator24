package com.demo.imageaigenerator24.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.model.ItemModel;
import java.util.List;

/* loaded from: classes.dex */
public class ItemModelAdapter extends RecyclerView.Adapter<ItemModelAdapter.ItemViewHolder> {
    private List<ItemModel> items;
    private OnItemClickListener listener;
    private int selectedPosition = 0;

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(ItemModel itemModel);
    }

    public ItemModelAdapter(List<ItemModel> list) {
        this.items = list;
        if (list.isEmpty()) {
            return;
        }
        list.get(0).setSelected(true);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ItemViewHolder itemViewHolder, final int i) {
        final ItemModel itemModel = this.items.get(i);
        Glide.with(itemViewHolder.itemView.getContext()).load("file:///android_asset/" + itemModel.getImageResId()).into(itemViewHolder.imageView);
        itemViewHolder.textView.setText(itemModel.getText());
        if (i == this.selectedPosition) {
            itemViewHolder.selectionView.setBackgroundResource(R.drawable.selected_star);
        } else {
            itemViewHolder.selectionView.setBackgroundResource(R.drawable.not_selected_star);
        }
        itemViewHolder.textView.setText(itemModel.getText());
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.adapter.ItemModelAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i2 = ItemModelAdapter.this.selectedPosition;
                if (i2 >= 0) {
                    ((ItemModel) ItemModelAdapter.this.items.get(i2)).setSelected(false);
                    ItemModelAdapter itemModelAdapter = ItemModelAdapter.this;
                    itemModelAdapter.notifyItemChanged(itemModelAdapter.selectedPosition);
                }
                itemModel.setSelected(true);
                ItemModelAdapter.this.selectedPosition = i;
                ItemModelAdapter.this.notifyItemChanged(i);
                OnItemClickListener onItemClickListener = ItemModelAdapter.this.listener;
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(itemModel);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    /* loaded from: classes.dex */
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        View selectionView;
        TextView textView;

        public ItemViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.itemImageView);
            this.textView = (TextView) view.findViewById(R.id.itemTextView);
            this.selectionView = view.findViewById(R.id.selectionView);
        }
    }
}
