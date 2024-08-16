package com.demo.imageaigenerator24.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.fragments.DownloadFragment;
import com.demo.imageaigenerator24.fragments.GeneralCategoryImage2ImageFragment;
import com.demo.imageaigenerator24.fragments.HomeNewFragment;
import com.demo.imageaigenerator24.fragments.InspirationFragment;
import com.demo.imageaigenerator24.fragments.SaveAndShareWatermarkFragment;
import com.demo.imageaigenerator24.fragments.TrendingFragment;
import com.demo.imageaigenerator24.practicetwo.EditImageFragment;
import com.demo.imageaigenerator24.practicetwo.Image2ImageFragment;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.demo.imageaigenerator24.splashAds.GivePermission;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/* loaded from: classes.dex */
public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private boolean isLanguageSet = false;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() { // from class: com.demo.imageaigenerator24.activities.HomeActivity.1
        @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            Fragment editImageFragment;
            int itemId = menuItem.getItemId();
            if (itemId == R.id.nav_home) {
                editImageFragment = new HomeNewFragment();
            } else {
                editImageFragment = itemId == R.id.nav_feature ? new EditImageFragment() : null;
            }
            HomeActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, editImageFragment).commit();
            return true;
        }
    };
    SharedPrefsHelper prefs;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);
        GivePermission.applyPermission();
        AdsCommon.RegulerBanner(this, (RelativeLayout) findViewById(R.id.Admob_Banner_Frame), (LinearLayout) findViewById(R.id.banner_container), (FrameLayout) findViewById(R.id.qureka));
        this.prefs = SharedPrefsHelper.getInstance(this);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        this.bottomNavigationView = bottomNavigationView;
        bottomNavigationView.setOnNavigationItemSelectedListener(this.navListener);
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeNewFragment()).commit();
        }
    }

    public void hideBottomNavigation() {
        this.bottomNavigationView.setVisibility(View.GONE);
    }

    public void showBottomNavigation() {
        this.bottomNavigationView.setVisibility(View.VISIBLE);
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
