package kr.co.dantalk.codingTest.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kr.co.dantalk.codingTest.api.response.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CallApi {
    private val gson: Gson? = GsonBuilder().setLenient().create()
    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://us-central1-findpcroom-f0e38.cloudfunctions.net/")
        .addConverterFactory(GsonConverterFactory.create(gson!!))
        .client(client)
        .build()

    fun signIn(
        id:String, pw:String,
        callback: (Int, String, String?) -> Unit
    ) {
        retrofit.create(Api::class.java).signin(id, pw)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    callback(response.body()?.code?:500, response.body()?.message.toString(), response.body()?.data)
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    callback(500, t.message.toString(), null)
                }
            })
    }

    fun signUp(
        id:String, pw:String,
        callback: (Int, String, String?) -> Unit
    ) {
        retrofit.create(Api::class.java).signup(id, pw)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    callback(response.body()?.code?:500, response.body()?.message.toString(), response.body()?.data)
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    callback(500, t.message.toString(), null)
                }
            })
    }

    fun album(
        callback: (Int, String, List<AlbumData>?) -> Unit
    ) {
        retrofit.create(Api::class.java).album()
            .enqueue(object : Callback<AlbumResponse> {
                override fun onResponse(
                    call: Call<AlbumResponse>,
                    response: Response<AlbumResponse>
                ) {
                    callback(response.body()?.code?:500, response.body()?.message.toString(), response.body()?.data)
                }

                override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                    callback(500, t.message.toString(), null)
                }
            })
    }

    fun albumItem(
        id:String,
        callback: (Int, String, AlbumDetailData?) -> Unit
    ) {
        retrofit.create(Api::class.java).albumItem(id)
            .enqueue(object : Callback<AlbumItemResponse> {
                override fun onResponse(
                    call: Call<AlbumItemResponse>,
                    response: Response<AlbumItemResponse>
                ) {
                    callback(response.body()?.code?:500, response.body()?.message.toString(), response.body()?.data)
                }

                override fun onFailure(call: Call<AlbumItemResponse>, t: Throwable) {
                    callback(500, t.message.toString(), null)
                }
            })
    }
}