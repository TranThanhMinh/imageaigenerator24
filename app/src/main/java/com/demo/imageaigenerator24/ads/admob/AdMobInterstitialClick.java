package com.demo.imageaigenerator24.ads.admob;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.MyApplication;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

/* loaded from: classes.dex */
public class AdMobInterstitialClick {
    private static AdMobInterstitialClick mInstance;
    public InterstitialAd interstitialOne;
    public InterstitialAd interstitialThree;
    public InterstitialAd interstitialTwo;
    MyCallback myCallback;

    /* loaded from: classes.dex */
    public interface MyCallback {
        void callbackCall();
    }

    public static AdMobInterstitialClick getInstance() {
        if (mInstance == null) {
            mInstance = new AdMobInterstitialClick();
        }
        return mInstance;
    }

    public void loadInterOne(final Activity activity) {
        MobileAds.initialize(activity, new OnInitializationCompleteListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.1
            @Override // com.google.android.gms.ads.initialization.OnInitializationCompleteListener
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        InterstitialAd.load(activity, MyApplication.AdMob_Int1, new AdRequest.Builder().build(), new InterstitialAdLoadCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.2
            @Override // com.google.android.gms.ads.AdLoadCallback
            public void onAdLoaded(InterstitialAd interstitialAd) {
                AdMobInterstitialClick.this.interstitialOne = interstitialAd;
                Log.i("TAG", "onAdLoaded 1");
                AdMobInterstitialClick.this.interstitialOne.setFullScreenContentCallback(new FullScreenContentCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.2.1
                    @Override // com.google.android.gms.ads.FullScreenContentCallback
                    public void onAdDismissedFullScreenContent() {
                        AdMobInterstitialClick.this.interstitialOne = null;
                        Log.d("TAG", "The ad 1 was dismissed.");
                        AdMobInterstitialClick.this.loadInterOne(activity);
                        if (AdMobInterstitialClick.this.myCallback != null) {
                            AdMobInterstitialClick.this.myCallback.callbackCall();
                            AdMobInterstitialClick.this.myCallback = null;
                        }
                    }

                    @Override // com.google.android.gms.ads.FullScreenContentCallback
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        AdMobInterstitialClick.this.interstitialOne = null;
                        Log.d("TAG", "The ad 1 failed to show.");
                        AdMobInterstitialClick.this.loadInterOne(activity);
                    }
                });
            }

            @Override // com.google.android.gms.ads.AdLoadCallback
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Log.d("TAG", "The ad 1 Load Error.");
                AdMobInterstitialClick.this.interstitialOne = null;
            }
        });
    }

    public void loadInterTwo(final Activity activity) {
        MobileAds.initialize(activity, new OnInitializationCompleteListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.3
            @Override // com.google.android.gms.ads.initialization.OnInitializationCompleteListener
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        InterstitialAd.load(activity, MyApplication.AdMob_Int2, new AdRequest.Builder().build(), new InterstitialAdLoadCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.4
            @Override // com.google.android.gms.ads.AdLoadCallback
            public void onAdLoaded(InterstitialAd interstitialAd) {
                AdMobInterstitialClick.this.interstitialTwo = interstitialAd;
                Log.i("TAG", "onAdLoaded 2");
                AdMobInterstitialClick.this.interstitialTwo.setFullScreenContentCallback(new FullScreenContentCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.4.1
                    @Override // com.google.android.gms.ads.FullScreenContentCallback
                    public void onAdDismissedFullScreenContent() {
                        AdMobInterstitialClick.this.interstitialTwo = null;
                        Log.d("TAG", "The ad 2 was dismissed.");
                        AdMobInterstitialClick.this.loadInterTwo(activity);
                        if (AdMobInterstitialClick.this.myCallback != null) {
                            AdMobInterstitialClick.this.myCallback.callbackCall();
                            AdMobInterstitialClick.this.myCallback = null;
                        }
                    }

                    @Override // com.google.android.gms.ads.FullScreenContentCallback
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        AdMobInterstitialClick.this.interstitialTwo = null;
                        Log.d("TAG", "The ad 2 failed to show.");
                        AdMobInterstitialClick.this.loadInterTwo(activity);
                    }
                });
            }

            @Override // com.google.android.gms.ads.AdLoadCallback
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Log.d("TAG", "The ad 2 Load Error.");
                AdMobInterstitialClick.this.interstitialTwo = null;
            }
        });
    }

    public void loadInterThree(final Activity activity) {
        MobileAds.initialize(activity, new OnInitializationCompleteListener() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.5
            @Override // com.google.android.gms.ads.initialization.OnInitializationCompleteListener
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        InterstitialAd.load(activity, MyApplication.AdMob_Int1, new AdRequest.Builder().build(), new InterstitialAdLoadCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.6
            @Override // com.google.android.gms.ads.AdLoadCallback
            public void onAdLoaded(InterstitialAd interstitialAd) {
                AdMobInterstitialClick.this.interstitialThree = interstitialAd;
                Log.i("TAG", "onAdLoaded 2");
                AdMobInterstitialClick.this.interstitialThree.setFullScreenContentCallback(new FullScreenContentCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.6.1
                    @Override // com.google.android.gms.ads.FullScreenContentCallback
                    public void onAdDismissedFullScreenContent() {
                        AdMobInterstitialClick.this.interstitialThree = null;
                        Log.d("TAG", "The ad 2 was dismissed.");
                        AdMobInterstitialClick.this.loadInterThree(activity);
                        if (AdMobInterstitialClick.this.myCallback != null) {
                            AdMobInterstitialClick.this.myCallback.callbackCall();
                            AdMobInterstitialClick.this.myCallback = null;
                        }
                    }

                    @Override // com.google.android.gms.ads.FullScreenContentCallback
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        AdMobInterstitialClick.this.interstitialThree = null;
                        Log.d("TAG", "The ad 2 failed to show.");
                        AdMobInterstitialClick.this.loadInterThree(activity);
                    }
                });
            }

            @Override // com.google.android.gms.ads.AdLoadCallback
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Log.d("TAG", "The ad 2 Load Error.");
                AdMobInterstitialClick.this.interstitialThree = null;
            }
        });
    }






    public void showInterOnlyAdMobFirst(Activity activity, MyCallback myCallback) {
        InterstitialAd interstitialAd = this.interstitialOne;
        if (interstitialAd != null) {
            interstitialAd.show(activity);
            return;
        }
        InterstitialAd interstitialAd2 = this.interstitialTwo;
        if (interstitialAd2 != null) {
            interstitialAd2.show(activity);
            return;
        }
        InterstitialAd interstitialAd3 = this.interstitialThree;
        if (interstitialAd3 != null) {
            interstitialAd3.show(activity);
        }else if (!MyApplication.Type2.contains("qureka")) {
            activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.fadeout);
        }
        else {
            getInstance().showInterOnlyAdMobSecond(activity, new MyCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.16
                @Override // com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.MyCallback
                public void callbackCall() {
                }
            });
        }
        MyCallback myCallback2 = this.myCallback;
        if (myCallback2 != null) {
            myCallback2.callbackCall();
            this.myCallback = null;
        }
    }

    public void showInterOnlyAdMobSecond(Activity activity, MyCallback myCallback) {
        InterstitialAd interstitialAd = this.interstitialOne;
        if (interstitialAd != null) {
            interstitialAd.show(activity);
            return;
        }
        InterstitialAd interstitialAd2 = this.interstitialTwo;
        if (interstitialAd2 != null) {
            interstitialAd2.show(activity);
            return;
        }
        InterstitialAd interstitialAd3 = this.interstitialThree;
        if (interstitialAd3 != null) {
            interstitialAd3.show(activity);
        }
        else if (!MyApplication.Type3.contains("qureka")) {
            activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.fadeout);
        }else {
            getInstance().showInterOnlyAdMobThird(activity, new MyCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.17
                @Override // com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.MyCallback
                public void callbackCall() {
                }
            });
        }
        MyCallback myCallback2 = this.myCallback;
        if (myCallback2 != null) {
            myCallback2.callbackCall();
            this.myCallback = null;
        }
    }

    public void showInterOnlyAdMobThird(Activity activity, MyCallback myCallback) {
        InterstitialAd interstitialAd = this.interstitialOne;
        if (interstitialAd != null) {
            interstitialAd.show(activity);
            return;
        }
        InterstitialAd interstitialAd2 = this.interstitialTwo;
        if (interstitialAd2 != null) {
            interstitialAd2.show(activity);
            return;
        }
        InterstitialAd interstitialAd3 = this.interstitialThree;
        if (interstitialAd3 != null) {
            interstitialAd3.show(activity);
        } else if (!MyApplication.Type4.contains("qureka")) {
            activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.fadeout);
        }
        else  {
            getInstance().showInterOnlyAdMobFour(activity, new MyCallback() { // from class: com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.18
                @Override // com.demo.imageaigenerator24.ads.admob.AdMobInterstitialClick.MyCallback
                public void callbackCall() {
                }
            });
        }
        MyCallback myCallback2 = this.myCallback;
        if (myCallback2 != null) {
            myCallback2.callbackCall();
            this.myCallback = null;
        }
    }

    public void showInterOnlyAdMobFour(Activity activity, MyCallback myCallback) {
        InterstitialAd interstitialAd = this.interstitialOne;
        if (interstitialAd != null) {
            interstitialAd.show(activity);
            return;
        }
        InterstitialAd interstitialAd2 = this.interstitialTwo;
        if (interstitialAd2 != null) {
            interstitialAd2.show(activity);
            return;
        }
        InterstitialAd interstitialAd3 = this.interstitialThree;
        if (interstitialAd3 != null) {
            interstitialAd3.show(activity);
        } else {
            activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.fadeout);
        }
        MyCallback myCallback2 = this.myCallback;
        if (myCallback2 != null) {
            myCallback2.callbackCall();
            this.myCallback = null;
        }
    }
}
