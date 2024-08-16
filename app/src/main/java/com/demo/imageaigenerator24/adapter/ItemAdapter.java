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
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<ItemModel> items;
    private OnStyleClickListener listener;
    private int selectedPosition = 0;

    /* loaded from: classes.dex */
    public interface OnStyleClickListener {
        void onStyleSelected(String str);
    }

    public void setOnStyleClickListener(OnStyleClickListener onStyleClickListener) {
        this.listener = onStyleClickListener;
    }

    public ItemAdapter(List<ItemModel> list) {
        this.items = list;
        if (list.isEmpty()) {
            return;
        }
        list.get(0).setSelected(true);
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
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.adapter.ItemAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i2 = ItemAdapter.this.selectedPosition;
                if (i2 >= 0) {
                    ((ItemModel) ItemAdapter.this.items.get(i2)).setSelected(false);
                    ItemAdapter itemAdapter = ItemAdapter.this;
                    itemAdapter.notifyItemChanged(itemAdapter.selectedPosition);
                }
                itemModel.setSelected(true);
                ItemAdapter.this.selectedPosition = i;
                ItemAdapter.this.notifyItemChanged(i);
                OnStyleClickListener onStyleClickListener = ItemAdapter.this.listener;
                if (onStyleClickListener != null) {
                    onStyleClickListener.onStyleSelected(itemModel.getText());
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
