package com.demo.imageaigenerator24.practicetwo;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import java.io.IOException;
import java.util.List;

/* loaded from: classes.dex */
public class PhotorealismAdapter extends RecyclerView.Adapter<PhotorealismAdapter.ViewHolder> {
    private int itemHeight;
    private final OnItemClickListener listener;
    private List<PhotorealismModel> models;
    private int numRows;

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(PhotorealismModel photorealismModel);

        void onLockedItemClick(PhotorealismModel photorealismModel);
    }

    public PhotorealismAdapter(List<PhotorealismModel> list, OnItemClickListener onItemClickListener) {
        this.models = list;
        this.listener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photorealism, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(this.models.get(i), this.listener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.models.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.item_image);
            this.textView = (TextView) view.findViewById(R.id.item_text);
        }

        public void bind(final PhotorealismModel photorealismModel, final OnItemClickListener onItemClickListener) {
            try {
                this.imageView.setImageDrawable(Drawable.createFromStream(this.itemView.getContext().getAssets().open(photorealismModel.getImageName() + ".png"), null));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.textView.setText(photorealismModel.getText());
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.PhotorealismAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onItemClickListener.onItemClick(photorealismModel);
                }
            });
        }
    }
}
