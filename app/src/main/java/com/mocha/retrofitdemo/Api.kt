package com.mocha.retrofitdemo

import com.mocha.retrofitdemo.entity.WeatherInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("http://www.weather.com.cn/data/sk/{cityCode}.html")
    fun getWeather(@Path("cityCode")code:String):Observable<WeatherInfo>
}