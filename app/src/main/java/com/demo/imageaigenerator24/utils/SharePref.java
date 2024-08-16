package com.demo.imageaigenerator24.utils;

import android.content.SharedPreferences;
import android.util.Log;
import com.demo.imageaigenerator24.ads.MyApplication;

/* loaded from: classes.dex */
public class SharePref {
    public static String Locale_KeyValue = "SavedLocale";
    public static String countryCodeKey = "countryCode";
    private static SharedPreferences pref;

    private static void initPref() {
        pref = MyApplication.getAppContext().getSharedPreferences("Background_Video_Maker", 0);
    }

    public static void putString(String str, String str2) {
        if (pref == null) {
            initPref();
        }
        pref.edit().putString(str, str2).apply();
    }

    public static void putBoolean(String str, Boolean bool) {
        if (pref == null) {
            initPref();
        }
        pref.edit().putBoolean(str, bool.booleanValue()).apply();
    }

    public static void putInt(String str, Integer num) {
        if (pref == null) {
            initPref();
        }
        pref.edit().putInt(str, num.intValue()).apply();
    }

    public static void putLong(String str, Long l) {
        if (pref == null) {
            initPref();
        }
        pref.edit().putLong(str, l.longValue()).apply();
    }

    public static String getString(String str, String str2) {
        if (pref == null) {
            initPref();
        }
        return pref.getString(str, str2);
    }

    public static Boolean getBoolean(String str, Boolean bool) {
        if (pref == null) {
            initPref();
        }
        return Boolean.valueOf(pref.getBoolean(str, bool.booleanValue()));
    }

    public static Integer getInt(String str, Integer num) {
        if (pref == null) {
            initPref();
        }
        return Integer.valueOf(pref.getInt(str, num.intValue()));
    }

    public static Long getLong(String str, Long l) {
        if (pref == null) {
            initPref();
        }
        return Long.valueOf(pref.getLong(str, l.longValue()));
    }

    public static void remove(String str) {
        if (pref == null) {
            initPref();
        }
        pref.edit().remove(str).apply();
        Log.d("HuHu", "remove:");
    }

    public static String getSelectedLanguage() {
        if (pref == null) {
            initPref();
        }
        return pref.getString(Locale_KeyValue, "en");
    }

    public static String getSelectedCountryCode() {
        if (pref == null) {
            initPref();
        }
        return pref.getString(countryCodeKey, "en");
    }
}
