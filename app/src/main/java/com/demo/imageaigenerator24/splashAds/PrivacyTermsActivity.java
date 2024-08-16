package com.demo.imageaigenerator24.splashAds;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.activities.HomeActivity;
import com.demo.imageaigenerator24.ads.MyApplication;

/* loaded from: classes.dex */
public class PrivacyTermsActivity extends AppCompatActivity {
    Button accept_button;
    Activity activity;
    CheckBox first_check;
    CheckBox second_check;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_privacy_terms);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(getResources().getColor(R.color.colorlight));
        }
        GivePermission.applyPermission();
        this.activity = this;
        this.first_check = (CheckBox) findViewById(R.id.first_check);
        this.second_check = (CheckBox) findViewById(R.id.second_check);
        Button button = (Button) findViewById(R.id.accept_button);
        this.accept_button = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.splashAds.PrivacyTermsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!PrivacyTermsActivity.this.first_check.isChecked() || !PrivacyTermsActivity.this.second_check.isChecked()) {
                    Toast.makeText(PrivacyTermsActivity.this.getApplicationContext(), "Check above options to continue", Toast.LENGTH_SHORT).show();
                    return;
                }
                MyApplication.setuser_onetime(1);
                PrivacyTermsActivity.this.startActivity(new Intent(PrivacyTermsActivity.this.activity, (Class<?>) HomeActivity.class));
                PrivacyTermsActivity.this.finish();
                PrivacyTermsActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            this.accept_button.setText("Get Started");
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
