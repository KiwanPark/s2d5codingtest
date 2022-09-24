package kr.co.dantalk.codingTest.api

import kr.co.dantalk.codingTest.api.response.AlbumItemResponse
import kr.co.dantalk.codingTest.api.response.AlbumResponse
import kr.co.dantalk.codingTest.api.response.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/signin")
    fun signin(@Query("id") id: String, @Query("password") password: String): Call<BaseResponse>

    @GET("/signup")
    fun signup(@Query("id") id: String, @Query("password") password: String): Call<BaseResponse>

    @GET("/album")
    fun album(): Call<AlbumResponse>

    @GET("/albumItem")
    fun albumItem(@Query("id") id: String): Call<AlbumItemResponse>

    @GET("/getEncyc")
    fun encyc(@Query("id") id: String): Call<BaseResponse>
}