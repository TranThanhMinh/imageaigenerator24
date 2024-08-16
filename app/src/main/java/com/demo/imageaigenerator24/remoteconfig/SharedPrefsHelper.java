package com.demo.imageaigenerator24.remoteconfig;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class SharedPrefsHelper {
    private static final String PREF_NAME = "RemoteConfigPrefs";
    private static SharedPrefsHelper instance;
    private final SharedPreferences sharedPreferences;

    private SharedPrefsHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
    }

    public static synchronized SharedPrefsHelper getInstance(Context context) {
        SharedPrefsHelper sharedPrefsHelper;
        synchronized (SharedPrefsHelper.class) {
            synchronized (SharedPrefsHelper.class) {
                if (instance == null) {
                    instance = new SharedPrefsHelper(context);
                }
                sharedPrefsHelper = instance;
            }
            return sharedPrefsHelper;
        }
       // return sharedPrefsHelper;
    }
}
