package com.mocha.retrofitdemo.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mocha.retrofitdemo.Api
import com.mocha.retrofitdemo.entity.WeatherInfo
import com.mocha.retrofitdemo.net.RetrofitHelper
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RetrofitViewModel:ViewModel() {

    private val disposables:CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun addDisposable(d:Disposable){
        disposables.add(d)
    }

    val weatherLiveData = MutableLiveData<WeatherInfo>()

    fun getWeather(){
        RetrofitHelper.getRetrofit().create(Api::class.java).getWeather("101010100")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Observer<WeatherInfo>{
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    addDisposable(d)
                }

                override fun onNext(t: WeatherInfo) {
                    weatherLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}