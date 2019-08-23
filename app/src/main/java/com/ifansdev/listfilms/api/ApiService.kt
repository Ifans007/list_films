package com.ifansdev.listfilms.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ifansdev.listfilms.api.dataclass.ListMovies
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("movie/popular")
    fun searchListFilms(
            @Query("api_key") apiKey: String,
            @Query("language") language: String) : io.reactivex.Observable<ListMovies>

    companion object Factory {

        private const val BASE_URL : String = "https://api.themoviedb.org/3/"

        fun create(): ApiService {
            val gson: Gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}