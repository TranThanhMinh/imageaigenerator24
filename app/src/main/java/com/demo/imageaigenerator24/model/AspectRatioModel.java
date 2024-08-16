package com.demo.imageaigenerator24.model;

/* loaded from: classes.dex */
public class AspectRatioModel {
    private boolean isSelected = false;
    private String ratio;

    public AspectRatioModel(String str) {
        this.ratio = str;
    }

    public String getRatio() {
        return this.ratio;
    }

    public void setRatio(String str) {
        this.ratio = str;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
