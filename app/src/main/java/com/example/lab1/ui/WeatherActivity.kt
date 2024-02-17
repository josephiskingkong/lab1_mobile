package com.example.lab1.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lab1.R
import com.example.lab1.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso

class WeatherActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var cityNameEditText: EditText
    private lateinit var loadWeatherButton: Button
    private lateinit var weatherInfoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        cityNameEditText = findViewById(R.id.cityNameEditText)
        loadWeatherButton = findViewById(R.id.loadWeatherButton)
        weatherInfoTextView = findViewById(R.id.weatherInfoTextView)

        loadWeatherButton.setOnClickListener {
            val cityName = cityNameEditText.text.toString()
            if (cityName.isNotEmpty()) {
                viewModel.fetchWeather(cityName, "2d8d698a221ec7b953940e9087afb302")
            }
        }

        viewModel.weatherData.observe(this, Observer { weatherData ->
            weatherData?.let {
                val weatherText = "Город: ${it.name}\n" +
                        "Температура: ${it.main.temp}°C\n" +
                        "Погода: ${it.weather[0].description}"
                weatherInfoTextView.text = weatherText

                val iconUrl = "https://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png"
                val weatherIconImageView = findViewById<ImageView>(R.id.weatherIconImageView)
                Picasso.get().load(iconUrl).into(weatherIconImageView)
            }
        })
    }
}
