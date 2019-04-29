package com.example.barcodescanner.http.connector;

import com.example.barcodescanner.request.RequestBody;
import com.example.barcodescanner.response.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {
//    @FormUrlEncoded
    @POST("/lesson/findByFilter/")
     Call<List<Post>> getPostWithValues(@Body RequestBody requestBody);
}
