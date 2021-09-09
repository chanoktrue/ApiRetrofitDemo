package com.trueddns.homenano.apiretrofitdemo.Viewmodels

import com.trueddns.homenano.apiretrofitdemo.Models.Album
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

val baseUrl = "https://jsonplaceholder.typicode.com/"

interface ApiService {
    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("albums/{no}")
    fun getAlbum(@Path("no") no: Int): Call<Album>

    @GET("albums/")
    fun getFromUserId(@Query("usesrId") userId: Int): Call<List<Album>>

    @GET("albums/")
    fun getFromUserIdByMap(@QueryMap map: HashMap<String, String>): Call<List<Album>>

    companion object {

//        fun retrofitBuild(): ApiService {
//            return Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ApiService::class.java)
//        }

        operator fun invoke(): ApiService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}