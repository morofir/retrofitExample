package com.example.retrofitexample;



import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {
    @GET("posts") //https://jsonplaceholder.typicode.com/posts -> the last changing part
//    Call<List<Post>> getPosts(@Query("userId")int userId);  //posts?userId=432 ->   ?   is a query

    Call<List<Post>> getPosts(@Query("userId")Integer[] userId,
                              @Query("sort")String sort,
                              @Query("order")String order);  //example https://jsonplaceholder.typicode.com/posts?userId=5&sort=id&order=desc



    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String,String> parameters);


    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComments(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post); //create body by ourself


    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId")int userId,
            @Field("title")String title,
            @Field("body")String text
    ); // other way replaces space with %20


    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String,String> fields); //cant pass a list




}
