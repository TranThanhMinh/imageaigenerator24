package com.demo.imageaigenerator24.model;

/* loaded from: classes.dex */
public class HistoryItem {
    private String dateTime;
    private String text;

    public HistoryItem(String str, String str2) {
        this.text = str;
        this.dateTime = str2;
    }

    public String getText() {
        return this.text;
    }

    public String getDateTime() {
        return this.dateTime;
    }
}
