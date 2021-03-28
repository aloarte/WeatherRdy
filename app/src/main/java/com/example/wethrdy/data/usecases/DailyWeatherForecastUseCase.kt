package com.example.wethrdy.data.usecases

import com.example.wethrdy.data.bo.DailyWeatherForecastBO
import com.example.wethrdy.data.repository.WeatherForecastRepository

class DailyWeatherForecastUseCase(private val repository: WeatherForecastRepository) :
    BaseUseCase<String, List<DailyWeatherForecastBO>>() {


    override suspend fun run(params: String): List<DailyWeatherForecastBO> {
        return repository.getDailyForecast(params)
    }
}