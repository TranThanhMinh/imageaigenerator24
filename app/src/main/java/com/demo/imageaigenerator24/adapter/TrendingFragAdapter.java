package com.demo.imageaigenerator24.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.model.InspirationFragItemModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* loaded from: classes.dex */
public class TrendingFragAdapter extends RecyclerView.Adapter<TrendingFragAdapter.ViewHolder> {
    private OnItemClickListener clickListener;
    Context context;
    private List<InspirationFragItemModel> itemList;

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(InspirationFragItemModel inspirationFragItemModel);
    }

    public TrendingFragAdapter(List<InspirationFragItemModel> list, Context context, OnItemClickListener onItemClickListener) {
        this.itemList = list;
        this.context = context;
        this.clickListener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_frag_layout_inspiration, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final InspirationFragItemModel inspirationFragItemModel = this.itemList.get(i);
        viewHolder.textView.setText(inspirationFragItemModel.getText());
        try {
            InputStream open = this.context.getAssets().open(inspirationFragItemModel.getAssetFileName());
            viewHolder.imageView.setImageDrawable(Drawable.createFromStream(open, null));
            open.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.adapter.TrendingFragAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OnItemClickListener onItemClickListener = TrendingFragAdapter.this.clickListener;
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(inspirationFragItemModel);
                }
            }
        });
        viewHolder.appCompatButton2.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.adapter.TrendingFragAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly((Activity) TrendingFragAdapter.this.context);
                OnItemClickListener onItemClickListener = TrendingFragAdapter.this.clickListener;
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(inspirationFragItemModel);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemList.size();
    }

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatButton appCompatButton2;
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.view_inspire_frag_item);
            this.textView = (TextView) view.findViewById(R.id.textView_inspire_frag_item);
            this.appCompatButton2 = (AppCompatButton) view.findViewById(R.id.appCompatButton2);
        }
    }
}
