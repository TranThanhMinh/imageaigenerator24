package com.demo.imageaigenerator24.practicetwo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/* loaded from: classes.dex */
public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> base64ImageLiveData = new MutableLiveData<>();

    public void setBase64Image(String str) {
        this.base64ImageLiveData.setValue(str);
    }

    public LiveData<String> getBase64Image() {
        return this.base64ImageLiveData;
    }
}
