package com.demo.imageaigenerator24.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/* loaded from: classes.dex */
public class TextViewModel extends ViewModel {
    private final MutableLiveData<String> textLiveData = new MutableLiveData<>();

    public void setText(String str) {
        this.textLiveData.setValue(str);
    }

    public LiveData<String> getText() {
        return this.textLiveData;
    }
}
