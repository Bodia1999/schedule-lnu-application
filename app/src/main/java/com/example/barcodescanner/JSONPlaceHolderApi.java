package com.example.barcodescanner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {
//    @FormUrlEncoded
    @POST("/lesson/findByFilter/")
     Call<List<Post>> getPostWithValues(@Body RequestBody requestBody);
}
