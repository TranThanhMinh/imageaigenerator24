package com.demo.imageaigenerator24.fragments;

import android.content.Intent;
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
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.adapter.AspectRatioAdapter;
import com.demo.imageaigenerator24.adapter.ItemAdapter;
import com.demo.imageaigenerator24.adapter.ItemModelAdapter;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.history.History;
import com.demo.imageaigenerator24.history.HistoryBottomSheetFragment;
import com.demo.imageaigenerator24.history.HistoryManager;
import com.demo.imageaigenerator24.model.AspectRatioModel;
import com.demo.imageaigenerator24.model.ItemModel;
import com.demo.imageaigenerator24.utils.PromptGenerator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.IOUtils;

/* loaded from: classes.dex */
public class EditYourImageWithModelFeatureBtnFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int REQUEST_STORAGE_PERMISSION = 101;
    private static final int SPEECH_REQUEST_CODE = 0;
    ConstraintLayout btnGeneratenew;
    public EditText editTextPrompt;
    public String globalBase64Image;
    private ImageView imageView;
    private ImageView imageViewButton;
    private PromptGenerator promptGenerator;
    RecyclerView recyclerView_model;
    private TextView responseTextView;
    String selectedModel;
    private ImageView speechtotext_btn;
    CardView uploadImage_btn;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    public String model = "stable-diffusion-xl-v1-0";
    public String outputFormat = "png";
    public String prompt = "Superman standing with super girl in galaxy and with cyborg girl";
    public int selectedHeight = 512;
    public int selectedWidth = 512;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_edit_your_image_with_model_feature_btn, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.imageView = (ImageView) view.findViewById(R.id.imageView);
        this.editTextPrompt = (EditText) view.findViewById(R.id.editTextPrompt);
        this.btnGeneratenew = (ConstraintLayout) view.findViewById(R.id.btnGeneratenew);
        this.responseTextView = (TextView) view.findViewById(R.id.responseTextView);
        this.imageViewButton = (ImageView) view.findViewById(R.id.imageView2);
        this.speechtotext_btn = (ImageView) view.findViewById(R.id.speechtotext_btn);
        this.promptGenerator = new PromptGenerator();
        this.recyclerView_model = (RecyclerView) view.findViewById(R.id.recyclerView_model);
        this.uploadImage_btn = (CardView) view.findViewById(R.id.uploadImage_btn);
        this.imageViewButton.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.EditYourImageWithModelFeatureBtnFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EditYourImageWithModelFeatureBtnFragment.this.editTextPrompt.setText(EditYourImageWithModelFeatureBtnFragment.this.promptGenerator.generateRandomPrompt());
            }
        });
        this.speechtotext_btn.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.EditYourImageWithModelFeatureBtnFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ContextCompat.checkSelfPermission(EditYourImageWithModelFeatureBtnFragment.this.getContext(), "android.permission.RECORD_AUDIO") != 0) {
                    EditYourImageWithModelFeatureBtnFragment.this.requestPermission();
                } else {
                    EditYourImageWithModelFeatureBtnFragment.this.displaySpeechRecognizer();
                }
            }
        });
        this.uploadImage_btn.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.EditYourImageWithModelFeatureBtnFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EditYourImageWithModelFeatureBtnFragment.this.pickImage();
            }
        });
        this.btnGeneratenew.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.EditYourImageWithModelFeatureBtnFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdsCommon.InterstitialAdsOnly(EditYourImageWithModelFeatureBtnFragment.this.getActivity());
                Log.d("taggy", "Button clicked!");
                EditYourImageWithModelFeatureBtnFragment editYourImageWithModelFeatureBtnFragment = EditYourImageWithModelFeatureBtnFragment.this;
                editYourImageWithModelFeatureBtnFragment.prompt = editYourImageWithModelFeatureBtnFragment.editTextPrompt.getText().toString();
                Log.d("taggy", "Prompt: " + EditYourImageWithModelFeatureBtnFragment.this.prompt);
                if (EditYourImageWithModelFeatureBtnFragment.this.selectedHeight <= 0 || EditYourImageWithModelFeatureBtnFragment.this.selectedWidth <= 0) {
                    Toast.makeText(EditYourImageWithModelFeatureBtnFragment.this.getActivity().getApplicationContext(), "Invalid dimensions!", 0).show();
                    return;
                }
                EditYourImageWithModelFeatureBtnFragment editYourImageWithModelFeatureBtnFragment2 = EditYourImageWithModelFeatureBtnFragment.this;
                editYourImageWithModelFeatureBtnFragment2.model = editYourImageWithModelFeatureBtnFragment2.selectedModel;
                new HistoryManager(EditYourImageWithModelFeatureBtnFragment.this.getActivity()).addHistoryItem(new History(EditYourImageWithModelFeatureBtnFragment.this.prompt, new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date())));
                DownloadFragment downloadFragment = new DownloadFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("model", EditYourImageWithModelFeatureBtnFragment.this.model);
                bundle2.putString("prompt", EditYourImageWithModelFeatureBtnFragment.this.prompt);
                bundle2.putInt("height", EditYourImageWithModelFeatureBtnFragment.this.selectedHeight);
                bundle2.putInt("width", EditYourImageWithModelFeatureBtnFragment.this.selectedWidth);
                bundle2.putString("outputFormat", EditYourImageWithModelFeatureBtnFragment.this.outputFormat);
                bundle2.putString("base64Image", EditYourImageWithModelFeatureBtnFragment.this.globalBase64Image);
                downloadFragment.setArguments(bundle2);
                EditYourImageWithModelFeatureBtnFragment.this.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, downloadFragment).addToBackStack(null).commit();
                Log.d("taggy", "Output Format: " + EditYourImageWithModelFeatureBtnFragment.this.outputFormat + ", Model: " + EditYourImageWithModelFeatureBtnFragment.this.model);
            }
        });
        view.findViewById(R.id.history_btn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.EditYourImageWithModelFeatureBtnFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AdsCommon.InterstitialAdsOnly(EditYourImageWithModelFeatureBtnFragment.this.getActivity());
                Log.d("MainActivityyyy", "History button clicked");
                HistoryBottomSheetFragment.newInstance().show(EditYourImageWithModelFeatureBtnFragment.this.getActivity().getSupportFragmentManager(), "historyFragment");
            }
        });
        setupRecyclerViewStyles(view);
        setupRecyclerViewModel();
        setupAspectRatioRV(view);
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
        this.globalBase64Image = convertImageToBase64(intent.getData());
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

    private void setupRecyclerViewStyles(View view) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ItemModel("style_image_1.png", "FANTASY", "FANTASY"));
        arrayList.add(new ItemModel("style_image_2.png", "VIBRANT", "VIBRANT"));
        arrayList.add(new ItemModel("style_image_3.png", "EUPHORIC", "EUPHORIC"));
        arrayList.add(new ItemModel("style_image_4.png", "GTA", "GTA"));
        arrayList.add(new ItemModel("style_image_5.png", "ANIME V2", "ANIME V2"));
        arrayList.add(new ItemModel("style_image_6.png", "COSMIC", "COSMIC"));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        ItemAdapter itemAdapter = new ItemAdapter(arrayList);
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.setOnStyleClickListener(new ItemAdapter.OnStyleClickListener() { // from class: com.demo.imageaigenerator24.fragments.EditYourImageWithModelFeatureBtnFragment.6
            @Override // com.demo.imageaigenerator24.adapter.ItemAdapter.OnStyleClickListener
            public void onStyleSelected(String str) {
                String str2 = str + " " + EditYourImageWithModelFeatureBtnFragment.this.editTextPrompt.getText().toString();
                EditYourImageWithModelFeatureBtnFragment.this.editTextPrompt.setText(str2);
                EditYourImageWithModelFeatureBtnFragment.this.editTextPrompt.setSelection(str2.length());
            }
        });
    }

    private void setupRecyclerViewModel() {
        ItemModelAdapter itemModelAdapter = new ItemModelAdapter(Arrays.asList(new ItemModel("model_1.png", getString(R.string.absolute_reality), "absolute-reality-v1-8-1"), new ItemModel("model_2.png", getString(R.string.dream_shaper), "dream-shaper-v8"), new ItemModel("model_3.png", getString(R.string.realistic_vision), "realistic-vision-v5-1"), new ItemModel("model_4.png", getString(R.string.stable_diffusion), "stable-diffusion-xl-v1-0"), new ItemModel("model_5.png", getString(R.string.absolute_reality), "absolute-reality-v1-6"), new ItemModel("model_6.png", getString(R.string.dark_sushi), "dark-sushi-mix-v2-25")));
        this.recyclerView_model.setAdapter(itemModelAdapter);
        this.recyclerView_model.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        itemModelAdapter.setOnItemClickListener(new ItemModelAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.fragments.EditYourImageWithModelFeatureBtnFragment.7
            @Override // com.demo.imageaigenerator24.adapter.ItemModelAdapter.OnItemClickListener
            public void onItemClick(ItemModel itemModel) {
                EditYourImageWithModelFeatureBtnFragment.this.selectedModel = itemModel.getModel();
                Toast.makeText(EditYourImageWithModelFeatureBtnFragment.this.getActivity(), "Selected Model: " + itemModel.getText(), 0).show();
            }
        });
    }

    private void setupAspectRatioRV(View view) {
        List asList = Arrays.asList(new AspectRatioModel("1:1"), new AspectRatioModel("4:3"), new AspectRatioModel("3:2"), new AspectRatioModel("16:9"));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_aspcetratio);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        recyclerView.setAdapter(new AspectRatioAdapter(asList, getActivity(), new AspectRatioAdapter.AspectRatioClickListener() { // from class: com.demo.imageaigenerator24.fragments.EditYourImageWithModelFeatureBtnFragment.8
            @Override // com.demo.imageaigenerator24.adapter.AspectRatioAdapter.AspectRatioClickListener
            public void onRatioSelected(String str) {
                EditYourImageWithModelFeatureBtnFragment.this.onRatioSelectedCall(str);
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
