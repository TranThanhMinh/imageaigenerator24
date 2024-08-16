package com.demo.imageaigenerator24.practicetwo;

import android.net.Uri;

/* loaded from: classes.dex */
public abstract class DataModel {

    /* loaded from: classes.dex */
    public static class AIAdvanceSettings extends DataModel {
    }

    /* loaded from: classes.dex */
    public static class AIGenerate extends DataModel {
    }

    /* loaded from: classes.dex */
    public static class AIModelAndStyles extends DataModel {
    }

    /* loaded from: classes.dex */
    public static class AIRatios extends DataModel {
    }

    /* loaded from: classes.dex */
    public static class Header extends DataModel {
        private int bgColor;
        private String title;

        public Header(int i, String str) {
            this.bgColor = i;
            this.title = str;
        }

        public int getBgColor() {
            return this.bgColor;
        }

        public String getTitle() {
            return this.title;
        }
    }

    /* loaded from: classes.dex */
    public static class AItitle extends DataModel {
        String textView;

        public AItitle(String str) {
            this.textView = str;
        }

        public String getTextView() {
            return this.textView;
        }

        public void setTextView(String str) {
            this.textView = str;
        }
    }

    /* loaded from: classes.dex */
    public static class AIEditText extends DataModel {
        private int drawableResourceId;

        public AIEditText(int i) {
            this.drawableResourceId = i;
        }

        public int getDrawableResourceId() {
            return this.drawableResourceId;
        }

        public void setDrawableResourceId(int i) {
            this.drawableResourceId = i;
        }
    }

    /* loaded from: classes.dex */
    public static class AISamplers extends DataModel {
        private String assetFileName;
        private String text;

        public AISamplers(String str, String str2) {
            this.text = str;
            this.assetFileName = str2;
        }

        public String getText() {
            return this.text;
        }

        public String getAssetFileName() {
            return this.assetFileName;
        }

        public AISamplers() {
        }
    }

    /* loaded from: classes.dex */
    public static class AIImage extends DataModel {
        private String assetFileName;
        private String text;

        public AIImage(String str, String str2) {
            this.text = str;
            this.assetFileName = str2;
        }

        public String getText() {
            return this.text;
        }

        public String getAssetFileName() {
            return this.assetFileName;
        }
    }

    /* loaded from: classes.dex */
    public static class AIImage2Imgae extends DataModel {
        private Uri imageUri;

        public AIImage2Imgae(Uri uri) {
            this.imageUri = uri;
        }

        public Uri getImageUri() {
            return this.imageUri;
        }

        public void setImageUri(Uri uri) {
            this.imageUri = uri;
        }
    }

    /* loaded from: classes.dex */
    public static class Family extends DataModel {
        private String currentText = "";
        private String name;
        private String relationship;

        public String getCurrentText() {
            return this.currentText;
        }

        public void setCurrentText(String str) {
            this.currentText = str;
        }

        public Family(String str, String str2) {
            this.name = str;
            this.relationship = str2;
        }

        public String getName() {
            return this.name;
        }

        public String getRelationship() {
            return this.relationship;
        }
    }

    /* loaded from: classes.dex */
    public static class Friend extends DataModel {
        private String gender;
        private String name;

        public Friend(String str, String str2) {
            this.name = str;
            this.gender = str2;
        }

        public String getName() {
            return this.name;
        }

        public String getGender() {
            return this.gender;
        }
    }

    /* loaded from: classes.dex */
    public static class Colleague extends DataModel {
        private String designation;
        private String name;
        private String organization;

        public Colleague(String str, String str2, String str3) {
            this.name = str;
            this.organization = str2;
            this.designation = str3;
        }

        public String getName() {
            return this.name;
        }

        public String getOrganization() {
            return this.organization;
        }

        public String getDesignation() {
            return this.designation;
        }
    }
}
