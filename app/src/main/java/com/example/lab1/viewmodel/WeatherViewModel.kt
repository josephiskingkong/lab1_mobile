package com.example.lab1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab1.WeatherData
import com.example.lab1.data.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel() {
    val weatherData = MutableLiveData<WeatherData>()
    private val weatherService: WeatherService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherService = retrofit.create(WeatherService::class.java)
    }

    fun fetchWeather(cityName: String, apiKey: String) {
        val call = weatherService.getWeatherData(cityName, apiKey)
        call.enqueue(object : retrofit2.Callback<WeatherData> {
            override fun onResponse(call: retrofit2.Call<WeatherData>, response: retrofit2.Response<WeatherData>) {
                if (response.isSuccessful) {
                    weatherData.value = response.body()
                }
            }

            override fun onFailure(call: retrofit2.Call<WeatherData>, t: Throwable) {
                // TODO: ошибка
            }
        })
    }
}