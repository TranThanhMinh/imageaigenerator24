package com.demo.imageaigenerator24.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.admob.AdMobBanner;
import com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick;
import com.demo.imageaigenerator24.ads.admob.AdMobNative;


/* loaded from: classes.dex */
public class AdsCommon {
    public static void OneTimeCall(Context context) {
        if (MyApplication.getuser_balance().intValue() == 0) {
            Activity activity = (Activity) context;
            AdMobInterstitialClick.getInstance().loadInterOne(activity);
            AdMobInterstitialClick.getInstance().loadInterTwo(activity);
            AdMobInterstitialClick.getInstance().loadInterThree(activity);

        }
    }

    public static void InterstitialAdsOnly(Activity activity) {
        if (MyApplication.getuser_balance().intValue() == 0) {
           // MyApplication.AdsClickCount++;
            if (MyApplication.AdsClickCount == MyApplication.click) {
                MyApplication.AdsClickCount = 0;

                    AdMobInterstitialClick.getInstance().showInterOnlyAdMobFirst(activity, new AdMobInterstitialClick.MyCallback() { // from class: com.demo.imageaigenerator24.ads.AdsCommon.5
                        @Override // com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.MyCallback
                        public void callbackCall() {
                        }
                    });
            }
        }
    }



    public static void SmallNative(Context context, FrameLayout frameLayout) {
        if (MyApplication.getuser_balance().intValue() == 0) {

                AdMobNative.getInstance().showNativeSmallFirst((Activity) context, frameLayout);
                return;

        }
        frameLayout.setVisibility(8);
    }

    public static void RegulerBanner(Context context, RelativeLayout relativeLayout, LinearLayout linearLayout, FrameLayout frameLayout) {
        if (MyApplication.getuser_balance().intValue() == 0) {
                relativeLayout.setVisibility(View.VISIBLE);
                new AdMobBanner().showAd((Activity) context, relativeLayout, linearLayout, frameLayout, "type2", new AdMobBanner.adMobSmallAdCallback() { // from class: com.demo.imageaigenerator24.ads.AdsCommon.6
                    @Override // com.demo.imageaigenerator24.ads.admob.AdMobBanner.adMobSmallAdCallback
                    public void onAdError(String str) {
                        relativeLayout.setVisibility(8);
                        linearLayout.setVisibility(8);
                    }

                    @Override // com.demo.imageaigenerator24.ads.admob.AdMobBanner.adMobSmallAdCallback
                    public void onAdLoaded() {
                    }
                });
                return;
        }

    }
}
