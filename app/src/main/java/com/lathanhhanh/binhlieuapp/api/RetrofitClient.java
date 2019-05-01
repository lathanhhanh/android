package com.lathanhhanh.binhlieuapp.api;

import android.support.annotation.NonNull;

import com.lathanhhanh.binhlieuapp.model.Data;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class RetrofitClient {
    private static final String ROOT_URL = "http://zapi.000webhostapp.com/api/";

    @NonNull
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @NonNull
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }

    public interface ApiService {
        @GET("api.php")
        Call<Data> getData(@Query("tuyen") String tuyen);

        @FormUrlEncoded
        @POST("gopy.php")
        Call<Data> sendGopY(@Field("noidung") String noidung);
    }
}
