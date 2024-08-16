package com.demo.imageaigenerator24.ads.admob;

import android.app.Activity;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.MyApplication;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;

/* loaded from: classes.dex */
public class AdMobNative {
    private static AdMobNative mInstance;
    public NativeAd nativeAd;

    public static AdMobNative getInstance() {
        if (mInstance == null) {
            mInstance = new AdMobNative();
        }
        return mInstance;
    }





    public void showNativeSmallFirst(final Activity activity, final FrameLayout frameLayout) {
        frameLayout.setVisibility(0);
        AdLoader.Builder builder = new AdLoader.Builder(activity, MyApplication.AdMob_NativeAdvance1);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobNative.9
            @Override // com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener
            public void onNativeAdLoaded(NativeAd nativeAd) {
                if ((Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) || activity.isFinishing() || activity.isChangingConfigurations()) {
                    nativeAd.destroy();
                    return;
                }
                if (AdMobNative.this.nativeAd != null) {
                    AdMobNative.this.nativeAd.destroy();
                }
                AdMobNative.this.nativeAd = nativeAd;
                NativeAdView nativeAdView = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.ads_google_small_native, (ViewGroup) null);
                AdMobNative.this.populateUnifiedNativeAdView2(nativeAd, nativeAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobNative.10
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                frameLayout.setVisibility(4);

                    AdMobNative.getInstance().showNativeSmallSecond(activity, frameLayout);
                    return;


            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    public void showNativeSmallSecond(final Activity activity, final FrameLayout frameLayout) {
        frameLayout.setVisibility(0);
        AdLoader.Builder builder = new AdLoader.Builder(activity, MyApplication.AdMob_NativeAdvance2);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobNative.11
            @Override // com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener
            public void onNativeAdLoaded(NativeAd nativeAd) {
                if ((Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) || activity.isFinishing() || activity.isChangingConfigurations()) {
                    nativeAd.destroy();
                    return;
                }
                if (AdMobNative.this.nativeAd != null) {
                    AdMobNative.this.nativeAd.destroy();
                }
                AdMobNative.this.nativeAd = nativeAd;
                NativeAdView nativeAdView = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.ads_google_small_native, (ViewGroup) null);
                AdMobNative.this.populateUnifiedNativeAdView2(nativeAd, nativeAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobNative.12
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                frameLayout.setVisibility(4);

                    AdMobNative.getInstance().showNativeSmallThird(activity, frameLayout);
                    return;


            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    public void showNativeSmallThird(final Activity activity, final FrameLayout frameLayout) {
        frameLayout.setVisibility(0);
        AdLoader.Builder builder = new AdLoader.Builder(activity, MyApplication.AdMob_NativeAdvance1);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobNative.13
            @Override // com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener
            public void onNativeAdLoaded(NativeAd nativeAd) {
                if ((Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) || activity.isFinishing() || activity.isChangingConfigurations()) {
                    nativeAd.destroy();
                    return;
                }
                if (AdMobNative.this.nativeAd != null) {
                    AdMobNative.this.nativeAd.destroy();
                }
                AdMobNative.this.nativeAd = nativeAd;
                NativeAdView nativeAdView = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.ads_google_small_native, (ViewGroup) null);
                AdMobNative.this.populateUnifiedNativeAdView2(nativeAd, nativeAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobNative.14
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                frameLayout.setVisibility(4);

                    AdMobNative.getInstance().showNativeSmallFour(activity, frameLayout);
                    return;

            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    public void showNativeSmallFour(final Activity activity, final FrameLayout frameLayout ) {
        frameLayout.setVisibility(0);
        AdLoader.Builder builder = new AdLoader.Builder(activity, MyApplication.AdMob_NativeAdvance2);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobNative.15
            @Override // com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener
            public void onNativeAdLoaded(NativeAd nativeAd) {
                if ((Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) || activity.isFinishing() || activity.isChangingConfigurations()) {
                    nativeAd.destroy();
                    return;
                }
                if (AdMobNative.this.nativeAd != null) {
                    AdMobNative.this.nativeAd.destroy();
                }
                AdMobNative.this.nativeAd = nativeAd;
                NativeAdView nativeAdView = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.ads_google_small_native, (ViewGroup) null);
                AdMobNative.this.populateUnifiedNativeAdView2(nativeAd, nativeAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobNative.16
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                frameLayout.setVisibility(4);
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }


    public void populateUnifiedNativeAdView2(NativeAd nativeAd, NativeAdView nativeAdView) {
        nativeAdView.setMediaView((MediaView) nativeAdView.findViewById(R.id.ad_media));
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body));
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.ad_call_to_action));
        nativeAdView.setIconView(nativeAdView.findViewById(R.id.ad_app_icon));
        nativeAdView.setPriceView(nativeAdView.findViewById(R.id.ad_price));
        nativeAdView.setStarRatingView(nativeAdView.findViewById(R.id.ad_stars));
        nativeAdView.setStoreView(nativeAdView.findViewById(R.id.ad_store));
        nativeAdView.setAdvertiserView(nativeAdView.findViewById(R.id.ad_advertiser));
        ((TextView) nativeAdView.getHeadlineView()).setText(nativeAd.getHeadline());
        if (nativeAd.getBody() == null) {
            nativeAdView.getBodyView().setVisibility(4);
        } else {
            nativeAdView.getBodyView().setVisibility(0);
            ((TextView) nativeAdView.getBodyView()).setText(nativeAd.getBody());
        }
        if (nativeAd.getCallToAction() == null) {
            nativeAdView.getCallToActionView().setVisibility(4);
        } else {
            nativeAdView.getCallToActionView().setVisibility(0);
            ((Button) nativeAdView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }
        if (nativeAd.getIcon() == null) {
            nativeAdView.getIconView().setVisibility(8);
        } else {
            ((ImageView) nativeAdView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
            nativeAdView.getIconView().setVisibility(0);
        }
        if (nativeAd.getPrice() == null) {
            nativeAdView.getPriceView().setVisibility(4);
        } else {
            nativeAdView.getPriceView().setVisibility(0);
            ((TextView) nativeAdView.getPriceView()).setText(nativeAd.getPrice());
        }
        if (nativeAd.getStore() == null) {
            nativeAdView.getStoreView().setVisibility(4);
        } else {
            nativeAdView.getStoreView().setVisibility(0);
            ((TextView) nativeAdView.getStoreView()).setText(nativeAd.getStore());
        }
        if (nativeAd.getStarRating() == null) {
            nativeAdView.getStarRatingView().setVisibility(4);
        } else {
            ((RatingBar) nativeAdView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            nativeAdView.getStarRatingView().setVisibility(0);
        }
        if (nativeAd.getAdvertiser() == null) {
            nativeAdView.getAdvertiserView().setVisibility(4);
        } else {
            ((TextView) nativeAdView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            nativeAdView.getAdvertiserView().setVisibility(0);
        }
        nativeAdView.setNativeAd(nativeAd);
    }
}
