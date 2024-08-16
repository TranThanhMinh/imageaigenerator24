package com.demo.imageaigenerator24.homeactivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.fragments.DownloadFragment;
import com.demo.imageaigenerator24.fragments.GeneralCategoryImage2ImageFragment;
import com.demo.imageaigenerator24.fragments.HomeNewFragment;
import com.demo.imageaigenerator24.fragments.InspirationFragment;
import com.demo.imageaigenerator24.fragments.SaveAndShareWatermarkFragment;
import com.demo.imageaigenerator24.fragments.SplashFragment;
import com.demo.imageaigenerator24.fragments.TrendingFragment;
import com.demo.imageaigenerator24.practicetwo.EditImageFragment;
import com.demo.imageaigenerator24.practicetwo.Image2ImageFragment;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.DexterError;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private boolean isLanguageSet;
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new HomeActivityBottomNavigationView(this);
    private SharedPrefsHelper prefs;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);
        chkPermission();
        SharedPrefsHelper sharedPrefsHelper = SharedPrefsHelper.getInstance(this);
        this.prefs = sharedPrefsHelper;
        if (sharedPrefsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        this.bottomNavigationView = bottomNavigationView;
        if (bottomNavigationView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomNavigationView");
            bottomNavigationView = null;
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(this.navListener);
        Log.d("checkiyty", "onCreateView: HomeActivity");
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SplashFragment()).commit();
        }
    }

    public static boolean HomeActivityBottomNavigationViewCall(HomeActivity this$0, MenuItem item) {
        Fragment editImageFragment;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            editImageFragment = new HomeNewFragment();
        } else {
            editImageFragment = itemId == R.id.nav_feature ? new EditImageFragment() : null;
        }
        FragmentTransaction beginTransaction = this$0.getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNull(editImageFragment);
        beginTransaction.replace(R.id.fragment_container, editImageFragment).commit();
        return true;
    }

    public void hideBottomNavigation() {
        BottomNavigationView bottomNavigationView = this.bottomNavigationView;
        if (bottomNavigationView == null) {
            bottomNavigationView = null;
        }
        bottomNavigationView.setVisibility(8);
    }

    public void showBottomNavigation() {
        BottomNavigationView bottomNavigationView = this.bottomNavigationView;
        if (bottomNavigationView == null) {
            bottomNavigationView = null;
        }
        bottomNavigationView.setVisibility(0);
    }

    private void chkPermission() {
        Dexter.withContext(this).withPermissions("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").withListener(new HomeActivityMultiplePermissionsListener()).withErrorListener(new HomeActivityPermissionRequestErrorListener()).onSameThread().check();
    }

    public static void HomeActivityPermissionRequestErrorListenerCall(DexterError dexterError) {
        Log.d("taggydexter", "onError: " + dexterError.name());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (findFragmentById instanceof HomeNewFragment) {
            ((HomeNewFragment) findFragmentById).handleBackPress();
            return;
        }
        if (findFragmentById instanceof EditImageFragment) {
            ((EditImageFragment) findFragmentById).handleBackPress();
            return;
        }
        if (findFragmentById instanceof InspirationFragment) {
            ((InspirationFragment) findFragmentById).handleBackPress();
            return;
        }
        if (findFragmentById instanceof TrendingFragment) {
            ((TrendingFragment) findFragmentById).handleBackPress();
            return;
        }
        if (findFragmentById instanceof GeneralCategoryImage2ImageFragment) {
            ((GeneralCategoryImage2ImageFragment) findFragmentById).handleBackPress();
            return;
        }
        if (findFragmentById instanceof DownloadFragment) {
            ((DownloadFragment) findFragmentById).handleBackPress();
            return;
        }
        if (findFragmentById instanceof SaveAndShareWatermarkFragment) {
            ((SaveAndShareWatermarkFragment) findFragmentById).handleBackPress();
        } else if (findFragmentById instanceof Image2ImageFragment) {
            ((Image2ImageFragment) findFragmentById).handleBackPress();
        } else {
            super.onBackPressed();
        }
    }
}
