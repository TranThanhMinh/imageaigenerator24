package com.demo.imageaigenerator24.model;

/* loaded from: classes.dex */
public class TimePlan {
    private String price;
    private String productId;
    private String title;
    private String tvdays;

    public TimePlan(String str, String str2, String str3, String str4) {
        this.title = str;
        this.price = str2;
        this.productId = str3;
        this.tvdays = str4;
    }

    public String getTvdays() {
        return this.tvdays;
    }

    public void setTvdays(String str) {
        this.tvdays = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }
}
