/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wethrdy.data.repository

import com.example.wethrdy.data.bo.TemperaturePairBO
import com.example.wethrdy.data.bo.WeatherForecastBO
import com.example.wethrdy.data.bo.enums.Hour
import com.example.wethrdy.data.bo.enums.WeatherStatus
import com.example.wethrdy.data.generators.Season
import com.example.wethrdy.data.generators.WeatherGenerator.generateDay
import com.example.wethrdy.data.generators.WeatherGenerator.generateWeek
import com.example.wethrdy.data.source.LocalWeatherForecastSource
import com.example.wethrdy.data.source.WeatherForecastSource
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month

class WeatherForecastRepositoryImpl(
    private val localSource: LocalWeatherForecastSource,
    private val remoteSource: WeatherForecastSource,

) : WeatherForecastRepository {

    override suspend fun getCurrentForecastDetails(city: String): WeatherForecastBO {
        localSource.getForecastByLocation(city)
        return WeatherForecastBO(
            DayOfWeek.MONDAY,
            LocalDate.of(2021, Month.MARCH, 28),
            Hour.TEN_AM,
            status = WeatherStatus.CLEAR,
            temperature = TemperaturePairBO(
                maxTemperature = 22,
                minTemperature = 7
            ),
            wind = 80,
            humidity = 57
        )
    }

    override suspend fun getDailyForecast(city: String): List<WeatherForecastBO> {
        localSource.getForecastByLocation(city)
        return generateWeek(Season.SUMMER)
    }

    override suspend fun getHourlyForecast(city: String): List<WeatherForecastBO> {
        localSource.getForecastByLocation(city)
        return generateDay(Season.SUMMER)
    }
}
