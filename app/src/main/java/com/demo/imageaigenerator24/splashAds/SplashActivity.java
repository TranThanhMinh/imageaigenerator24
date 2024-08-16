package com.demo.imageaigenerator24.splashAds;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.activities.HomeActivity;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.ads.MyApplication;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

/* loaded from: classes.dex */
public class SplashActivity extends AppCompatActivity {
    int check = 0;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    String var;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        AdsCommon.RegulerBanner(this, (RelativeLayout) findViewById(R.id.Admob_Banner_Frame), (LinearLayout) findViewById(R.id.banner_container), (FrameLayout) findViewById(R.id.qureka));
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(getResources().getColor(R.color.colorlight));
        }
        MyApplication.setuser_balance(0);
        refreshItem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshItem() {
        if (isNetworkConnected()) {
            new Handler().postDelayed(new Runnable() { // from class: com.demo.imageaigenerator24.splashAds.SplashActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    SplashActivity.this.OpenAppAds();
                }
            }, 6000L);
            return;
        }
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.setContentView(R.layout.no_internet);
        dialog.setCancelable(false);
        CardView cardView = (CardView) dialog.findViewById(R.id.refresh);
        CardView cardView2 = (CardView) dialog.findViewById(R.id.exit);
        final ImageView imageView = (ImageView) dialog.findViewById(R.id.img);
        final Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        imageView.startAnimation(loadAnimation);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.splashAds.SplashActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                imageView.startAnimation(loadAnimation);
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.splashAds.SplashActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SplashActivity.this.refreshItem();
                dialog.dismiss();
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.splashAds.SplashActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
                SplashActivity.this.finish();
                System.exit(0);
            }
        });
        dialog.show();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void OpenAppAds() {
        try {
            if (MyApplication.getuser_balance().intValue() == 0 && !MyApplication.App_Open.equals("")) {
                String str = MyApplication.App_Open;
                this.loadCallback = new AppOpenAd.AppOpenAdLoadCallback() { // from class: com.demo.imageaigenerator24.splashAds.SplashActivity.5
                    @Override // com.google.android.gms.ads.AdLoadCallback
                    public void onAdLoaded(AppOpenAd appOpenAd) {
                        super.onAdLoaded(appOpenAd);
                        appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() { // from class: com.demo.imageaigenerator24.splashAds.SplashActivity.5.1
                            @Override // com.google.android.gms.ads.FullScreenContentCallback
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                SplashActivity.this.goNext(1);
                            }

                            @Override // com.google.android.gms.ads.FullScreenContentCallback
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                            }

                            @Override // com.google.android.gms.ads.FullScreenContentCallback
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                SplashActivity.this.goNext(1);
                            }
                        });
                        appOpenAd.show(SplashActivity.this);
                    }

                    @Override // com.google.android.gms.ads.AdLoadCallback
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        SplashActivity.this.goNext(1);
                    }
                };
                AppOpenAd.load(this, str, new AdRequest.Builder().build(), 1, this.loadCallback);
            } else {
                goNext(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goNext(int i) {
        int i2 = this.check + 1;
        this.check = i2;
        if (i2 == 1) {
            loadOpenApp();
        }
    }

    private void loadOpenApp() {
        AdsCommon.OneTimeCall(this);
        if (MyApplication.getuser_onetime().intValue() == 0) {
            startActivity(new Intent(this, (Class<?>) PrivacyTermsActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else {
            startActivity(new Intent(this, (Class<?>) HomeActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }
}
