package com.demo.imageaigenerator24.homeactivity;

import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequestErrorListener;

/* loaded from: classes.dex */
public final class HomeActivityPermissionRequestErrorListener implements PermissionRequestErrorListener {
    @Override // com.karumi.dexter.listener.PermissionRequestErrorListener
    public void onError(DexterError dexterError) {
        HomeActivity.HomeActivityPermissionRequestErrorListenerCall(dexterError);
    }
}
