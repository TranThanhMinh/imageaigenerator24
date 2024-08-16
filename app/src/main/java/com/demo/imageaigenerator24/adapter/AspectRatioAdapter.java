package com.demo.imageaigenerator24.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.model.AspectRatioModel;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class AspectRatioAdapter extends RecyclerView.Adapter<AspectRatioAdapter.ViewHolder> {
    private List<AspectRatioModel> aspectRatios;
    private AspectRatioClickListener clickListener;
    private Context context;
    SharedPrefsHelper prefs;

    /* loaded from: classes.dex */
    public interface AspectRatioClickListener {
        void onRatioSelected(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void AspectRatioAdapterImageViewCall1(View view) {
    }

    public AspectRatioAdapter(List<AspectRatioModel> list, Context context, AspectRatioClickListener aspectRatioClickListener) {
        this.aspectRatios = list;
        this.context = context;
        this.clickListener = aspectRatioClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.aspect_item_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        AspectRatioClickListener aspectRatioClickListener;
        final AspectRatioModel aspectRatioModel = this.aspectRatios.get(i);
        viewHolder.textView.setText(aspectRatioModel.getRatio());
        viewHolder.itemView.setSelected(aspectRatioModel.isSelected());
        char c = 65535;
        if (aspectRatioModel.isSelected()) {
            viewHolder.itemView.setBackground(ContextCompat.getDrawable(this.context, R.drawable.selected_aspect_ratio_background));
            viewHolder.imageView.setColorFilter(-1);
            viewHolder.textView.setTextColor(-1);
        } else {
            viewHolder.itemView.setBackground(ContextCompat.getDrawable(this.context, R.drawable.aspect_ratio_background));
            viewHolder.imageView.setColorFilter(Color.parseColor("#5F5F5F"));
            viewHolder.textView.setTextColor(Color.parseColor("#5F5F5F"));
        }
        if (i == 0 && !aspectRatioModel.isSelected() && noItemSelected() && (aspectRatioClickListener = this.clickListener) != null) {
            aspectRatioClickListener.onRatioSelected(aspectRatioModel.getRatio());
        }
        this.prefs = SharedPrefsHelper.getInstance(this.context);
        String ratio = aspectRatioModel.getRatio();
        ratio.hashCode();
        ratio.hashCode();
        switch (ratio.hashCode()) {
            case 48936:
                if (ratio.equals("1:1")) {
                    c = 0;
                    break;
                }
                break;
            case 49899:
                if (ratio.equals("2:3")) {
                    c = 1;
                    break;
                }
                break;
            case 50859:
                if (ratio.equals("3:2")) {
                    c = 2;
                    break;
                }
                break;
            case 51821:
                if (ratio.equals("4:3")) {
                    c = 3;
                    break;
                }
                break;
            case 51823:
                if (ratio.equals("4:5")) {
                    c = 4;
                    break;
                }
                break;
            case 52783:
                if (ratio.equals("5:4")) {
                    c = 5;
                    break;
                }
                break;
            case 54706:
                if (ratio.equals("7:5")) {
                    c = 6;
                    break;
                }
                break;
            case 1513508:
                if (ratio.equals("16:9")) {
                    c = 7;
                    break;
                }
                break;
            case 1538494:
                if (ratio.equals("21:9")) {
                    c = '\b';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                viewHolder.imageView.setImageResource(R.drawable.square_icon);
                viewHolder.imageView.setOnClickListener(new AspectRatioAdapterImageViewCall());
                break;
            case 1:
                viewHolder.imageView.setImageResource(R.drawable.two_three_icon);
                break;
            case 2:
                viewHolder.imageView.setImageResource(R.drawable.three_two_icon);
                break;
            case 3:
                viewHolder.imageView.setImageResource(R.drawable.four_three_icon);
                break;
            case 4:
                viewHolder.imageView.setImageResource(R.drawable.four_five_icon);
                break;
            case 5:
                viewHolder.imageView.setImageResource(R.drawable.five_four_icon);
                break;
            case 6:
                viewHolder.imageView.setImageResource(R.drawable.seven_five_icon);
                break;
            case 7:
                viewHolder.imageView.setImageResource(R.drawable.sixteen_nine_icon);
                break;
            case '\b':
                viewHolder.imageView.setImageResource(R.drawable.twentyone_nine_icon);
                break;
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.adapter.AspectRatioAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AspectRatioClickListener aspectRatioClickListener2 = AspectRatioAdapter.this.clickListener;
                if (aspectRatioClickListener2 != null) {
                    aspectRatioClickListener2.onRatioSelected(aspectRatioModel.getRatio());
                }
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.adapter.AspectRatioAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly((Activity) AspectRatioAdapter.this.context);
                AspectRatioAdapter.this.itemViewCall(i, aspectRatioModel, view);
            }
        });
    }

    public void itemViewCall(int i, AspectRatioModel aspectRatioModel, View view) {
        Iterator<AspectRatioModel> it = this.aspectRatios.iterator();
        while (it.hasNext()) {
            it.next().setSelected(false);
        }
        aspectRatioModel.setSelected(true);
        notifyDataSetChanged();
        AspectRatioClickListener aspectRatioClickListener = this.clickListener;
        if (aspectRatioClickListener != null) {
            aspectRatioClickListener.onRatioSelected(aspectRatioModel.getRatio());
        }
    }

    private boolean noItemSelected() {
        Iterator<AspectRatioModel> it = this.aspectRatios.iterator();
        while (it.hasNext()) {
            if (it.next().isSelected()) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.aspectRatios.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.ratioImageView);
            this.textView = (TextView) view.findViewById(R.id.ratioTextView);
        }
    }
}
