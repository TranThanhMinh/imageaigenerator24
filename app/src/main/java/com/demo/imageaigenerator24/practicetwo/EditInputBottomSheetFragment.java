package com.demo.imageaigenerator24.practicetwo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.fragments.DownloadFragment;
import com.demo.imageaigenerator24.practicetwo.DataAdapter;
import com.demo.imageaigenerator24.practicetwo.DataModel;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EditInputBottomSheetFragment extends BottomSheetDialogFragment implements SpeechToTextListener, DataAdapter.OnGenerateClickListener, DataAdapter.OnImage2ImageClickListener {
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int REQUEST_STORAGE_PERMISSION = 101;
    private static final int SPEECH_REQUEST_CODE = 0;
    View arrow_choose_settings;
    private DataAdapter dataAdapter;
    private DrawerLayout drawerLayout;
    ImageView imageView2ImageBtn;
    String interUploadID;
    boolean isInterUploadEnabled;
    ImageView lock_layout;
    private NavigationView navView;
    SharedPrefsHelper prefs;
    RecyclerView recyclerView;
    private String globalBase64Image = "";
    private String inspirationText = null;
    private String outputFormat = "png";

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_edit_input_bottom_sheet, viewGroup, false);
        this.prefs = SharedPrefsHelper.getInstance(getActivity());
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.dataAdapter = new DataAdapter(getActivity(), this.recyclerView, this, this, DataAdapter.collectedData, this, this.prefs);
        ((SharedViewModel) new ViewModelProvider(requireActivity()).get(SharedViewModel.class)).getBase64Image().observe(getViewLifecycleOwner(), new Observer<String>() { // from class: com.demo.imageaigenerator24.practicetwo.EditInputBottomSheetFragment.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(String str) {
                if (str != null) {
                    EditInputBottomSheetFragment.this.globalBase64Image = str;
                    Log.d("DownloadFragment", "onCreateView: with livedata " + str);
                } else {
                    Log.d("DownloadFragment", "onCreateView: LiveData is null");
                    EditInputBottomSheetFragment.this.globalBase64Image = "";
                }
            }
        });
        if (getArguments() != null) {
            this.dataAdapter.setSharedText(getArguments().getString("text_key"));
        }
        this.dataAdapter.setData(getMockData());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.dataAdapter);
        return inflate;
    }

    private List<DataModel> getMockData() {
        ArrayList arrayList = new ArrayList();
        getResources();
        arrayList.add(new DataModel.AItitle(getString(R.string.enter_prompt)));
        arrayList.add(new DataModel.AIEditText(R.drawable.clear_btn_bg));
        arrayList.add(new DataModel.AItitle(getString(R.string.spinners)));
        arrayList.add(new DataModel.AISamplers());
        arrayList.add(new DataModel.AItitle(getString(R.string.advanced_settings)));
        arrayList.add(new DataModel.AIAdvanceSettings());
        arrayList.add(new DataModel.AIGenerate());
        return arrayList;
    }

    @Override // com.demo.imageaigenerator24.practicetwo.DataAdapter.OnGenerateClickListener
    public void onGenerateClick() {
        proceedWithDownload();
    }

    public void proceedWithDownload() {
        DownloadFragment downloadFragment = new DownloadFragment();
        Bundle bundle = new Bundle();
        bundle.putString("model", DataAdapter.collectedData.getModelAndStyle());
        bundle.putString("prompt", DataAdapter.collectedData.getPrompt());
        bundle.putString("negativePrompt", DataAdapter.collectedData.getNegativePrompt());
        bundle.putInt("height", DataAdapter.collectedData.getHeight());
        bundle.putInt("width", DataAdapter.collectedData.getWidth());
        bundle.putString("scheduler", DataAdapter.collectedData.getScheduler());
        bundle.putString("outputFormat", this.outputFormat);
        bundle.putInt("steps", DataAdapter.collectedData.getSteps());
        bundle.putInt("seed", DataAdapter.collectedData.getSeed());
        bundle.putFloat("guidance", DataAdapter.collectedData.getGuidance());
        bundle.putString("negativeStyle", DataAdapter.collectedData.getNegativeStyle());
        bundle.putString("sampler", DataAdapter.collectedData.getSampler());
        bundle.putString("aspectRatio", DataAdapter.collectedData.getAspectRatio());
        if (!this.globalBase64Image.isEmpty()) {
            bundle.putString("base64ImagePath", this.globalBase64Image);
            bundle.putString("fromImage2Image", "image2image");
        }
        downloadFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, downloadFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
        dismiss();
    }

    @Override // com.demo.imageaigenerator24.practicetwo.DataAdapter.OnImage2ImageClickListener
    public void onImage2ImageClick() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Image2ImageFragment()).commit();
    }

    @Override // com.demo.imageaigenerator24.practicetwo.SpeechToTextListener
    public void requestSpeechToTextPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.RECORD_AUDIO"}, 101);
    }

    @Override // com.demo.imageaigenerator24.practicetwo.SpeechToTextListener
    public void startSpeechToText() {
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

    @Override // com.demo.imageaigenerator24.practicetwo.SpeechToTextListener
    public void onSpeechResult(String str) {
        this.dataAdapter.setSpokenText(str);
    }

    public void handleBackPress() {
        dismiss();
    }
}
