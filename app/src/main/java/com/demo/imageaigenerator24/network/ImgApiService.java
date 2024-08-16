package com.demo.imageaigenerator24.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* loaded from: classes.dex */
public interface ImgApiService {
    @Headers({"accept: application/json", "authorization: Bearer key-3X3e5LjVmMW2FbVgzOeHCUkMWWotBn1qPJES4QAOwdTVNH4U6yQSLSeReeJ1wGdOIWwlkexVxfmHygzFRvd1KrYZbqQKtjaF", "content-type: application/json"})
    @POST("stable-diffusion/text-to-image")
    Call<ApiResponse> createImage(@Body ImageRequestBody imageRequestBody);

    @Headers({"accept: application/json", "authorization: Bearer key-3X3e5LjVmMW2FbVgzOeHCUkMWWotBn1qPJES4QAOwdTVNH4U6yQSLSeReeJ1wGdOIWwlkexVxfmHygzFRvd1KrYZbqQKtjaF", "content-type: application/json"})
    @POST("stable-diffusion/image-to-image")
    Call<ApiResponse> createImageFromImage(@Body ImageFromImageRequestBody imageFromImageRequestBody);

    /* loaded from: classes.dex */
    public static class ImageRequestBody {
        public float guidance;
        public int height;
        public String model;
        public String negative_prompt;
        public String output_format;
        public String prompt;
        public String scheduler;
        public int seed;
        public int steps;
        public int width;

        public ImageRequestBody(String str, String str2, String str3, String str4, int i, int i2, String str5, int i3, float f, int i4) {
            this.model = str;
            this.prompt = str2;
            this.negative_prompt = str3;
            this.output_format = str4;
            this.height = i;
            this.width = i2;
            this.scheduler = str5;
            this.seed = i3;
            this.guidance = f;
            this.steps = i4;
        }
    }

    /* loaded from: classes.dex */
    public static class ImageFromImageRequestBody {
        public float guidance;
        public int height;
        String image;
        public String model;
        public String negative_prompt;
        public String output_format;
        public String prompt;
        public String scheduler;
        public int seed;
        public int steps;
        public int width;

        public ImageFromImageRequestBody(String str, String str2, String str3, String str4, int i, int i2, String str5, int i3, float f, int i4, String str6) {
            this.model = str;
            this.prompt = str2;
            this.negative_prompt = str3;
            this.output_format = str4;
            this.height = i;
            this.width = i2;
            this.scheduler = str5;
            this.seed = i3;
            this.guidance = f;
            this.steps = i4;
            this.image = str6;
        }
    }
}
