package com.demo.imageaigenerator24.model;

import java.util.Random;

/* loaded from: classes.dex */
public class PromptModel {
    private String category;
    private String[] prompts;

    public PromptModel(String str, String[] strArr) {
        this.category = str;
        this.prompts = strArr;
    }

    public String getRandomPrompt() {
        return this.prompts[new Random().nextInt(this.prompts.length)];
    }
}
