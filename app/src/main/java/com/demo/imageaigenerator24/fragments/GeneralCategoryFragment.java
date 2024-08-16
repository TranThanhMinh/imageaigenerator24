package com.demo.imageaigenerator24.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.adapter.AspectRatioAdapter;
import com.demo.imageaigenerator24.adapter.ItemModelAdapter;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.history.History;
import com.demo.imageaigenerator24.history.HistoryBottomSheetFragment;
import com.demo.imageaigenerator24.history.HistoryManager;
import com.demo.imageaigenerator24.model.AspectRatioModel;
import com.demo.imageaigenerator24.model.ItemModel;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.demo.imageaigenerator24.utils.PromptGenerator;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class GeneralCategoryFragment extends Fragment {
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int REQUEST_STORAGE_PERMISSION = 101;
    private static final int SPEECH_REQUEST_CODE = 0;
    ConstraintLayout btnGeneratenew;
    public EditText editTextPrompt;
    private ImageView imageView;
    private ImageView imageViewButton;
    boolean isRewardedAdEnabled;
    SharedPrefsHelper prefs;
    private PromptGenerator promptGenerator;
    RecyclerView recyclerView_model;
    private TextView responseTextView;
    private ImageView speechtotext_btn;
    public String model = "stable-diffusion-xl-v1-0";
    public String outputFormat = "png";
    public String prompt = "Superman standing with super girl in galaxy and with cyborg girl";
    public int selectedHeight = 512;
    String selectedModel = "stable-diffusion-xl-v1-0";
    public int selectedWidth = 512;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_general_category, viewGroup, false);
        this.editTextPrompt = (EditText) inflate.findViewById(R.id.editTextPrompt);
        this.btnGeneratenew = (ConstraintLayout) inflate.findViewById(R.id.btnGeneratenew);
        this.imageViewButton = (ImageView) inflate.findViewById(R.id.imageView2);
        this.speechtotext_btn = (ImageView) inflate.findViewById(R.id.speechtotext_btn);
        this.promptGenerator = new PromptGenerator();
        this.recyclerView_model = (RecyclerView) inflate.findViewById(R.id.recyclerView_model);
        if (getArguments() != null && getArguments().containsKey("textKey")) {
            this.editTextPrompt.setText(getArguments().getString("textKey"));
        }
        setupRecyclerViewModel();
        setupAspectRatioRV(inflate);
        inflate.findViewById(R.id.constraintLayout5).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.d("MainActivityyyy", "History button clicked");
                GeneralCategoryFragment.this.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ModelFragment()).addToBackStack(null).commit();
            }
        });
        this.imageViewButton.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GeneralCategoryFragment.this.editTextPrompt.setText(GeneralCategoryFragment.this.promptGenerator.generateRandomPrompt());
            }
        });
        this.speechtotext_btn.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(GeneralCategoryFragment.this.getContext(), "android.permission.RECORD_AUDIO") != 0) {
                    GeneralCategoryFragment.this.requestPermission();
                } else {
                    GeneralCategoryFragment.this.displaySpeechRecognizer();
                }
            }
        });
        inflate.findViewById(R.id.advance_settings_btn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly(GeneralCategoryFragment.this.getActivity());
                AdvanceSettingsFragment.newInstance().show(GeneralCategoryFragment.this.getActivity().getSupportFragmentManager(), "advanceSettingsFragment");
            }
        });
        this.btnGeneratenew.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly(GeneralCategoryFragment.this.getActivity());
                Log.d("taggy", "Button clicked!");
                GeneralCategoryFragment generalCategoryFragment = GeneralCategoryFragment.this;
                generalCategoryFragment.prompt = generalCategoryFragment.editTextPrompt.getText().toString();
                Log.d("taggy", "Prompt: " + GeneralCategoryFragment.this.prompt);
                if (GeneralCategoryFragment.this.selectedHeight <= 0 || GeneralCategoryFragment.this.selectedWidth <= 0) {
                    Toast.makeText(GeneralCategoryFragment.this.getActivity().getApplicationContext(), "Invalid dimensions!", 0).show();
                    return;
                }
                GeneralCategoryFragment generalCategoryFragment2 = GeneralCategoryFragment.this;
                generalCategoryFragment2.model = generalCategoryFragment2.selectedModel;
                new HistoryManager(GeneralCategoryFragment.this.getActivity()).addHistoryItem(new History(GeneralCategoryFragment.this.prompt, new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date())));
                DownloadFragment downloadFragment = new DownloadFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("model", GeneralCategoryFragment.this.model);
                bundle2.putString("prompt", GeneralCategoryFragment.this.prompt);
                bundle2.putInt("height", GeneralCategoryFragment.this.selectedHeight);
                bundle2.putInt("width", GeneralCategoryFragment.this.selectedWidth);
                bundle2.putString("outputFormat", GeneralCategoryFragment.this.outputFormat);
                downloadFragment.setArguments(bundle2);
                GeneralCategoryFragment.this.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, downloadFragment).addToBackStack(null).commit();
                Log.d("taggy", "Output Format: " + GeneralCategoryFragment.this.outputFormat + ", Model: " + GeneralCategoryFragment.this.model);
            }
        });
        inflate.findViewById(R.id.history_btn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly(GeneralCategoryFragment.this.getActivity());
                Log.d("MainActivityyyy", "History button clicked");
                HistoryBottomSheetFragment.newInstance().show(GeneralCategoryFragment.this.getActivity().getSupportFragmentManager(), "historyFragment");
            }
        });
        return inflate;
    }

    private void proceedWithDownload() {
        this.prompt = this.editTextPrompt.getText().toString();
        Log.d("taggy", "Prompt: " + this.prompt);
        if (this.selectedHeight <= 0 || this.selectedWidth <= 0) {
            Toast.makeText(getActivity().getApplicationContext(), "Invalid dimensions!", 0).show();
            return;
        }
        this.model = this.selectedModel;
        new HistoryManager(getActivity()).addHistoryItem(new History(this.prompt, new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date())));
        DownloadFragment downloadFragment = new DownloadFragment();
        Bundle bundle = new Bundle();
        bundle.putString("model", this.model);
        bundle.putString("prompt", this.prompt);
        bundle.putInt("height", this.selectedHeight);
        bundle.putInt("width", this.selectedWidth);
        bundle.putString("outputFormat", this.outputFormat);
        downloadFragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, downloadFragment).addToBackStack(null).commit();
        Log.d("taggy", "Output Format: " + this.outputFormat + ", Model: " + this.model);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.RECORD_AUDIO"}, 101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displaySpeechRecognizer() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        startActivityForResult(intent, 0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 101 && iArr.length > 0 && iArr[0] == 0) {
            return;
        }
        Toast.makeText(getActivity(), "Storage permission is required to save the file", 0).show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == -1) {
            this.editTextPrompt.setText(intent.getStringArrayListExtra("android.speech.extra.RESULTS").get(0));
        }
    }

    private void setupRecyclerViewModel() {
        ItemModelAdapter itemModelAdapter = new ItemModelAdapter(Arrays.asList(new ItemModel("model_1.png", getString(R.string.absolute_reality), "absolute-reality-v1-8-1"), new ItemModel("model_2.png", getString(R.string.dream_shaper), "dream-shaper-v8"), new ItemModel("model_3.png", getString(R.string.realistic_vision), "realistic-vision-v5-1"), new ItemModel("model_4.png", getString(R.string.stable_diffusion), "stable-diffusion-xl-v1-0"), new ItemModel("model_5.png", getString(R.string.absolute_reality), "absolute-reality-v1-6"), new ItemModel("model_6.png", getString(R.string.dark_sushi), "dark-sushi-mix-v2-25")));
        this.recyclerView_model.setAdapter(itemModelAdapter);
        this.recyclerView_model.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        itemModelAdapter.setOnItemClickListener(new ItemModelAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryFragment.7
            @Override // com.demo.imageaigenerator24.adapter.ItemModelAdapter.OnItemClickListener
            public void onItemClick(ItemModel itemModel) {
                GeneralCategoryFragment.this.selectedModel = itemModel.getModel();
                Toast.makeText(GeneralCategoryFragment.this.getActivity(), "Selected Model: " + itemModel.getText(), 0).show();
            }
        });
    }

    private void setupAspectRatioRV(View view) {
        List asList = Arrays.asList(new AspectRatioModel("1:1"), new AspectRatioModel("4:3"), new AspectRatioModel("3:2"), new AspectRatioModel("16:9"));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_aspcetratio);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        recyclerView.setAdapter(new AspectRatioAdapter(asList, getActivity(), new AspectRatioAdapter.AspectRatioClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryFragment.8
            @Override // com.demo.imageaigenerator24.adapter.AspectRatioAdapter.AspectRatioClickListener
            public void onRatioSelected(String str) {
                GeneralCategoryFragment.this.onRatioSelectedCall(str);
            }
        }));
    }

    public void onRatioSelectedCall(String str) {
        str.hashCode();
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 48936:
                if (str.equals("1:1")) {
                    c = 0;
                    break;
                }
                break;
            case 50859:
                if (str.equals("3:2")) {
                    c = 1;
                    break;
                }
                break;
            case 51821:
                if (str.equals("4:3")) {
                    c = 2;
                    break;
                }
                break;
            case 1513508:
                if (str.equals("16:9")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.selectedHeight = 512;
                this.selectedWidth = 512;
                return;
            case 1:
                this.selectedHeight = 384;
                this.selectedWidth = 512;
                return;
            case 2:
                this.selectedHeight = 512;
                this.selectedWidth = 768;
                return;
            case 3:
                this.selectedHeight = 576;
                this.selectedWidth = 1024;
                return;
            default:
                return;
        }
    }
}
