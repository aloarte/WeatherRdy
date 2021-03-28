package com.example.wethrdy.data.usecases

import com.example.wethrdy.data.bo.CurrentForecastDetailsBO
import com.example.wethrdy.data.repository.WeatherForecastRepository

class DetailCurrentWeatherForecastUseCase(private val repository: WeatherForecastRepository) :
    BaseUseCase<String, CurrentForecastDetailsBO>() {

    override suspend fun run(params: String): CurrentForecastDetailsBO {
        return repository.getCurrentForecastDetails(params)
    }
}