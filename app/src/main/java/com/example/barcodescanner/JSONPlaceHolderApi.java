package com.example.barcodescanner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {
//    @FormUrlEncoded
    @POST("/lesson/findByFilter/")
     Call<List<Post>> getPostWithValues(@Body RequestBody requestBody);
}
