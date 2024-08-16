package com.demo.imageaigenerator24.practicetwo;

/* loaded from: classes.dex */
public interface SpeechToTextListener {
    void onSpeechResult(String str);

    void requestSpeechToTextPermission();

    void startSpeechToText();
}
