package com.demo.imageaigenerator24.practicetwo;

/* loaded from: classes.dex */
public class PhotorealismModel {
    private String imageName;
    private boolean isLocked;
    private String modelName;
    private String text;

    public PhotorealismModel(String str, String str2) {
        this.imageName = str;
        this.text = str2;
    }

    public PhotorealismModel(String str, String str2, String str3, boolean z) {
        this.imageName = str;
        this.text = str2;
        this.modelName = str3;
        this.isLocked = z;
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    public void setLocked(boolean z) {
        this.isLocked = z;
    }

    public void setImageName(String str) {
        this.imageName = str;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String str) {
        this.modelName = str;
    }

    public String getImageName() {
        return this.imageName;
    }

    public String getText() {
        return this.text;
    }
}
