package com.demo.imageaigenerator24.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.model.StylesItemModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* loaded from: classes.dex */
public class StylesAdpaterBottom extends RecyclerView.Adapter<StylesAdpaterBottom.ViewHolder> {
    private List<StylesItemModel> itemList;
    private OnItemClickListener listener;
    private int selectedPosition = -1;

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(StylesItemModel stylesItemModel);
    }

    public StylesAdpaterBottom(List<StylesItemModel> list, OnItemClickListener onItemClickListener) {
        this.itemList = list;
        this.listener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bottom_sheet_styles, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final StylesItemModel stylesItemModel = this.itemList.get(i);
        try {
            InputStream open = viewHolder.itemView.getContext().getAssets().open(stylesItemModel.getImageFileName());
            viewHolder.imageView.setImageDrawable(Drawable.createFromStream(open, null));
            open.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        viewHolder.textView.setText(stylesItemModel.getText());
        if (i == this.selectedPosition) {
            viewHolder.selectionView.setBackgroundResource(R.drawable.selected_star);
        } else {
            viewHolder.selectionView.setBackgroundResource(R.drawable.not_selected_star);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.adapter.StylesAdpaterBottom.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i2 = StylesAdpaterBottom.this.selectedPosition;
                if (i2 >= 0) {
                    ((StylesItemModel) StylesAdpaterBottom.this.itemList.get(i2)).setSelected(false);
                    StylesAdpaterBottom stylesAdpaterBottom = StylesAdpaterBottom.this;
                    stylesAdpaterBottom.notifyItemChanged(stylesAdpaterBottom.selectedPosition);
                }
                stylesItemModel.setSelected(true);
                StylesAdpaterBottom.this.selectedPosition = i;
                StylesAdpaterBottom.this.notifyItemChanged(i);
                OnItemClickListener onItemClickListener = StylesAdpaterBottom.this.listener;
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(stylesItemModel);
                }
            }
        });
        viewHolder.appCompatButton2.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.adapter.StylesAdpaterBottom.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i2 = StylesAdpaterBottom.this.selectedPosition;
                if (i2 >= 0) {
                    ((StylesItemModel) StylesAdpaterBottom.this.itemList.get(i2)).setSelected(false);
                    StylesAdpaterBottom stylesAdpaterBottom = StylesAdpaterBottom.this;
                    stylesAdpaterBottom.notifyItemChanged(stylesAdpaterBottom.selectedPosition);
                }
                stylesItemModel.setSelected(true);
                StylesAdpaterBottom.this.selectedPosition = i;
                StylesAdpaterBottom.this.notifyItemChanged(i);
                OnItemClickListener onItemClickListener = StylesAdpaterBottom.this.listener;
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(stylesItemModel);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemList.size();
    }

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatButton appCompatButton2;
        ImageView imageView;
        View selectionView;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.itemImageView);
            this.textView = (TextView) view.findViewById(R.id.itemTextView);
            this.selectionView = view.findViewById(R.id.selectionView);
            this.appCompatButton2 = (AppCompatButton) view.findViewById(R.id.appCompatButton2);
        }
    }
}
