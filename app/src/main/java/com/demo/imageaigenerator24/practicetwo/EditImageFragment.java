package com.demo.imageaigenerator24.practicetwo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.fragments.ExitFragment;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;

/* loaded from: classes.dex */
public class EditImageFragment extends Fragment {
    SharedPrefsHelper prefs;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_edit_image, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.prefs = SharedPrefsHelper.getInstance(getContext());
        final Bundle bundle2 = new Bundle();
        view.findViewById(R.id.btneditimage).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.EditImageFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdsCommon.InterstitialAdsOnly(EditImageFragment.this.getActivity());
                bundle2.putString("fragment", "Image2ImageFragment");
                Image2ImageFragment image2ImageFragment = new Image2ImageFragment();
                image2ImageFragment.setArguments(bundle2);
                EditImageFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, image2ImageFragment).commit();
            }
        });
        view.findViewById(R.id.appCompatButtonPro).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.EditImageFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
            }
        });
        view.findViewById(R.id.btntrendingimage).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.EditImageFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdsCommon.InterstitialAdsOnly(EditImageFragment.this.getActivity());
                bundle2.putString("fragment", "TrendingFragment");
                Image2ImageFragment image2ImageFragment = new Image2ImageFragment();
                image2ImageFragment.setArguments(bundle2);
                EditImageFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, image2ImageFragment).commit();
            }
        });
        view.findViewById(R.id.btnaicharacterimage).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.EditImageFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdsCommon.InterstitialAdsOnly(EditImageFragment.this.getActivity());
                bundle2.putString("fragment", "AICharacterFragment");
                Image2ImageFragment image2ImageFragment = new Image2ImageFragment();
                image2ImageFragment.setArguments(bundle2);
                EditImageFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, image2ImageFragment).commit();
            }
        });
    }

    public void handleBackPress() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new ExitFragment());
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}
