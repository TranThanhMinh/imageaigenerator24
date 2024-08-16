package com.demo.imageaigenerator24.practicetwo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.fragments.DownloadFragment;
import com.demo.imageaigenerator24.practicetwo.DataAdapter;
import com.demo.imageaigenerator24.practicetwo.DataModel;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class Image2ImageFragment extends Fragment implements SpeechToTextListener, DataAdapter.OnGenerateClickListener, ImageUploadListener, DataAdapter.OnImage2ImageClickListener {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int REQUEST_STORAGE_PERMISSION = 101;
    private static final int SPEECH_REQUEST_CODE = 0;
    public static CollectedData collectedData = new CollectedData();
    public DataAdapter dataAdapter;
    private String globalBase64Image;
    SharedPrefsHelper prefs;
    RecyclerView recyclerView;
    private String inspirationText = null;
    private String outputFormat = "png";
    private Uri uriToDataAdpater = null;

    @Override // com.demo.imageaigenerator24.practicetwo.DataAdapter.OnImage2ImageClickListener
    public void onImage2ImageClick() {
    }

    public void setInspirationText(String str) {
        this.inspirationText = str;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_image2_image, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        collectedData.setModelAndStyle("absolute-reality-v1-8-1");
        this.prefs = SharedPrefsHelper.getInstance(getActivity());
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        DataAdapter dataAdapter = new DataAdapter(getActivity(), this.recyclerView, this, this, collectedData, this, this, this.prefs);
        this.dataAdapter = dataAdapter;
        String str = this.inspirationText;
        if (str != null) {
            dataAdapter.setSharedText(str);
        }
        this.dataAdapter.setSharedText("Imagine a woman handcrafted by the Gods: She emerging from molten lava, levitating above the heart of a volcano. She in a otherworldly dress made by fire and ashes that shapes her body perfectly. Her face resembles of a beautiful woman with some magical elements. Her hair is fiery red and is fueled by the very energy of life itself, a throwback to The Creation at the same time connecting with today nature. Every element of this portrait should come together naturally creating that most beautiful concept art ever created. ");
        this.dataAdapter.setData(getMockData());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.demo.imageaigenerator24.practicetwo.Image2ImageFragment.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                return Image2ImageFragment.this.dataAdapter.getItemViewType(i) == 7 ? 1 : 2;
            }
        });
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.dataAdapter);
        view.findViewById(R.id.appCompatButtonPro).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.Image2ImageFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
            }
        });
    }

    private List<DataModel> getMockData() {
        ArrayList arrayList = new ArrayList();
        getResources();
        arrayList.add(new DataModel.AItitle(getString(R.string.add_image)));
        arrayList.add(new DataModel.AIImage2Imgae(this.uriToDataAdpater));
        arrayList.add(new DataModel.AItitle(getString(R.string.enter_prompt)));
        arrayList.add(new DataModel.AIEditText(R.drawable.clear_btn_bg));
        arrayList.add(new DataModel.AItitle(getString(R.string.spinners)));
        arrayList.add(new DataModel.AISamplers());
        arrayList.add(new DataModel.AItitle(getString(R.string.aspect_ratios)));
        arrayList.add(new DataModel.AIRatios());
        arrayList.add(new DataModel.AItitle(getString(R.string.model_and_style)));
        arrayList.add(new DataModel.AIModelAndStyles());
        arrayList.add(new DataModel.AItitle(getString(R.string.advanced_settings)));
        arrayList.add(new DataModel.AIAdvanceSettings());
        arrayList.add(new DataModel.AIGenerate());
        arrayList.add(new DataModel.AItitle(getString(R.string.inspiration)));
        for (int i = 1; i <= 54; i++) {
            arrayList.add(new DataModel.AIImage(getString(getResources().getIdentifier("item_" + i + "_text", TypedValues.Custom.S_STRING, getActivity().getPackageName())), "inspiration__" + i + ".png"));
        }
        return arrayList;
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

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == -1) {
            DataAdapter.speechToTextInterface.onSpeechResult(intent.getStringArrayListExtra("android.speech.extra.RESULTS").get(0));
        }
        if (i != 1 || i2 != -1 || intent == null || intent.getData() == null) {
            return;
        }
        Uri data = intent.getData();
        List<DataModel> mockData = getMockData();
        ((DataModel.AIImage2Imgae) mockData.get(1)).setImageUri(data);
        this.dataAdapter.setData(mockData);
        this.dataAdapter.setSharedText(" ");
        this.dataAdapter.notifyItemChanged(1);
        String convertImageToBase64 = convertImageToBase64(data);
        this.globalBase64Image = convertImageToBase64;
        ((SharedViewModel) new ViewModelProvider(requireActivity()).get(SharedViewModel.class)).setBase64Image(convertImageToBase64);
    }

    private void displaySavedData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", 0);
        String string = sharedPreferences.getString("savedText", "DefaultText");
        String string2 = sharedPreferences.getString("savedDateTime", "DefaultDateTime");
        Log.d("SavedData", "Text: " + string);
        Log.d("SavedData", "DateTime: " + string2);
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
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.Image2ImageFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        create.show();
    }

    public void proceedWithDownload() {
        DownloadFragment downloadFragment = new DownloadFragment();
        Bundle bundle = new Bundle();
        bundle.putString("prompt", collectedData.getPrompt());
        bundle.putString("model", collectedData.getModelAndStyle());
        bundle.putString("negativePrompt", collectedData.getNegativePrompt());
        bundle.putInt("height", collectedData.getHeight());
        bundle.putInt("width", collectedData.getWidth());
        bundle.putString("scheduler", collectedData.getScheduler());
        bundle.putString("outputFormat", this.outputFormat);
        bundle.putInt("steps", collectedData.getSteps());
        bundle.putInt("seed", collectedData.getSeed());
        bundle.putFloat("guidance", collectedData.getGuidance());
        bundle.putString("negativeStyle", collectedData.getNegativeStyle());
        bundle.putString("sampler", collectedData.getSampler());
        bundle.putString("aspectRatio", collectedData.getAspectRatio());
        bundle.putString("base64ImagePath", this.globalBase64Image);
        bundle.putString("fromImage2Image", "image2image");
        downloadFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, downloadFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }

    @Override // com.demo.imageaigenerator24.practicetwo.DataAdapter.OnGenerateClickListener
    public void onGenerateClick() {
        proceedWithDownload();
    }

    @Override // com.demo.imageaigenerator24.practicetwo.ImageUploadListener
    public void onImageUploadClick() {
        pickImage();
    }

    public void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(intent, 1);
    }

    private String convertImageToBase64(Uri uri) {
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(rotateImageIfRequired(decodeStream, uri), 800, 600, false);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createScaledBitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Log.d("ImageCompression", "Original Size: " + decodeStream.getByteCount());
            Log.d("ImageCompression", "Compressed Size: " + byteArray.length);
            String encodeToString = Base64.encodeToString(byteArray, 2);
            File file = new File(getActivity().getCacheDir(), "tempBase64.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(encodeToString);
            fileWriter.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            Log.e("Base64 Conversion", "Error converting image to Base64", e);
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap rotateImageIfRequired(Bitmap bitmap, Uri uri) throws IOException {
        InputStream openInputStream = getActivity().getContentResolver().openInputStream(uri);
        if (openInputStream == null) {
            return bitmap;
        }
        int attributeInt = new ExifInterface(openInputStream).getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        if (attributeInt == 3) {
            return rotateImage(bitmap, 180);
        }
        if (attributeInt == 6) {
            return rotateImage(bitmap, 90);
        }
        return attributeInt != 8 ? bitmap : rotateImage(bitmap, 270);
    }

    private Bitmap rotateImage(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return createBitmap;
    }

    public void handleBackPress() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new EditImageFragment());
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}
