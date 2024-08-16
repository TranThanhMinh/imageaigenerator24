package com.demo.imageaigenerator24.practicetwo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.practicetwo.PhotorealismAdapter;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AIArtBottomSheetFragment extends BottomSheetDialogFragment {
    public BottomSheetListener mListener;
    SharedPrefsHelper prefs;

    /* loaded from: classes.dex */
    public interface BottomSheetListener {
        void onLockedModelSelected(String str);

        void onModelSelected(String str);
    }

    public void setBottomSheetListener(BottomSheetListener bottomSheetListener) {
        this.mListener = bottomSheetListener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_a_i_art_bottom_sheet, viewGroup, false);
        this.prefs = SharedPrefsHelper.getInstance(getActivity());
        String[] strArr = {"ojv4", "ojv4", "dsv8", "dsv8", "dsv8", "dsv8", "moonfilmv3", "moonfilmv3", "moonfilmv3", "moonfilmv3", "rvv5.1", "rvv5.1", "rvv5.1", "rvv5.1", "RPG", "RPG", "RPG", "RPG", "rlv3", "rlv3", "rlv3", "rlv3"};
        String[] strArr2 = {"openjourney-v4", "openjourney-v4", "dream-shaper-v8", "dream-shaper-v8", "dream-shaper-v8", "dream-shaper-v8", "moonfilm-reality-v3", "moonfilm-reality-v3", "moonfilm-reality-v3", "moonfilm-reality-v3", "realistic-vision-v5-1", "realistic-vision-v5-1", "realistic-vision-v5-1", "realistic-vision-v5-1", "anashel-rpg", "anashel-rpg", "anashel-rpg", "anashel-rpg", "realistic-vision-v1-3", "realistic-vision-v1-3", "realistic-vision-v1-3", "realistic-vision-v1-3"};
        ArrayList arrayList = new ArrayList();
        int i = 1;
        while (i <= 22) {
            int i2 = i - 1;
            arrayList.add(new PhotorealismModel("ai_art_" + i, strArr[i2], strArr2[i2], i == 3 || i == 5 || i == 7 || i == 10 || i == 12 || i == 16));
            i++;
        }
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.photorealism_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.item_spacing), true));
        recyclerView.setAdapter(new PhotorealismAdapter(arrayList, new PhotorealismAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.AIArtBottomSheetFragment.1
            @Override // com.demo.imageaigenerator24.practicetwo.PhotorealismAdapter.OnItemClickListener
            public void onItemClick(PhotorealismModel photorealismModel) {
                AdsCommon.InterstitialAdsOnly(AIArtBottomSheetFragment.this.getActivity());
                Image2ImageFragment.collectedData.appendToPrompt(" " + photorealismModel.getModelName());
                AIArtBottomSheetFragment.this.dismiss();
            }

            @Override // com.demo.imageaigenerator24.practicetwo.PhotorealismAdapter.OnItemClickListener
            public void onLockedItemClick(PhotorealismModel photorealismModel) {
                AIArtBottomSheetFragment.this.mListener.onLockedModelSelected(photorealismModel.getModelName());
            }
        }));
        ((ConstraintLayout) inflate.findViewById(R.id.back_btn_photorealism)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.AIArtBottomSheetFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AIArtBottomSheetFragment.this.dismiss();
            }
        });
        return inflate;
    }
}
