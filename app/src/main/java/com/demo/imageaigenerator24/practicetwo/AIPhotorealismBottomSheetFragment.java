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
public class AIPhotorealismBottomSheetFragment extends BottomSheetDialogFragment {
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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_a_i_photorealism_bottom_sheet, viewGroup, false);
        this.prefs = SharedPrefsHelper.getInstance(getActivity());
        String[] strArr = {"ICB V7", "ICB V8", "(recommended) ICBINP V7", "INP V8", "AbsoluteReality V1.8", "AbsoluteReality V8.1", "AbsoluteReality V8.2", "AbsoluteReality V8.9", "AbsoluteReality V2", "AbsoluteReality v1.6", "AbsoluteReality v1.7", "AbsoluteReality v1.1", "MoonFilm", "MoonFilm V1", "MoonFilm FilmGrain V1", "MoonFilm FilmGrain", "ICBINP", "I Can't believe V1", "I Can't believe V2", "I Can't believe Final", "ICBINP Afterburn V1", "ICBINP Afterburn V2", "ICBINP Afterburn V3", "ICBINP Afterburn V4", "Relapse V1", "Relapse V2", "Relapse V3", "Relapse V4", "ICBINP SECO", "ICBINP SECO", "ICBINP SECO", "ICBINP SECO"};
        String[] strArr2 = {"icbinp", "icbinp", "icbinp", "icbinp", "absolute-reality-v1-8-1", "absolute-reality-v1-8-1", "absolute-reality-v1-8-1", "absolute-reality-v1-8-1", "absolute-reality-v1-6", "absolute-reality-v1-6", "absolute-reality-v1-6", "absolute-reality-v1-6", "moonfilm-film-grain-v1", "moonfilm-film-grain-v1", "moonfilm-film-grain-v1", "moonfilm-film-grain-v1", "icbinp-final", "icbinp-final", "icbinp-final", "icbinp-final", "icbinp-afterburn", "icbinp-afterburn", "icbinp-afterburn", "icbinp-afterburn", "icbinp-relapse", "icbinp-relapse", "icbinp-relapse", "icbinp-relapse", "icbinp-seco", "icbinp-seco", "icbinp-seco", "icbinp-seco"};
        ArrayList arrayList = new ArrayList();
        int i = 1;
        while (i <= 32) {
            int i2 = i - 1;
            arrayList.add(new PhotorealismModel("ai_photorealism_" + i, strArr[i2], strArr2[i2], i == 3 || i == 8 || i == 9 || i == 14 || i == 15 || i == 20 || i == 21 || i == 27));
            i++;
        }
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.photorealism_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.item_spacing), true));
        recyclerView.setAdapter(new PhotorealismAdapter(arrayList, new PhotorealismAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.AIPhotorealismBottomSheetFragment.1
            @Override // com.demo.imageaigenerator24.practicetwo.PhotorealismAdapter.OnItemClickListener
            public void onItemClick(PhotorealismModel photorealismModel) {
                AdsCommon.InterstitialAdsOnly(AIPhotorealismBottomSheetFragment.this.getActivity());
                Image2ImageFragment.collectedData.appendToPrompt(" " + photorealismModel.getModelName());
                AIPhotorealismBottomSheetFragment.this.mListener.onModelSelected(photorealismModel.getModelName());
                AIPhotorealismBottomSheetFragment.this.dismiss();
            }

            @Override // com.demo.imageaigenerator24.practicetwo.PhotorealismAdapter.OnItemClickListener
            public void onLockedItemClick(PhotorealismModel photorealismModel) {
                AIPhotorealismBottomSheetFragment.this.mListener.onLockedModelSelected(photorealismModel.getModelName());
            }
        }));
        ((ConstraintLayout) inflate.findViewById(R.id.back_btn_photorealism)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.AIPhotorealismBottomSheetFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AIPhotorealismBottomSheetFragment.this.dismiss();
            }
        });
        return inflate;
    }
}
