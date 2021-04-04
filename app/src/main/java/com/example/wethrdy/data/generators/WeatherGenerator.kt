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
package com.example.wethrdy.data.generators

import com.example.wethrdy.data.bo.TemperaturePairBO
import com.example.wethrdy.data.bo.WeatherForecastBO
import com.example.wethrdy.data.bo.enums.Hour
import com.example.wethrdy.data.bo.enums.WeatherStatus
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.autumnMeanMaxTemperature
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.autumnMeanMinTemperature
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.autumnWeatherStatus
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.springMeanMaxTemperature
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.springMeanMinTemperature
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.springWeatherStatus
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.summerMeanMaxTemperature
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.summerMeanMinTemperature
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.summerWeatherStatus
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.winterMeanMaxTemperature
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.winterMeanMinTemperature
import com.example.wethrdy.data.generators.WeatherGenerator.WeatherGeneratorParams.winterWeatherStatus
import java.time.DayOfWeek
import java.time.LocalDate

object WeatherGenerator {

    object WeatherGeneratorParams {

        const val springMeanMinTemperature = 10
        const val springMeanMaxTemperature = 24
        val springWeatherStatus = listOf(
            WeatherStatus.CLEAR,
            WeatherStatus.CLEAR,
            WeatherStatus.CLEAR,
            WeatherStatus.CLEAR,
            WeatherStatus.RAIN,
            WeatherStatus.RAIN,
            WeatherStatus.CLOUDY,
            WeatherStatus.CLOUDY,
            WeatherStatus.SHOWERS,
            WeatherStatus.SHOWERS
        )
        const val summerMeanMinTemperature = 18
        const val summerMeanMaxTemperature = 32
        val summerWeatherStatus = listOf(
            WeatherStatus.CLEAR,
            WeatherStatus.CLEAR,
            WeatherStatus.CLEAR,
            WeatherStatus.CLEAR,
            WeatherStatus.CLEAR,
            WeatherStatus.CLEAR,
            WeatherStatus.CLEAR,
            WeatherStatus.CLOUDY,
            WeatherStatus.SHOWERS,
            WeatherStatus.RAIN
        )
        const val autumnMeanMinTemperature = 8
        const val autumnMeanMaxTemperature = 24
        val autumnWeatherStatus = listOf(
            WeatherStatus.CLEAR,
            WeatherStatus.CLEAR,
            WeatherStatus.CLOUDY,
            WeatherStatus.CLOUDY,
            WeatherStatus.SHOWERS,
            WeatherStatus.SHOWERS,
            WeatherStatus.SHOWERS,
            WeatherStatus.RAIN,
            WeatherStatus.RAIN,
            WeatherStatus.RAIN
        )
        const val winterMeanMinTemperature = 0
        const val winterMeanMaxTemperature = 15
        val winterWeatherStatus = listOf(
            WeatherStatus.CLEAR,
            WeatherStatus.CLOUDY,
            WeatherStatus.CLOUDY,
            WeatherStatus.CLOUDY,
            WeatherStatus.RAIN,
            WeatherStatus.RAIN,
            WeatherStatus.SNOW,
            WeatherStatus.SNOW,
            WeatherStatus.SNOW,
            WeatherStatus.SNOW
        )
    }

    fun generateWeek(season: Season): List<WeatherForecastBO> {
        val localDate = LocalDate.now()
        return DayOfWeek.values().map {
            generateWeatherByDay(season, it, localDate)
        }
    }

    fun generateDay(season: Season): List<WeatherForecastBO> {
        return Hour.values().map {
            generateWeatherByHour(season, it)
        }
    }

    private fun generateWeatherByDay(
        season: Season,
        day: DayOfWeek,
        date: LocalDate
    ): WeatherForecastBO {
        val minMeanTemperature = getMinMeanTemperatureBySeason(season)
        val maxMeanTemperature = getMaxMeanTemperatureBySeason(season)
        return WeatherForecastBO(
            day,
            date,
            Hour.TWELVE_AM,
            generateWeatherStatus(season),
            TemperaturePairBO(
                ((maxMeanTemperature - 5)..(maxMeanTemperature + 5)).random(),
                ((minMeanTemperature - 5)..(minMeanTemperature + 5)).random()
            ),
            0, 0
        )
    }

    private fun generateWeatherByHour(season: Season, hour: Hour): WeatherForecastBO {
        val minMeanTemperature = getMinMeanTemperatureBySeason(season)
        val maxMeanTemperature = getMaxMeanTemperatureBySeason(season)
        return WeatherForecastBO(
            DayOfWeek.MONDAY,
            LocalDate.now(),
            hour,
            generateWeatherStatus(season),
            TemperaturePairBO(
                ((maxMeanTemperature - 5)..(maxMeanTemperature + 5)).random(),
                ((minMeanTemperature - 5)..(minMeanTemperature + 5)).random()
            ),
            0, 0
        )
    }

    private fun generateWeatherStatus(season: Season) = getWeatherStatusBySeason(season).random()

    private fun getWeatherStatusBySeason(season: Season) = when (season) {
        Season.SPRING -> springWeatherStatus
        Season.SUMMER -> summerWeatherStatus
        Season.AUTUMN -> autumnWeatherStatus
        Season.WINTER -> winterWeatherStatus
    }

    private fun getMinMeanTemperatureBySeason(season: Season) = when (season) {
        Season.SPRING -> springMeanMinTemperature
        Season.SUMMER -> summerMeanMinTemperature
        Season.AUTUMN -> autumnMeanMinTemperature
        Season.WINTER -> winterMeanMinTemperature
    }

    private fun getMaxMeanTemperatureBySeason(season: Season) = when (season) {
        Season.SPRING -> springMeanMaxTemperature
        Season.SUMMER -> summerMeanMaxTemperature
        Season.AUTUMN -> autumnMeanMaxTemperature
        Season.WINTER -> winterMeanMaxTemperature
    }
}
