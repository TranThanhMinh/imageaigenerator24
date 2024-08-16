package com.demo.imageaigenerator24.ads.admob;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.demo.imageaigenerator24.ads.MyApplication;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

/* loaded from: classes.dex */
public class AdMobBanner {
    String TAG = "BannerAdClass";
    private Activity activity;
    LinearLayout adContainer;
    private AdView adView;
    private RelativeLayout admob_banner;
    private adMobSmallAdCallback listener;
    FrameLayout qureka;
    String type;

    /* loaded from: classes.dex */
    public interface adMobSmallAdCallback {
        void onAdError(String str);

        void onAdLoaded();
    }

    public void showAd(Activity activity, RelativeLayout relativeLayout, LinearLayout linearLayout, FrameLayout frameLayout, String str, adMobSmallAdCallback admobsmalladcallback) {
        this.activity = activity;
        this.listener = admobsmalladcallback;
        this.admob_banner = relativeLayout;
        this.adContainer = linearLayout;
        this.qureka = frameLayout;
        this.type = str;
        if (!isOnline()) {
            this.listener.onAdError("No Internet Connection");
        } else {
            loadAd();
        }
    }

    private void loadAd() {
        AdView adView = new AdView(this.activity);
        this.adView = adView;
        adView.setAdSize(getAdSize());
        this.adView.setAdUnitId(MyApplication.AdMob_Banner1);
        this.adView.setAdListener(new AdListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobBanner.1
            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                AdMobBanner.this.listener.onAdLoaded();
                AdMobBanner.this.admob_banner.removeAllViews();
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(14);
                AdMobBanner.this.admob_banner.addView(AdMobBanner.this.adView, layoutParams);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Log.e(AdMobBanner.this.TAG, "onAdFailedToLoad: " + loadAdError.toString());
                AdMobBanner.this.admob_banner.setVisibility(8);
                if (AdMobBanner.this.type.equals("type2")) {

                        AdMobBanner.this.admob_banner.setVisibility(0);
                        new AdMobBanner().showAd(AdMobBanner.this.activity, AdMobBanner.this.admob_banner, AdMobBanner.this.adContainer, AdMobBanner.this.qureka, "type3", new adMobSmallAdCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobBanner.1.1
                            @Override // com.demo.imageaigenerator24.ads.admob.AdMobBanner.adMobSmallAdCallback
                            public void onAdError(String str) {
                            }

                            @Override // com.demo.imageaigenerator24.ads.admob.AdMobBanner.adMobSmallAdCallback
                            public void onAdLoaded() {
                            }
                        });
                        return;

                }
                if (AdMobBanner.this.type.equals("type3")) {
                        AdMobBanner.this.admob_banner.setVisibility(0);
                        new AdMobBanner().showAd(AdMobBanner.this.activity, AdMobBanner.this.admob_banner, AdMobBanner.this.adContainer, AdMobBanner.this.qureka, "type4", new adMobSmallAdCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobBanner.1.2
                            @Override // com.demo.imageaigenerator24.ads.admob.AdMobBanner.adMobSmallAdCallback
                            public void onAdError(String str) {
                            }

                            @Override // com.demo.imageaigenerator24.ads.admob.AdMobBanner.adMobSmallAdCallback
                            public void onAdLoaded() {
                            }
                        });
                        return;

                }
                if (AdMobBanner.this.type.equals("type4")) {

                        AdMobBanner.this.admob_banner.setVisibility(0);
                        new AdMobBanner().showAd(AdMobBanner.this.activity, AdMobBanner.this.admob_banner, AdMobBanner.this.adContainer, AdMobBanner.this.qureka, "", new adMobSmallAdCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobBanner.1.3
                            @Override // com.demo.imageaigenerator24.ads.admob.AdMobBanner.adMobSmallAdCallback
                            public void onAdError(String str) {
                            }

                            @Override // com.demo.imageaigenerator24.ads.admob.AdMobBanner.adMobSmallAdCallback
                            public void onAdLoaded() {
                            }
                        });

                }
            }
        });
        this.adView.loadAd(new AdRequest.Builder().build());
    }

    private AdSize getAdSize() {
        Display defaultDisplay = this.activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this.activity, (int) (displayMetrics.widthPixels / displayMetrics.density));
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.activity.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
