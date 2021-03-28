package com.example.wethrdy.data.usecases

import com.example.wethrdy.data.bo.HourlyWeatherForecastBO
import com.example.wethrdy.data.repository.WeatherForecastRepository

class HourlyWeatherForecastUseCase(private val repository: WeatherForecastRepository) :
    BaseUseCase<String, List<HourlyWeatherForecastBO>>() {
    override suspend fun run(params: String): List<HourlyWeatherForecastBO> {
        return repository.getHourlyForecast(params)
    }
}