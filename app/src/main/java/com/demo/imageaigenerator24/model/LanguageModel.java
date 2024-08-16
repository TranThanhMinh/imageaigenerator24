package com.demo.imageaigenerator24.model;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes.dex */
public final class LanguageModel {
    private Boolean check;
    private String code;
    private String countryCode;
    private String language;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LanguageModel() {
        this(null, null, null, null, 15, null);
    }

    public LanguageModel(String str, String str2, String str3, Boolean bool) {
        this.language = str;
        this.code = str2;
        this.countryCode = str3;
        this.check = bool;
    }

    public LanguageModel(String str, String str2, String str3, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : bool);
    }

    public final String getLanguage() {
        return this.language;
    }

    public final void setLanguage(String str) {
        this.language = str;
    }

    public final String getCode() {
        return this.code;
    }

    public final void setCode(String str) {
        this.code = str;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final void setCountryCode(String str) {
        this.countryCode = str;
    }

    public final Boolean getCheck() {
        return this.check;
    }

    public final void setCheck(Boolean bool) {
        this.check = bool;
    }
}
