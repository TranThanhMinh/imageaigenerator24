package com.demo.imageaigenerator24.model;

import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public class InspirationItem {
    private Drawable imageResourceId;
    private String text;

    public InspirationItem(String str, Drawable drawable) {
        this.text = str;
        this.imageResourceId = drawable;
    }

    public String getText() {
        return this.text;
    }

    public Drawable getImageResourceId() {
        return this.imageResourceId;
    }
}
