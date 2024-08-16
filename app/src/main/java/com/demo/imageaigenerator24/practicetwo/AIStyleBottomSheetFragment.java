package com.demo.imageaigenerator24.practicetwo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.practicetwo.PhotorealismAdapter;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AIStyleBottomSheetFragment extends BottomSheetDialogFragment {
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
        View inflate = layoutInflater.inflate(R.layout.fragment_a_i_style_bottom_sheet, viewGroup, false);
        this.prefs = SharedPrefsHelper.getInstance(getActivity());
        String[] strArr = {"synwpunkv2", "synwpunkv2", "synwpunkv2", "synwpunkv2", "arcn dfsion", "arcn dfsion", "arcn dfsion", "arcn dfsion", "opn jrny v4", "opn jrny v4", "opn jrny v4", "opn jrny v4", "anlog dfsn", "anlog dfsn", "anlog dfsn", "anlog dfsn", " vangogh dfsn", " vangogh dfsn", " vangogh dfsn", " vangogh dfsn", "mdrndsnydfsion", "mdrndsnydfsion", "mdrndsnydfsion", "mdrndsnydfsion", "midjrny pprcut", "midjrny pprcut", "midjrny pprcut", "midjrny pprcut"};
        String[] strArr2 = {"synthwave-punk-v2", "synthwave-punk-v2", "synthwave-punk-v2", "synthwave-punk-v2", "arcane-diffusion", "arcane-diffusion", "arcane-diffusion", "arcane-diffusion", "openjourney-v4", "openjourney-v4", "openjourney-v4", "openjourney-v4", "openjourney-v1-0", "openjourney-v1-0", "openjourney-v1-0", "openjourney-v1-0", "analog-diffusion", "analog-diffusion", "analog-diffusion", "analog-diffusion", "van-gogh-diffusion", "van-gogh-diffusion", "van-gogh-diffusion", "van-gogh-diffusion", "mo-di-diffusion", "mo-di-diffusion", "mo-di-diffusion", "mo-di-diffusion"};
        ArrayList arrayList = new ArrayList();
        int i = 1;
        while (i <= 28) {
            int i2 = i - 1;
            arrayList.add(new PhotorealismModel("ai_style_" + i, strArr[i2], strArr2[i2], i == 3 || i == 4 || i == 7 || i == 8 || i == 12 || i == 23 || i == 18 || i == 19));
            i++;
        }
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.photorealism_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.item_spacing), true));
        recyclerView.setAdapter(new PhotorealismAdapter(arrayList, new PhotorealismAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.AIStyleBottomSheetFragment.1
            @Override // com.demo.imageaigenerator24.practicetwo.PhotorealismAdapter.OnItemClickListener
            public void onItemClick(PhotorealismModel photorealismModel) {
                AdsCommon.InterstitialAdsOnly(AIStyleBottomSheetFragment.this.getActivity());
                Image2ImageFragment.collectedData.appendToPrompt(" " + photorealismModel.getModelName());
                AIStyleBottomSheetFragment.this.dismiss();
            }

            @Override // com.demo.imageaigenerator24.practicetwo.PhotorealismAdapter.OnItemClickListener
            public void onLockedItemClick(PhotorealismModel photorealismModel) {
                AIStyleBottomSheetFragment.this.mListener.onLockedModelSelected(photorealismModel.getModelName());
            }
        }));
        return inflate;
    }
}
