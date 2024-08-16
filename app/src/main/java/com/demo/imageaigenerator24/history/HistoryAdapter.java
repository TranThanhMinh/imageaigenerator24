package com.demo.imageaigenerator24.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.model.HistoryItem;

/* loaded from: classes.dex */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private HistoryItem historyItem;

    public HistoryAdapter(HistoryItem historyItem) {
        this.historyItem = historyItem;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.text.setText(this.historyItem.getText());
        viewHolder.dateTime.setText(this.historyItem.getDateTime());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.historyItem != null ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTime;
        TextView text;

        ViewHolder(View view) {
            super(view);
            this.text = (TextView) view.findViewById(R.id.textHistory);
            this.dateTime = (TextView) view.findViewById(R.id.textDate);
        }
    }
}
