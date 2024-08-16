package com.demo.imageaigenerator24.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.network.ApiResponse;
import com.demo.imageaigenerator24.splashAds.GivePermission;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class SaveAndShareActivity extends AppCompatActivity {
    public Bitmap bitmap;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    Uri imageUri;
    ImageView imageView;
    private AlertDialog loadingDialog;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_save_and_share);
        GivePermission.applyPermission();
        this.imageView = (ImageView) findViewById(R.id.ivrecieve);
        Intent intent = getIntent();
        intent.getStringExtra("model");
        intent.getStringExtra("prompt");
        int intExtra = intent.getIntExtra("height", 0);
        int intExtra2 = intent.getIntExtra("width", 0);
        Log.d("taggy", "height" + intExtra);
        Log.d("taggy", "width" + intExtra2);
        intent.getStringExtra("outputFormat");
        findViewById(R.id.backbtnsave).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.activities.SaveAndShareActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SaveAndShareActivity.this.finish();
            }
        });
        findViewById(R.id.download_btn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.activities.SaveAndShareActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly(SaveAndShareActivity.this);
                if (SaveAndShareActivity.this.bitmap != null) {
                    SaveAndShareActivity saveAndShareActivity = SaveAndShareActivity.this;
                    saveAndShareActivity.downloadImage(saveAndShareActivity.bitmap);
                } else {
                    Toast.makeText(SaveAndShareActivity.this, "Image not yet loaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.loading_dialog, (ViewGroup) null);
        builder.setView(inflate);
        builder.setCancelable(false);
        AlertDialog create = builder.create();
        this.loadingDialog = create;
        create.show();
    }

    private void makeApiCall(String str, String str2, int i, int i2, String str3) {
        showLoadingDialog();
    }

    private void processApiResponseInBackground(final ApiResponse apiResponse) {
        this.executorService.execute(new Runnable() { // from class: com.demo.imageaigenerator24.activities.SaveAndShareActivity.3
            @Override // java.lang.Runnable
            public void run() {
                String image = apiResponse.getImage();
                SaveAndShareActivity saveAndShareActivity = SaveAndShareActivity.this;
                saveAndShareActivity.bitmap = saveAndShareActivity.decodeBase64ToBitmap(image);
                final RequestOptions fitCenter = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter();
                SaveAndShareActivity.this.runOnUiThread(new Runnable() { // from class: com.demo.imageaigenerator24.activities.SaveAndShareActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Glide.with((FragmentActivity) SaveAndShareActivity.this).load(SaveAndShareActivity.this.bitmap).apply((BaseRequestOptions<?>) fitCenter).into(SaveAndShareActivity.this.imageView);
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

    public File downloadImage(Bitmap bitmap) {
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
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
