package com.mtu.ceit.hhk.imagesearch.api

import com.mtu.ceit.hhk.imagesearch.utils.Util.Companion.ACCESS_KEY
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {


    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("search/photos")
    suspend fun searchPhoto(
        @Query("query") query:String,
        @Query("page") page:Int,
        @Query("per_page") perPage:Int
    ):UnsplashResponse
}