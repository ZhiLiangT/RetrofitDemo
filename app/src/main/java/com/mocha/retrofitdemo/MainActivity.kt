package com.mocha.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mocha.retrofitdemo.util.JsonUtil
import com.mocha.retrofitdemo.vm.RetrofitViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<RetrofitViewModel>()

    private var btnWeather: Button? = null

    private var tvWeather: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnWeather = findViewById(R.id.btnWeather)
        tvWeather = findViewById(R.id.tvWeather)
        viewModel.weatherLiveData.observe(this, Observer {
            tvWeather?.text = it.toString()
        })
        btnWeather?.setOnClickListener {
            viewModel.getWeather()
        }
    }
}