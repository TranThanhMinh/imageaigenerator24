package com.demo.imageaigenerator24.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
//import com.facebook.ads.NativeAdLayout;

/* loaded from: classes.dex */
public class ExitFragment extends Fragment {
    SharedPrefsHelper prefs;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_exit, viewGroup, false);
        AdsCommon.SmallNative(getActivity(), (FrameLayout) inflate.findViewById(R.id.Admob_Small_Native));
        this.prefs = SharedPrefsHelper.getInstance(getContext());
        ((AppCompatButton) inflate.findViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.ExitFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ExitFragment.this.requireActivity().finishAffinity();
            }
        });
        ((AppCompatButton) inflate.findViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.ExitFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ExitFragment.this.getFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeNewFragment()).commit();
            }
        });
        return inflate;
    }
}
