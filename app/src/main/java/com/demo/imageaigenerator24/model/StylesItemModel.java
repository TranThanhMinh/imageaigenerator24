package com.demo.imageaigenerator24.model;

/* loaded from: classes.dex */
public class StylesItemModel {
    private String imageFileName;
    private boolean isSelected;
    private String text;

    public StylesItemModel(String str, String str2) {
        this.imageFileName = str;
        this.text = str2;
    }

    public String getImageFileName() {
        return this.imageFileName;
    }

    public String getText() {
        return this.text;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
