package com.demo.imageaigenerator24.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.activities.HomeActivity;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class SplashFragment extends Fragment {
    ConstraintLayout btnStart;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Intent intenttohome;
    SharedPrefsHelper prefs;
    public ProgressBar splashProgress;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_splash, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getActivity() != null && (getActivity() instanceof HomeActivity)) {
            ((HomeActivity) getActivity()).hideBottomNavigation();
        }
        this.prefs = SharedPrefsHelper.getInstance(getContext());
        this.btnStart = (ConstraintLayout) view.findViewById(R.id.btnStart);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.splashProgress);
        this.splashProgress = progressBar;
        progressBar.animate();
        this.btnStart.setVisibility(0);
        this.btnStart.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.SplashFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SplashFragment.this.startAcctivity();
            }
        });
    }

    private void playProgress() {
        final ValueAnimator ofInt = ValueAnimator.ofInt(0, 100);
        ofInt.setDuration(8000L);
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.demo.imageaigenerator24.fragments.SplashFragment.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SplashFragment.this.splashProgress.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.demo.imageaigenerator24.fragments.SplashFragment.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                SplashFragment.this.splashProgress.setProgress(0);
                ofInt.start();
            }
        });
        ofInt.start();
    }

    public void startAcctivity() {
        startFragment();
    }

    public void startFragment() {
        Log.d("checkiyty", "activity called: true");
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new HomeNewFragment());
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() == null || !(getActivity() instanceof HomeActivity)) {
            return;
        }
        ((HomeActivity) getActivity()).showBottomNavigation();
    }
}
