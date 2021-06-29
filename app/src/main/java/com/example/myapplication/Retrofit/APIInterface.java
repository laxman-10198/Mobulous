package com.example.myapplication.Retrofit;

import com.example.myapplication.Model.Zemato.ZematoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers("user-key: 41a5401d2f4bb7aba885fe8d6d1f3d69")
    @GET("api/v2.1/search")
    Call<ZematoResponse> getAllSearch(
            @Query("q") String query
    );

}
