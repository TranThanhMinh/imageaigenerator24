package com.demo.imageaigenerator24.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDex;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.onesignal.OneSignal;
import java.util.Date;

/* loaded from: classes.dex */
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks, LifecycleObserver {
   // public static String AdMob_Banner1 = "ca-app-pub-3940256099942544/6300978111";
    public static String AdMob_Banner1 = "ca-app-pub-5751638294565515/3399617822";
    //public static String AdMob_Int1 = "ca-app-pub-3940256099942544/1033173712";
    public static String AdMob_Int1 = "ca-app-pub-5751638294565515/1190157389";
    //public static String AdMob_Int2 = "ca-app-pub-3940256099942544/1033173712";
    public static String AdMob_Int2 = "ca-app-pub-5751638294565515/1190157389";
    //public static String AdMob_NativeAdvance1 = "ca-app-pub-3940256099942544/2247696110";
    public static String AdMob_NativeAdvance1 = "ca-app-pub-5751638294565515/6406254227";
    //public static String AdMob_NativeAdvance2 = "ca-app-pub-3940256099942544/2247696110";
    public static String AdMob_NativeAdvance2 = "ca-app-pub-5751638294565515/6406254227";
    public static int AdsClickCount = 0;
    public static String App_Open = "ca-app-pub-5751638294565515/6797707281";
    //public static String App_Open = "ca-app-pub-3940256099942544/9257395921";
    public static String FbBanner = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String FbInter = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String FbNativeB = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String Fbnative = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String MAX_Banner = "";
    public static String MAX_Int = "";
    public static String MAX_Native = "";
    public static String MoreApps = "your_moreapp_account_name_here";
    private static final String ONESIGNAL_APP_ID = "";
    public static String PrivacyPolicy = "https://www.freeprivacypolicy.com/blog/privacy-policy-url/";
    public static String TermsandConditions = "https://www.freeprivacypolicy.com/blog/privacy-policy-url/";
    public static String Type1 = "admob";
    public static String Type2 = "fb";
    public static String Type3 = "";
    public static String Type4 = "";
    private static MyApplication application = null;
    public static int backAdsClickCount = 0;
    public static int backclick = 5;
    public static int checkInAppUpdate = 0;
    public static int click = 0;
    public static Context context1;
    public static SharedPreferences.Editor editorInApp;
    public static SharedPreferences sharedPreferencesInApp;
    private AppOpenAdManager appOpenAdManager;
    private Activity currentActivity;
    public Context mContext;
    SharedPrefsHelper prefs;

    /* loaded from: classes.dex */
    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        SharedPreferences sharedPreferences = getSharedPreferences("my", 0);
        sharedPreferencesInApp = sharedPreferences;
        editorInApp = sharedPreferences.edit();
        context1 = getApplicationContext();
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId("");

        MobileAds.initialize(this, new OnInitializationCompleteListener() { // from class: com.demo.imageaigenerator24.ads.MyApplication.1
            @Override // com.google.android.gms.ads.initialization.OnInitializationCompleteListener
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        this.appOpenAdManager = new AppOpenAdManager();
        this.mContext = this;
        application = this;
        this.prefs = SharedPrefsHelper.getInstance(this);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMoveToForeground() {
        this.appOpenAdManager.showAdIfAvailable(this.currentActivity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (this.appOpenAdManager.isShowingAd) {
            return;
        }
        this.currentActivity = activity;
    }

    public void showAdIfAvailable(Activity activity, OnShowAdCompleteListener onShowAdCompleteListener) {
        this.appOpenAdManager.showAdIfAvailable(activity, onShowAdCompleteListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class AppOpenAdManager {
        private static final String LOG_TAG = "AppOpenAdManager";
        private AppOpenAd appOpenAd = null;
        private boolean isLoadingAd = false;
        private boolean isShowingAd = false;
        private long loadTime = 0;

        public AppOpenAdManager() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void loadAd(Context context) {
            if (this.isLoadingAd || isAdAvailable() || MyApplication.getuser_balance().intValue() != 0 || MyApplication.App_Open.equals("")) {
                return;
            }
            this.isLoadingAd = true;
            AppOpenAd.load(context, MyApplication.App_Open, new AdRequest.Builder().build(), 1, new AppOpenAd.AppOpenAdLoadCallback() { // from class: com.demo.imageaigenerator24.ads.MyApplication.AppOpenAdManager.1
                @Override // com.google.android.gms.ads.AdLoadCallback
                public void onAdLoaded(AppOpenAd appOpenAd) {
                    AppOpenAdManager.this.appOpenAd = appOpenAd;
                    AppOpenAdManager.this.isLoadingAd = false;
                    AppOpenAdManager.this.loadTime = new Date().getTime();
                    Log.d(AppOpenAdManager.LOG_TAG, "onAdLoaded.");
                }

                @Override // com.google.android.gms.ads.AdLoadCallback
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    AppOpenAdManager.this.isLoadingAd = false;
                    Log.d(AppOpenAdManager.LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
                }
            });
        }

        private boolean wasLoadTimeLessThanNHoursAgo(long j) {
            return new Date().getTime() - this.loadTime < j * 3600000;
        }

        private boolean isAdAvailable() {
            return this.appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showAdIfAvailable(Activity activity) {
            showAdIfAvailable(activity, new OnShowAdCompleteListener() { // from class: com.demo.imageaigenerator24.ads.MyApplication.AppOpenAdManager.2
                @Override // com.demo.imageaigenerator24.ads.MyApplication.OnShowAdCompleteListener
                public void onShowAdComplete() {
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showAdIfAvailable(final Activity activity, final OnShowAdCompleteListener onShowAdCompleteListener) {
            if (this.isShowingAd) {
                Log.d(LOG_TAG, "The app open ad is already showing.");
                return;
            }
            if (!isAdAvailable()) {
                Log.d(LOG_TAG, "The app open ad is not ready yet.");
                onShowAdCompleteListener.onShowAdComplete();
                loadAd(activity);
            } else {
                Log.d(LOG_TAG, "Will show ad.");
                this.appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() { // from class: com.demo.imageaigenerator24.ads.MyApplication.AppOpenAdManager.3
                    @Override // com.google.android.gms.ads.FullScreenContentCallback
                    public void onAdDismissedFullScreenContent() {
                        AppOpenAdManager.this.appOpenAd = null;
                        AppOpenAdManager.this.isShowingAd = false;
                        Log.d(AppOpenAdManager.LOG_TAG, "onAdDismissedFullScreenContent.");
                        onShowAdCompleteListener.onShowAdComplete();
                        AppOpenAdManager.this.loadAd(activity);
                    }

                    @Override // com.google.android.gms.ads.FullScreenContentCallback
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        AppOpenAdManager.this.appOpenAd = null;
                        AppOpenAdManager.this.isShowingAd = false;
                        Log.d(AppOpenAdManager.LOG_TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());
                        onShowAdCompleteListener.onShowAdComplete();
                        AppOpenAdManager.this.loadAd(activity);
                    }

                    @Override // com.google.android.gms.ads.FullScreenContentCallback
                    public void onAdShowedFullScreenContent() {
                        Log.d(AppOpenAdManager.LOG_TAG, "onAdShowedFullScreenContent.");
                    }
                });
                this.isShowingAd = true;
                this.appOpenAd.show(activity);
            }
        }
    }

    public static void setuser_balance(Integer num) {
        editorInApp.putInt("user_balance", num.intValue()).commit();
    }

    public static Integer getuser_balance() {
        return Integer.valueOf(sharedPreferencesInApp.getInt("user_balance", 0));
    }

    public static void setuser_onetime(Integer num) {
        editorInApp.putInt("user_onetime", num.intValue()).commit();
    }

    public static Integer getuser_onetime() {
        return Integer.valueOf(sharedPreferencesInApp.getInt("user_onetime", 0));
    }

    public static void setuser_permission(Integer num) {
        editorInApp.putInt("user_permission", num.intValue()).commit();
    }

    public static Integer getuser_permission() {
        return Integer.valueOf(sharedPreferencesInApp.getInt("user_permission", 0));
    }

    public static void setuser_guideline(Integer num) {
        editorInApp.putInt("user_guideline", num.intValue()).commit();
    }

    public static Integer getuser_guideline() {
        return Integer.valueOf(sharedPreferencesInApp.getInt("user_guideline", 0));
    }

    public static Context getAppContext() {
        return application.getApplicationContext();
    }
}
