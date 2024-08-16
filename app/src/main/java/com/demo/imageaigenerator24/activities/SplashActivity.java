package com.demo.imageaigenerator24.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.demo.imageaigenerator24.utils.ConnectivityChecker;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class SplashActivity extends AppCompatActivity {
    ConstraintLayout btnStart;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Intent intenttohome;
    SharedPrefsHelper prefs;
    public ProgressBar splashProgress;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        setContentView(R.layout.activity_splash_app);
        this.prefs = SharedPrefsHelper.getInstance(this);
        this.btnStart = (ConstraintLayout) findViewById(R.id.btnStart);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.splashProgress);
        this.splashProgress = progressBar;
        progressBar.animate();
        this.btnStart.setVisibility(View.VISIBLE);
        this.btnStart.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.activities.SplashActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ConnectivityChecker.isNetworkAvailable(SplashActivity.this)) {
                    SplashActivity.this.startAcctivity();
                } else {
                    Toast.makeText(SplashActivity.this, "Please turn on the internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void playProgress() {
        final ValueAnimator ofInt = ValueAnimator.ofInt(0, 100);
        ofInt.setDuration(8000L);
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.demo.imageaigenerator24.activities.SplashActivity.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SplashActivity.this.splashProgress.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.demo.imageaigenerator24.activities.SplashActivity.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                SplashActivity.this.splashProgress.setProgress(0);
                ofInt.start();
            }
        });
        ofInt.start();
    }

    public void startAcctivity() {
        startFragment();
    }

    public void startFragment() {
        SharedPrefsHelper.getInstance(this);
        Log.d("checkiyty", "activity called: true");
        startActivity(new Intent(this, (Class<?>) HomeActivity.class));
        finish();
    }
}
