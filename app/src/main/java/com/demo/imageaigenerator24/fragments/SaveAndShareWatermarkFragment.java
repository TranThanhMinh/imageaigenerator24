package com.demo.imageaigenerator24.fragments;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.demo.imageaigenerator24.R;

/* loaded from: classes.dex */
public class SaveAndShareWatermarkFragment extends Fragment {
    Uri imageUri;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String string;
        View inflate = layoutInflater.inflate(R.layout.fragment_save_and_share_watermark, viewGroup, false);
        Bundle arguments = getArguments();
        if (arguments != null && (string = arguments.getString("imageUri")) != null) {
            this.imageUri = Uri.parse(string);
            Glide.with(this).load(this.imageUri).into((ImageView) inflate.findViewById(R.id.imageViewwatermark));
            if (getContext() != null) {
                final AlertDialog create = new AlertDialog.Builder(getContext()).setView(layoutInflater.inflate(R.layout.anime_saved_dailog, (ViewGroup) null)).create();
                if (create.getWindow() != null) {
                    create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                create.show();
                Window window = create.getWindow();
                if (window != null) {
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(window.getAttributes());
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
                    layoutParams.width = (int) (displayMetrics.widthPixels * 0.8d);
                    window.setAttributes(layoutParams);
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.demo.imageaigenerator24.fragments.SaveAndShareWatermarkFragment.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (create.isShowing()) {
                            create.dismiss();
                        }
                    }
                }, 2000L);
            } else {
                Log.e("DialogError", "Context is null, cannot show dialog.");
            }
        }
        inflate.findViewById(R.id.share_image_button).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.SaveAndShareWatermarkFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.addFlags(1);
                intent.setType("image/png");
                intent.putExtra("android.intent.extra.STREAM", SaveAndShareWatermarkFragment.this.imageUri);
                SaveAndShareWatermarkFragment.this.startActivity(Intent.createChooser(intent, "Share image using"));
            }
        });
        inflate.findViewById(R.id.backbtnsave).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.SaveAndShareWatermarkFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SaveAndShareWatermarkFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeNewFragment()).commit();
            }
        });
        return inflate;
    }

    public void handleBackPress() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new HomeNewFragment());
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}
