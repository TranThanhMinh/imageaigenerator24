package com.demo.imageaigenerator24.practicetwo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/* loaded from: classes.dex */
public class AdvancedSettingsBottomSheetFragment extends BottomSheetDialogFragment {
    private static SharedPrefsHelper prefs;
    public CollectedData collectedData;
    private EditText etNegativePrompt;
    private EditText etSeed;
    private SeekBar sbCfgScale;
    private SeekBar sbSteps;

    public void showBillingFragment() {
    }

    public AdvancedSettingsBottomSheetFragment(CollectedData collectedData) {
        this.collectedData = collectedData;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_advanced_settings_bottom_sheet, viewGroup, false);
        prefs = SharedPrefsHelper.getInstance(getActivity());
        inflate.findViewById(R.id.overlayEtNegativePrompt);
        this.etNegativePrompt = (EditText) inflate.findViewById(R.id.etNegativePrompt);
        this.etSeed = (EditText) inflate.findViewById(R.id.etSeed);
        this.sbCfgScale = (SeekBar) inflate.findViewById(R.id.sbCfgScale);
        this.sbSteps = (SeekBar) inflate.findViewById(R.id.sbSteps);
        inflate.findViewById(R.id.back_btn_advance_settings).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.AdvancedSettingsBottomSheetFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdvancedSettingsBottomSheetFragment.this.dismiss();
            }
        });
        setupListeners();
        return inflate;
    }

    private void setOverlayClickListener(View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.AdvancedSettingsBottomSheetFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdvancedSettingsBottomSheetFragment.this.showBillingFragment();
            }
        });
    }

    private void disableAndSetBillingListener(View view) {
        view.setEnabled(false);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.AdvancedSettingsBottomSheetFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdvancedSettingsBottomSheetFragment.this.showBillingFragment();
            }
        });
    }

    private void setupListeners() {
        this.etNegativePrompt.addTextChangedListener(new TextWatcher() { // from class: com.demo.imageaigenerator24.practicetwo.AdvancedSettingsBottomSheetFragment.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                AdvancedSettingsBottomSheetFragment.this.collectedData.setNegativePrompt(editable.toString());
            }
        });
        this.etSeed.addTextChangedListener(new TextWatcher() { // from class: com.demo.imageaigenerator24.practicetwo.AdvancedSettingsBottomSheetFragment.5
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                try {
                    int parseInt = Integer.parseInt(editable.toString());
                    if (parseInt < 1 || parseInt > Integer.MAX_VALUE) {
                        return;
                    }
                    AdvancedSettingsBottomSheetFragment.this.collectedData.setSeed(parseInt);
                } catch (NumberFormatException unused) {
                }
            }
        });
        this.sbCfgScale.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.demo.imageaigenerator24.practicetwo.AdvancedSettingsBottomSheetFragment.6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                AdvancedSettingsBottomSheetFragment.this.collectedData.setGuidance(i / 2.0f);
            }
        });
        this.sbSteps.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.demo.imageaigenerator24.practicetwo.AdvancedSettingsBottomSheetFragment.7
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                AdvancedSettingsBottomSheetFragment.this.collectedData.setSteps(i);
            }
        });
    }

    private void setGlobalBillingListener(View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.AdvancedSettingsBottomSheetFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdvancedSettingsBottomSheetFragment.this.showBillingFragment();
            }
        });
    }
}
