package com.demo.imageaigenerator24.splashAds;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import java.util.List;

/* loaded from: classes.dex */
public class GivePermission {
    public static void applyPermission() {
        if (Build.VERSION.SDK_INT >= 33) {
            TedPermission.create().setPermissions("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_MEDIA_IMAGES", "android.permission.CAMERA").setPermissionListener(new PermissionListener() { // from class: com.demo.imageaigenerator24.splashAds.GivePermission.1
                @Override // com.gun0912.tedpermission.PermissionListener
                public void onPermissionDenied(List<String> list) {
                }

                @Override // com.gun0912.tedpermission.PermissionListener
                public void onPermissionGranted() {
                }
            }).check();
        } else {
            TedPermission.create().setPermissions("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA").setPermissionListener(new PermissionListener() { // from class: com.demo.imageaigenerator24.splashAds.GivePermission.2
                @Override // com.gun0912.tedpermission.PermissionListener
                public void onPermissionDenied(List<String> list) {
                }

                @Override // com.gun0912.tedpermission.PermissionListener
                public void onPermissionGranted() {
                }
            }).check();
        }
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
