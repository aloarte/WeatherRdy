package com.example.wethrdy.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.wethrdy.main.WeatherForecastViewModel

@Composable
fun HourlyWeatherForecast(viewModel: WeatherForecastViewModel) {
    val hourlyWeatherForecast by viewModel.currentForecastDetail.observeAsState()
}