package com.smartherd.retrofitjosn

import com.smartherd.retrofitjosn.data.placeholderItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface MyApi {

    @GET("posts")
    suspend fun getMessages(): Response<List<placeholderItem>>

    @GET("posts/{userId}")
    suspend fun getMessagesId(@Path("userId") postId: Int) : Response<List<placeholderItem>>


    @FormUrlEncoded()
    @POST("posts")
    suspend fun getPostsUrlEncoded(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("userId") userId: Int
    ): Response<placeholderItem>

    @POST("posts")
    suspend fun getPostBody(
        @Body holder: placeholderItem
    ): Response<placeholderItem>


    @FormUrlEncoded
    @PUT("posts/{userId}")
    suspend fun getPut(
        @Path("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Response<placeholderItem>


    @FormUrlEncoded
    @PUT("posts/{userId}")
    suspend fun getPatch(
        @Path("userId") userId: Int,
        @Field("title") title: String
    ): Response<placeholderItem>


    @DELETE("posts/{userId}")
    suspend fun getDelete(
        @Path("userId") userId: Int
    ): Response<placeholderItem>



//    @GET()
//    suspend fun getMessagesPost(@QueryMap filter: HashMap<String,String>)

    companion object{

        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        var httpClient = OkHttpClient.Builder()
            .connectTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .addInterceptor(logger)


        operator fun invoke(): MyApi{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
                .create(MyApi::class.java)
        }

    }

}