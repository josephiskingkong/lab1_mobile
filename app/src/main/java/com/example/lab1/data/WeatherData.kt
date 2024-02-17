package com.example.lab1

data class WeatherData(
    val name: String,
    val main: Main,
    val weather: List<Weather>
) {
    data class Main(val temp: Double)
    data class Weather(val description: String, val icon: String)
}
