package com.example.lab1.data

import com.example.lab1.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather")
    fun getWeatherData(@Query("q") cityName: String, @Query("appid") apiKey: String, @Query("units") units: String = "metric", @Query("lang") lang: String = "ru"): Call<WeatherData>
}