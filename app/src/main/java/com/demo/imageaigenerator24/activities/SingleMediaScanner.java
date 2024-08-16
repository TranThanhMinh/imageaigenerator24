package com.demo.imageaigenerator24.activities;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import java.io.File;

/* loaded from: classes.dex */
public class SingleMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {
    private File mFile;
    private MediaScannerConnection mMs;

    public SingleMediaScanner(Context context, File file) {
        this.mFile = file;
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(context, this);
        this.mMs = mediaScannerConnection;
        mediaScannerConnection.connect();
    }

    @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
    public void onMediaScannerConnected() {
        this.mMs.scanFile(this.mFile.getAbsolutePath(), null);
    }

    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
    public void onScanCompleted(String str, Uri uri) {
        this.mMs.disconnect();
    }
}
