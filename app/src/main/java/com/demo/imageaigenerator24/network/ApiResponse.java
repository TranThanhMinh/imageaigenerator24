package com.demo.imageaigenerator24.network;

/* loaded from: classes.dex */
public class ApiResponse {
    private double cost;
    private String image;
    private int seed;

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public int getSeed() {
        return this.seed;
    }

    public void setSeed(int i) {
        this.seed = i;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double d) {
        this.cost = d;
    }
}
