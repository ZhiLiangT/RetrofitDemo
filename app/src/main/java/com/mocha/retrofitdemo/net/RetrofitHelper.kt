package com.mocha.retrofitdemo.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    private const val BASE_URL = "https://www.baidu.com"

    private var retrofit: Retrofit? = null

    private var retrofitBuilder: Retrofit.Builder? = null

    fun init(){
        if (retrofitBuilder == null) {
            val client = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
            retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
        }
        retrofit = retrofitBuilder!!.build()
    }

    fun getRetrofit():Retrofit{
        if (retrofit == null) {
            throw IllegalAccessException("Retrofit is not initialized!")
        }
        return retrofit!!
    }

}