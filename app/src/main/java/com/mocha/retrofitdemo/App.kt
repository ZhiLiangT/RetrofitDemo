package com.mocha.retrofitdemo

import android.app.Application
import com.mocha.retrofitdemo.net.RetrofitHelper

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitHelper.init()
    }
}