package com.demo.imageaigenerator24.model;

/* loaded from: classes.dex */
public class ItemModel {
    private String imageFileName;
    private boolean isSelected;
    private String model;
    private String text;

    public ItemModel(String str, String str2, String str3) {
        this.imageFileName = str;
        this.text = str2;
        this.model = str3;
    }

    public String getImageResId() {
        return this.imageFileName;
    }

    public void setImageResId(String str) {
        this.imageFileName = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
