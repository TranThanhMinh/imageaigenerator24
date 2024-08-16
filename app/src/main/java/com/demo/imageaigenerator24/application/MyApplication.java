package com.demo.imageaigenerator24.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/* loaded from: classes.dex */
public class MyApplication extends Application {
    private static MyApplication application;
    public Context mContext;
    SharedPrefsHelper prefs;

    public static Context getAppContext() {
        return application.getApplicationContext();
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        application = this;
        this.prefs = SharedPrefsHelper.getInstance(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() { // from class: com.demo.imageaigenerator24.application.MyApplication.1
            @Override // com.google.android.gms.ads.initialization.OnInitializationCompleteListener
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d("unity_testing", "onInitializationComplete: Admob");
            }
        });
        Log.d("MYAPPLICATIOOON", "onCreate: MyApplication");
    }
}
