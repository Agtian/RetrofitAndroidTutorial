package com.test.retrofitandroidtutorial

import com.test.retrofitandroidtutorial.models.Users
import com.test.retrofitandroidtutorial.models.UsersItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiInterface {
    @GET("/posts")
    suspend fun getAllUsers():Response<Users>

    @GET("/posts/1")
    suspend fun getUser():Response<UsersItem>

    @POST("/posts")
    suspend fun createPost(
        @Body user: UsersItem
    ):Response<UsersItem>

    @FormUrlEncoded
    @POST("/posts")
    suspend fun createUrlPost(
        @Field("userId") userId : Int,
        @Field("title") title : String,
        @Field("body") body :String,
    ):Response<UsersItem>


    @PUT("posts/{id}")
    suspend fun putPost(
        @Path("id") id : Int,
        @Body user: UsersItem
    ):Response<UsersItem>

    @PATCH("posts/{id}")
    suspend fun patchPost(
        @Path("id") id : Int,
        @Body user: UsersItem
    ):Response<UsersItem>

    @DELETE("posts/{id}")
    suspend fun deletePost(
        @Path("id") id : Int
    ) : Response<UsersItem>
}