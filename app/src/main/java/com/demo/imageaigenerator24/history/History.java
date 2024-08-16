package com.demo.imageaigenerator24.history;

import java.util.Objects;

/* loaded from: classes.dex */
public class History {
    private String date;
    private String text;

    public History(String str, String str2) {
        this.text = str;
        this.date = str2;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            History history = (History) obj;
            if (Objects.equals(this.text, history.text) && Objects.equals(this.date, history.date)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.text, this.date);
    }
}
