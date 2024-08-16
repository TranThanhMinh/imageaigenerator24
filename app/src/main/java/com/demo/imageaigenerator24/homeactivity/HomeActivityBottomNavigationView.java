package com.demo.imageaigenerator24.homeactivity;

import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/* loaded from: classes.dex */
public final class HomeActivityBottomNavigationView implements BottomNavigationView.OnNavigationItemSelectedListener {
    public final HomeActivity homeAct1;

    public HomeActivityBottomNavigationView(HomeActivity homeActivity) {
        this.homeAct1 = homeActivity;
    }

    @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return HomeActivity.HomeActivityBottomNavigationViewCall(this.homeAct1, menuItem);
    }
}
