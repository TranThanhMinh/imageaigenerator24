package com.demo.imageaigenerator24.practicetwo;

import android.util.Log;

/* loaded from: classes.dex */
public class CollectedData {
    private String aspectRatio;
    private float guidance;
    private int height;
    private String modelAndStyle;
    private String negativePrompt;
    private String negativeStyle;
    private String outputFormat;
    private String prompt;
    private String sampler;
    private String scheduler;
    private int seed;
    private int steps;
    private int width;

    public CollectedData(String str, String str2, int i, int i2, String str3, String str4, int i3, int i4, int i5, String str5, String str6, String str7, String str8) {
        this.prompt = str;
        this.negativePrompt = str2;
        this.width = i;
        this.height = i2;
        this.scheduler = str3;
        this.outputFormat = str4;
        this.steps = i3;
        this.seed = i4;
        this.guidance = i5;
        this.modelAndStyle = str5;
        this.negativeStyle = str6;
        this.sampler = str7;
        this.aspectRatio = str8;
    }

    public CollectedData() {
        this.negativePrompt = "Disfigured, cartoon, blurry, nude, low quality, worst quality, ugly, bad anatomy, blurry,bad-picture-chill-75v, realisticvision-negative-embedding";
        this.width = 512;
        this.height = 512;
        this.scheduler = "ddim";
        this.steps = 30;
        this.seed = 2147483;
        this.guidance = 12.0f;
        this.modelAndStyle = "absolute-reality-v1-8-1";
        this.negativeStyle = "nude";
        this.sampler = "ddim";
    }

    public void setPrompt(String str) {
        Log.d("Loggy", "Setting prompt: " + str);
        this.prompt = str;
    }

    public void setNegativePrompt(String str) {
        Log.d("Loggy", "Setting negativePrompt: " + str);
        this.negativePrompt = str;
    }

    public void setWidth(int i) {
        Log.d("Loggy", "Setting width: " + i);
        this.width = i;
    }

    public void setHeight(int i) {
        Log.d("Loggy", "Setting height: " + i);
        this.height = i;
    }

    public void setScheduler(String str) {
        Log.d("Loggy", "Setting scheduler: " + str);
        this.scheduler = str;
    }

    public void setOutputFormat(String str) {
        Log.d("Loggy", "Setting outputFormat: " + str);
        this.outputFormat = str;
    }

    public void setSteps(int i) {
        Log.d("Loggy", "Setting steps: " + i);
        this.steps = i;
    }

    public void setSeed(int i) {
        Log.d("Loggy", "Setting seed: " + i);
        this.seed = i;
    }

    public void setModelAndStyle(String str) {
        Log.d("Loggy", "Setting modelAndStyle: " + str);
        this.modelAndStyle = str;
    }

    public void setNegativeStyle(String str) {
        Log.d("Loggy", "Setting negativeStyle: " + str);
        this.negativeStyle = str;
    }

    public void setSampler(String str) {
        Log.d("Loggy", "Setting sampler: " + str);
        this.sampler = str;
    }

    public void setAspectRatio(String str) {
        Log.d("Loggy", "Setting aspectRatio: " + str);
        this.aspectRatio = str;
    }

    public String getPrompt() {
        Log.d("Loggy", "Getting prompt: " + this.prompt);
        return this.prompt;
    }

    public void appendToPrompt(String str) {
        if (this.prompt == null) {
            this.prompt = "";
        }
        this.prompt += " " + str;
    }

    public String getNegativePrompt() {
        Log.d("Loggy", "Getting negativePrompt: " + this.negativePrompt);
        return this.negativePrompt;
    }

    public int getWidth() {
        Log.d("Loggy", "Getting width: " + this.width);
        return this.width;
    }

    public int getHeight() {
        Log.d("Loggy", "Getting height: " + this.height);
        return this.height;
    }

    public String getScheduler() {
        Log.d("Loggy", "Getting scheduler: " + this.scheduler);
        return this.scheduler;
    }

    public String getOutputFormat() {
        Log.d("Loggy", "Getting outputFormat: " + this.outputFormat);
        return this.outputFormat;
    }

    public int getSteps() {
        Log.d("Loggy", "Getting steps: " + this.steps);
        return this.steps;
    }

    public int getSeed() {
        Log.d("Loggy", "Getting seed: " + this.seed);
        return this.seed;
    }

    public void setGuidance(float f) {
        this.guidance = f;
    }

    public float getGuidance() {
        return this.guidance;
    }

    public String getModelAndStyle() {
        Log.d("Loggy", "Getting modelAndStyle: " + this.modelAndStyle);
        return this.modelAndStyle;
    }

    public String getNegativeStyle() {
        Log.d("Loggy", "Getting negativeStyle: " + this.negativeStyle);
        return this.negativeStyle;
    }

    public String getSampler() {
        Log.d("Loggy", "Getting sampler: " + this.sampler);
        return this.sampler;
    }

    public String getAspectRatio() {
        Log.d("Loggy", "Getting aspectRatio: " + this.aspectRatio);
        return this.aspectRatio;
    }
}
