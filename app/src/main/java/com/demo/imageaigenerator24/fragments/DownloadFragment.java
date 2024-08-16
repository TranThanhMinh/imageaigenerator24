package com.demo.imageaigenerator24.fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.network.ApiResponse;
import com.demo.imageaigenerator24.network.ImgApiService;
import com.demo.imageaigenerator24.network.RetrofitInstance;
import com.demo.imageaigenerator24.practicetwo.EditInputBottomSheetFragment;
import com.demo.imageaigenerator24.practicetwo.Image2ImageFragment;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class DownloadFragment extends Fragment {
    public static Bundle bundle = new Bundle();
    String base64Image;
    String base64ImagePath;
    public Bitmap bitmap;
    CardView cardView2;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    TextView finalprompttv;
    Fragment fragmentToNavigate;
    Uri imageUri;
    ImageView imageView;
    ImageView imageView10;
    ImageView ivrecieve;
    public AlertDialog loadingDialog;
    SharedPrefsHelper prefs;
    ProgressBar progres_Watermark;
    ConstraintLayout remove_watermark_btn;
    TextView textView20;
    TextView textView21;
    TextView textView5;
    Uri uri;
    ImageView watermark_logo;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle2) {
        super.onCreate(bundle2);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle2) {
        return layoutInflater.inflate(R.layout.activity_save_and_share, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle2) {
        super.onViewCreated(view, bundle2);
        this.prefs = SharedPrefsHelper.getInstance(getActivity());
        this.remove_watermark_btn = (ConstraintLayout) view.findViewById(R.id.remove_watermark_btn);
        final TextView textView = (TextView) view.findViewById(R.id.finalprompttv);
        ((ConstraintLayout) view.findViewById(R.id.editinput_layoyut)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.DownloadFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EditInputBottomSheetFragment editInputBottomSheetFragment = new EditInputBottomSheetFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString("text_key", textView.getText().toString());
                editInputBottomSheetFragment.setArguments(bundle3);
                editInputBottomSheetFragment.show(DownloadFragment.this.getActivity().getSupportFragmentManager(), editInputBottomSheetFragment.getTag());
            }
        });
        this.imageView = (ImageView) view.findViewById(R.id.ivrecieve);
        this.watermark_logo = (ImageView) view.findViewById(R.id.watermark_logo);
        this.cardView2 = (CardView) view.findViewById(R.id.cardView2);
        this.imageView10 = (ImageView) view.findViewById(R.id.imageView10);
        this.textView20 = (TextView) view.findViewById(R.id.textView20);
        this.textView21 = (TextView) view.findViewById(R.id.textView21);
        this.textView5 = (TextView) view.findViewById(R.id.textView5);
        this.progres_Watermark = (ProgressBar) view.findViewById(R.id.progres_Watermark);
        this.finalprompttv = (TextView) view.findViewById(R.id.finalprompttv);
        if (HomeFragment.increment == 1) {
            this.imageView10.setImageResource(R.drawable.sorry_icon);
            this.textView20.setText("Sorry");
            this.textView20.setGravity(17);
            this.textView20.setTextSize(10.0f);
            this.textView20.setText("Results are not as per expectation you need to learn prompt input or try again.");
        } else if (HomeFragment.increment == 2) {
            this.imageView10.setImageResource(R.drawable.pretty_emoji_face);
            this.textView20.setText("Cool try");
            this.textView20.setGravity(17);
            this.textView20.setTextSize(10.0f);
            this.textView20.setText("Results are good but you can do it much better in magic world");
        } else {
            this.imageView10.setImageResource(R.drawable.hugging_face);
            this.textView20.setText("Excellent");
            this.textView20.setTextSize(10.0f);
            this.textView20.setGravity(17);
            this.textView20.setText("Congratulation! you are the champion of magical world");
        }
        getArguments();
        if (getArguments() != null) {
            String string = getArguments().getString("model");
            String string2 = getArguments().getString("prompt");
            String string3 = getArguments().getString("negativePrompt");
            int i = getArguments().getInt("height", 0);
            int i2 = getArguments().getInt("width", 0);
            int i3 = getArguments().getInt("seed", 0);
            float f = getArguments().getFloat("guidance", 0.0f);
            int i4 = getArguments().getInt("steps", 0);
            String string4 = getArguments().getString("sampler");
            String string5 = getArguments().getString("outputFormat");
            String string6 = getArguments().getString("base64ImagePath");
            this.base64ImagePath = string6;
            if (string6 == null || string6.isEmpty()) {
                Log.d("DownloadFragment", "argument base64 is null: ");
            } else {
                this.base64Image = readBase64StringFromFile(this.base64ImagePath);
            }
            Log.d("DownloadFragment", "Model: " + string);
            Log.d("DownloadFragment", "Prompt: " + string2);
            Log.d("DownloadFragment", "Negative Prompt: " + string3);
            Log.d("DownloadFragment", "Height: " + i);
            Log.d("DownloadFragment", "Width: " + i2);
            Log.d("DownloadFragment", "Seed: " + i3);
            Log.d("DownloadFragment", "Guidance: " + f);
            Log.d("DownloadFragment", "Sampler: " + string4);
            Log.d("DownloadFragment", "outputFormat: " + string5);
            Log.d("DownloadFragment", "Base64 Image: " + this.base64Image);
            makeApiCall(string, string2, string3, string5, i, i2, string4, f, i3, i4, this.base64Image);
            this.finalprompttv.setText(string2);
        }
        view.findViewById(R.id.backbtnsave).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.DownloadFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (DownloadFragment.this.getArguments() == null || !"image2image".equals(DownloadFragment.this.getArguments().getString("fromImage2Image"))) {
                    DownloadFragment.this.fragmentToNavigate = new HomeNewFragment();
                } else {
                    DownloadFragment.this.fragmentToNavigate = new Image2ImageFragment();
                }
                FragmentTransaction beginTransaction = DownloadFragment.this.getParentFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.fragment_container, DownloadFragment.this.fragmentToNavigate);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
            }
        });
        view.findViewById(R.id.variate_btn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.DownloadFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (DownloadFragment.this.getArguments() == null || !"image2image".equals(DownloadFragment.this.getArguments().getString("fromImage2Image"))) {
                    DownloadFragment.this.fragmentToNavigate = new HomeNewFragment();
                } else {
                    DownloadFragment.this.fragmentToNavigate = new Image2ImageFragment();
                }
                FragmentTransaction beginTransaction = DownloadFragment.this.getParentFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.fragment_container, DownloadFragment.this.fragmentToNavigate);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
            }
        });
        view.findViewById(R.id.remove_watermark_btn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.DownloadFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
            }
        });
        view.findViewById(R.id.dwoanadload_btn_new).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.DownloadFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DownloadFragment downloadFragment = DownloadFragment.this;
                Bitmap bitmapFromCardView = downloadFragment.getBitmapFromCardView(downloadFragment.cardView2);
                if (bitmapFromCardView != null) {
                    String str = System.currentTimeMillis() + ".jpg";
                    try {
                        DownloadFragment downloadFragment2 = DownloadFragment.this;
                        downloadFragment2.uri = downloadFragment2.bitmapToUri(bitmapFromCardView, str, downloadFragment2.getContext());
                        if (DownloadFragment.this.uri == null) {
                            return;
                        }
                        DownloadFragment.this.watermark_logo.setVisibility(8);
                        DownloadFragment.bundle.putString("imageUri", DownloadFragment.this.uri.toString());
                        SaveAndShareWatermarkFragment saveAndShareWatermarkFragment = new SaveAndShareWatermarkFragment();
                        saveAndShareWatermarkFragment.setArguments(DownloadFragment.bundle);
                        FragmentTransaction beginTransaction = DownloadFragment.this.getParentFragmentManager().beginTransaction();
                        beginTransaction.replace(R.id.fragment_container, saveAndShareWatermarkFragment);
                        beginTransaction.addToBackStack(null);
                        beginTransaction.commit();
                        return;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                Toast.makeText(DownloadFragment.this.requireContext(), "Image not yet loaded", 0).show();
            }
        });
    }

    private String readBase64StringFromFile(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(str)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void handleBackPress() {
        Fragment homeNewFragment;
        if (getArguments() == null || !"image2image".equals(getArguments().getString("fromImage2Image"))) {
            homeNewFragment = new HomeNewFragment();
        } else {
            homeNewFragment = new Image2ImageFragment();
        }
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, homeNewFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }

    public Uri bitmapToUri(Bitmap bitmap, String str, Context context) throws IOException {
        if (Build.VERSION.SDK_INT >= 29) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", str);
            contentValues.put("mime_type", "image/jpg");
            contentValues.put("relative_path", Environment.DIRECTORY_DCIM);
            Uri insert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            OutputStream openOutputStream = contentResolver.openOutputStream((Uri) Objects.requireNonNull(insert));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, openOutputStream);
            ((OutputStream) Objects.requireNonNull(openOutputStream)).close();
            return insert;
        }
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), str);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        fileOutputStream.close();
        return Uri.fromFile(file);
    }

    private void saveBase64StringToFile(String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "downloadfragment.txt"));
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = getLayoutInflater().inflate(R.layout.loading_dialog, (ViewGroup) null);
        builder.setView(inflate);
        builder.setCancelable(false);
        AlertDialog create = builder.create();
        this.loadingDialog = create;
        create.show();
    }

    private void makeApiCall(String str, String str2, String str3, String str4, int i, int i2, String str5, float f, int i3, int i4, String str6) {
        Call<ApiResponse> createImage;
        showLoadingDialog();
        if (str6 != null) {
            createImage = RetrofitInstance.getApi().createImageFromImage(new ImgApiService.ImageFromImageRequestBody(str, str2, str3, str4, i, i2, str5, i3, f, i4, str6));
        } else {
            createImage = RetrofitInstance.getApi().createImage(new ImgApiService.ImageRequestBody(str, str2, str3, str4, i, i2, str5, i3, f, i4));
        }
        if (createImage != null) {
            createImage.enqueue(new Callback<ApiResponse>() { // from class: com.demo.imageaigenerator24.fragments.DownloadFragment.6
                @Override // retrofit2.Callback
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    DownloadFragment.this.loadingDialog.dismiss();
                    if (!response.isSuccessful() || response.body() == null) {
                        try {
                            String string = response.errorBody() != null ? response.errorBody().string() : "Unknown Error";
                            DownloadFragment.this.loadingDialog.dismiss();
                            DownloadFragment.this.imageView.setBackgroundResource(R.drawable.inspiration__10);
                            Log.d("DownloadFragment", "Error: " + string);
                            Log.d("DownloadFragment", "onResponse: Received null body or unsuccessful response");
                            return;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    DownloadFragment.this.processApiResponseInBackground(response.body());
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<ApiResponse> call, Throwable th) {
                    DownloadFragment.this.loadingDialog.dismiss();
                    DownloadFragment.this.imageView.setBackgroundResource(R.drawable.inspiration__10);
                    Log.d("DownloadFragment", "onFailure: " + th.getMessage());
                }
            });
        } else {
            Log.d("DownloadFragment", "Failed to create API call");
        }
    }

    public void processApiResponseInBackground(final ApiResponse apiResponse) {
        this.executorService.execute(new Runnable() { // from class: com.demo.imageaigenerator24.fragments.DownloadFragment.7
            @Override // java.lang.Runnable
            public void run() {
                String image = apiResponse.getImage();
                DownloadFragment downloadFragment = DownloadFragment.this;
                downloadFragment.bitmap = downloadFragment.decodeBase64ToBitmap(image);
                final RequestOptions fitCenter = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter();
                DownloadFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.demo.imageaigenerator24.fragments.DownloadFragment.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Glide.with(DownloadFragment.this.getActivity()).load(DownloadFragment.this.bitmap).apply((BaseRequestOptions<?>) fitCenter).into(DownloadFragment.this.imageView);
                        Log.d("taggy", "Processed image in background and updated UI");
                    }
                });
            }
        });
    }

    public Bitmap decodeBase64ToBitmap(String str) {
        byte[] decode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    private Uri downloadImage(Bitmap bitmap) {
        try {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!externalStoragePublicDirectory.exists()) {
                externalStoragePublicDirectory.mkdir();
            }
            File file = new File(externalStoragePublicDirectory, "downloadedImage_" + System.currentTimeMillis() + ".png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Log.d("taggy", "Image saved at " + file.getAbsolutePath());
            return Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap getBitmapFromCardView(CardView cardView) {
        Bitmap createBitmap = Bitmap.createBitmap(cardView.getWidth(), cardView.getHeight(), Bitmap.Config.ARGB_8888);
        cardView.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    private Uri saveBitmapToDownloads(Bitmap bitmap) {
        try {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!externalStoragePublicDirectory.exists()) {
                externalStoragePublicDirectory.mkdir();
            }
            File file = new File(externalStoragePublicDirectory, "downloadedImage_" + System.currentTimeMillis() + ".png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Log.d("taggy", "Image saved at " + file.getAbsolutePath());
            return Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
