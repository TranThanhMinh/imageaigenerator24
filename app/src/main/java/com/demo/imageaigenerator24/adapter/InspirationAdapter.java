package com.demo.imageaigenerator24.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.interafaces.OnItemClickListenerinspiration;
import com.demo.imageaigenerator24.model.InspirationItem;
import java.util.List;

/* loaded from: classes.dex */
public class InspirationAdapter extends RecyclerView.Adapter<InspirationAdapter.ViewHolder> {
    private Context context;
    public OnItemClickListenerinspiration itemClickListener;
    public List<InspirationItem> itemList;

    public InspirationAdapter(Context context, List<InspirationItem> list, OnItemClickListenerinspiration onItemClickListenerinspiration) {
        this.context = context;
        this.itemList = list;
        this.itemClickListener = onItemClickListenerinspiration;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inspiration_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        InspirationItem inspirationItem = this.itemList.get(i);
        viewHolder.textViewInspiration.setText(inspirationItem.getText());
        viewHolder.view_inspire.setBackground(inspirationItem.getImageResourceId());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemList.size();
    }

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewInspiration;
        AppCompatButton tryButton;
        ConstraintLayout view_inspire;

        public ViewHolder(View view) {
            super(view);
            this.textViewInspiration = (TextView) view.findViewById(R.id.textViewInspiration);
            this.view_inspire = (ConstraintLayout) view.findViewById(R.id.view_inspire);
            this.tryButton = (AppCompatButton) view.findViewById(R.id.appCompatButton2);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.adapter.InspirationAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ViewHolder.this.handleItemClick();
                }
            });
            this.tryButton.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.adapter.InspirationAdapter.ViewHolder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ViewHolder.this.handleItemClick();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleItemClick() {
            AdsCommon.InterstitialAdsOnly((Activity) InspirationAdapter.this.context);
            int adapterPosition = getAdapterPosition();
            if (adapterPosition != -1) {
                InspirationItem inspirationItem = InspirationAdapter.this.itemList.get(adapterPosition);
                if (InspirationAdapter.this.itemClickListener != null) {
                    InspirationAdapter.this.itemClickListener.onItemClick(inspirationItem);
                }
            }
        }
    }
}
