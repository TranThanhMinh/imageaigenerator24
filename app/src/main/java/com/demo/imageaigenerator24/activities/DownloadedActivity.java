package com.demo.imageaigenerator24.activities;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.demo.imageaigenerator24.R;

/* loaded from: classes.dex */
public class DownloadedActivity extends AppCompatActivity {
    Uri imageUri;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_downloaded);
        this.imageUri = Uri.parse(getIntent().getStringExtra("imageFileUri"));
        ImageView imageView = (ImageView) findViewById(R.id.ivrecieve);
        imageView.setImageURI(this.imageUri);
        Glide.with((FragmentActivity) this).load(this.imageUri).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter()).into(imageView);
    }
}
