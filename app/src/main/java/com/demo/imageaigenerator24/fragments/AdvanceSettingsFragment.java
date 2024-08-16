package com.demo.imageaigenerator24.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.demo.imageaigenerator24.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/* loaded from: classes.dex */
public class AdvanceSettingsFragment extends BottomSheetDialogFragment {
    public static AdvanceSettingsFragment newInstance() {
        return new AdvanceSettingsFragment(R.layout.fragment_advance_settings);
    }

    public AdvanceSettingsFragment(int i) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_advance_settings, viewGroup, false);
    }
}
