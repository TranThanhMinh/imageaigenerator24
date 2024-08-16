package com.demo.imageaigenerator24.fragments;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.adapter.ItemModelAdapter;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.history.HistoryBottomSheetFragment;
import com.demo.imageaigenerator24.model.ItemModel;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.demo.imageaigenerator24.utils.ConnectivityChecker;
import com.demo.imageaigenerator24.utils.PromptGenerator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;

/* loaded from: classes.dex */
public class GeneralCategoryImage2ImageFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int REQUEST_STORAGE_PERMISSION = 101;
    private static final int SPEECH_REQUEST_CODE = 0;
    ConstraintLayout btnGeneratenew;
    public EditText editTextPrompt;
    ImageView getBackImage;
    CardView getBackImageCard;
    private String globalBase64Image;
    private ImageView imageView;
    private ImageView imageViewButton;
    ImageView lock_layout;
    SharedPrefsHelper prefs;
    private PromptGenerator promptGenerator;
    RecyclerView recyclerView_model;
    private TextView responseTextView;
    String selectedModel;
    private ImageView speechtotext_btn;
    TextView textView10;
    TextView textView101;
    TextView textView4;
    CardView uploadImage_btn;
    public String model = "stable-diffusion-xl-v1-0";
    public String outputFormat = "png";
    public String prompt = "Superman standing with super girl in galaxy and with cyborg girl";
    private int selectedHeight = 512;
    private int selectedWidth = 512;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_general_category_image2_image, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.editTextPrompt = (EditText) view.findViewById(R.id.editTextPrompt);
        this.textView4 = (TextView) view.findViewById(R.id.textView4);
        this.prefs = SharedPrefsHelper.getInstance(getActivity());
        if (getArguments() != null && getArguments().containsKey("textKey")) {
            this.editTextPrompt.setText(getArguments().getString("textKey"));
        }
        this.imageView = (ImageView) view.findViewById(R.id.imageView);
        this.btnGeneratenew = (ConstraintLayout) view.findViewById(R.id.btnGeneratenew);
        this.responseTextView = (TextView) view.findViewById(R.id.responseTextView);
        this.imageViewButton = (ImageView) view.findViewById(R.id.imageView2);
        this.speechtotext_btn = (ImageView) view.findViewById(R.id.speechtotext_btn);
        this.promptGenerator = new PromptGenerator();
        this.recyclerView_model = (RecyclerView) view.findViewById(R.id.recyclerView_model);
        this.uploadImage_btn = (CardView) view.findViewById(R.id.uploadImage_btn);
        this.getBackImage = (ImageView) view.findViewById(R.id.getBackImage);
        this.getBackImageCard = (CardView) view.findViewById(R.id.getBackImageCard);
        this.textView10 = (TextView) view.findViewById(R.id.textView10);
        this.textView101 = (TextView) view.findViewById(R.id.textView101);
        this.lock_layout = (ImageView) view.findViewById(R.id.lock_layout);
        this.imageViewButton.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryImage2ImageFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                GeneralCategoryImage2ImageFragment.this.editTextPrompt.setText(GeneralCategoryImage2ImageFragment.this.promptGenerator.generateRandomPrompt());
            }
        });
        this.speechtotext_btn.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryImage2ImageFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ContextCompat.checkSelfPermission(GeneralCategoryImage2ImageFragment.this.getContext(), "android.permission.RECORD_AUDIO") != 0) {
                    GeneralCategoryImage2ImageFragment.this.requestPermission();
                } else {
                    GeneralCategoryImage2ImageFragment.this.displaySpeechRecognizer();
                }
            }
        });
        this.uploadImage_btn.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryImage2ImageFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                GeneralCategoryImage2ImageFragment.this.pickImage();
            }
        });
        view.findViewById(R.id.choose_Setting_btn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryImage2ImageFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdsCommon.InterstitialAdsOnly(GeneralCategoryImage2ImageFragment.this.getActivity());
                Log.d("MainActivityyyy", "History button clicked");
                AdvanceSettingsFragment.newInstance().show(GeneralCategoryImage2ImageFragment.this.getActivity().getSupportFragmentManager(), "advanceSettingsFragment");
            }
        });
        view.findViewById(R.id.constraintLayout5).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryImage2ImageFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdsCommon.InterstitialAdsOnly(GeneralCategoryImage2ImageFragment.this.getActivity());
                Log.d("MainActivityyyy", "History button clicked");
                GeneralCategoryImage2ImageFragment.this.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ModelFragment()).addToBackStack(null).commit();
            }
        });
        this.btnGeneratenew.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryImage2ImageFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdsCommon.InterstitialAdsOnly(GeneralCategoryImage2ImageFragment.this.getActivity());
                if (ConnectivityChecker.isNetworkAvailable(GeneralCategoryImage2ImageFragment.this.getActivity())) {
                    GeneralCategoryImage2ImageFragment generalCategoryImage2ImageFragment = GeneralCategoryImage2ImageFragment.this;
                    generalCategoryImage2ImageFragment.prompt = generalCategoryImage2ImageFragment.editTextPrompt.getText().toString();
                    if (GeneralCategoryImage2ImageFragment.this.prompt.trim().isEmpty()) {
                        Toast.makeText(GeneralCategoryImage2ImageFragment.this.getActivity(), "Please input Something!", 0).show();
                        return;
                    }
                    HomeFragment.increment++;
                    GeneralCategoryImage2ImageFragment.this.proceedWithDownload();
                    Log.d("taggy", "Output Format: " + GeneralCategoryImage2ImageFragment.this.outputFormat + ", Model: " + GeneralCategoryImage2ImageFragment.this.model);
                    return;
                }
                GeneralCategoryImage2ImageFragment.this.showNoInternetDialog();
            }
        });
        view.findViewById(R.id.history_btn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryImage2ImageFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdsCommon.InterstitialAdsOnly(GeneralCategoryImage2ImageFragment.this.getActivity());
                Log.d("MainActivityyyy", "History button clicked");
                HistoryBottomSheetFragment.newInstance().show(GeneralCategoryImage2ImageFragment.this.getActivity().getSupportFragmentManager(), "historyFragment");
            }
        });
        setupRecyclerViewModel();
    }

    public void proceedWithDownload() {
        Log.d("taggy", "Prompt: " + this.prompt);
        if (this.selectedHeight <= 0 || this.selectedWidth <= 0) {
            Toast.makeText(getActivity().getApplicationContext(), "Invalid dimensions!", 0).show();
            return;
        }
        this.model = this.selectedModel;
        DownloadFragment downloadFragment = new DownloadFragment();
        Bundle bundle = new Bundle();
        bundle.putString("model", this.model);
        bundle.putString("prompt", this.prompt);
        bundle.putInt("height", this.selectedHeight);
        bundle.putInt("width", this.selectedWidth);
        bundle.putString("outputFormat", this.outputFormat);
        downloadFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, downloadFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
        Log.d("taggy", "Output Format: " + this.outputFormat + ", Model: " + this.model);
    }

    public void showNoInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = requireActivity().getLayoutInflater().inflate(R.layout.custom_inetrnet_dailog, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView12);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        if (create.getWindow() != null) {
            create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryImage2ImageFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        create.show();
    }

    public void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(intent, 1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1 || i2 != -1 || intent == null || intent.getData() == null) {
            return;
        }
        this.getBackImageCard.setVisibility(0);
        this.textView10.setVisibility(8);
        this.textView101.setVisibility(0);
        Uri data = intent.getData();
        Glide.with(this).load(data).into(this.getBackImage);
        this.globalBase64Image = convertImageToBase64(data);
        Log.d("checkitlog", "onViewCreated: EditInputfrag " + this.globalBase64Image);
    }

    private void saveBase64StringToFile(String str) {
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "edityourimage.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            Toast.makeText(getActivity(), "Saved to " + file.getAbsolutePath(), 0).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error saving file", 0).show();
        }
    }

    private String convertImageToBase64(Uri uri) {
        try {
            return Base64.encodeToString(IOUtils.toByteArray(getActivity().getContentResolver().openInputStream(uri)), 2);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displaySpeechRecognizer() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        startActivityForResult(intent, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.RECORD_AUDIO"}, 101);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 101 && iArr.length > 0 && iArr[0] == 0) {
            return;
        }
        Toast.makeText(getActivity(), "Storage permission is required to save the file", 0).show();
    }

    private void setupRecyclerViewModel() {
        ItemModelAdapter itemModelAdapter = new ItemModelAdapter(Arrays.asList(new ItemModel("model_1.png", getString(R.string.absolute_reality), "absolute-reality-v1-8-1"), new ItemModel("model_2.png", getString(R.string.dream_shaper), "dream-shaper-v8"), new ItemModel("model_3.png", getString(R.string.realistic_vision), "realistic-vision-v5-1"), new ItemModel("model_4.png", getString(R.string.stable_diffusion), "stable-diffusion-xl-v1-0"), new ItemModel("model_5.png", getString(R.string.absolute_reality), "absolute-reality-v1-6"), new ItemModel("model_6.png", getString(R.string.dark_sushi), "dark-sushi-mix-v2-25")));
        this.recyclerView_model.setAdapter(itemModelAdapter);
        this.recyclerView_model.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        itemModelAdapter.setOnItemClickListener(new ItemModelAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.fragments.GeneralCategoryImage2ImageFragment.9
            @Override // com.demo.imageaigenerator24.adapter.ItemModelAdapter.OnItemClickListener
            public void onItemClick(ItemModel itemModel) {
                GeneralCategoryImage2ImageFragment.this.selectedModel = itemModel.getModel();
            }
        });
    }

    public void handleBackPress() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new FeatureFragment());
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}
