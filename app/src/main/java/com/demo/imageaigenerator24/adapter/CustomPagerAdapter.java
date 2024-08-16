package com.demo.imageaigenerator24.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.demo.imageaigenerator24.R;

/* loaded from: classes.dex */
public class CustomPagerAdapter extends PagerAdapter {
    private View.OnClickListener clickListener;
    private Context mContext;
    private int[] mLayouts;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public CustomPagerAdapter(Context context, int[] iArr, View.OnClickListener onClickListener) {
        this.mContext = context;
        this.mLayouts = iArr;
        this.clickListener = onClickListener;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ConstraintLayout constraintLayout;
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(this.mLayouts[i], viewGroup, false);
        if (i == 0) {
            constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.cons_slider_fashion);
        } else if (i == 1) {
            constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.cons_slider_character);
        } else if (i == 2) {
            constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.cons_slider_edit_input);
        } else if (i == 3) {
            constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.cons_slider_hd_button);
        } else {
            constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.cons_slider_fashion);
        }
        constraintLayout.setOnClickListener(this.clickListener);
        viewGroup.addView(inflate);
        return inflate;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mLayouts.length;
    }
}
