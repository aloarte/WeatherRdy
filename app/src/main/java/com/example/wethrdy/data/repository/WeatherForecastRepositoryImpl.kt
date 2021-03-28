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

import com.example.wethrdy.data.bo.CurrentForecastDetailsBO
import com.example.wethrdy.data.bo.DailyWeatherForecastBO
import com.example.wethrdy.data.bo.HourlyWeatherForecastBO
import com.example.wethrdy.data.bo.enums.Hour
import com.example.wethrdy.data.bo.enums.WeatherStatus
import com.example.wethrdy.data.source.LocalWeatherForecastSource
import com.example.wethrdy.data.source.WeatherForecastSource
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.util.Date

class WeatherForecastRepositoryImpl(
    private val localSource: LocalWeatherForecastSource,
    private val remoteSource: WeatherForecastSource,

    ) : WeatherForecastRepository {

    override suspend fun getCurrentForecastDetails(city: String): CurrentForecastDetailsBO {
        localSource.getForecastByLocation(city)
        return CurrentForecastDetailsBO(
            status = WeatherStatus.CLEAR,
            temperature = 14,
            maxTemperature = 22,
            minTemperature = 7,
            precipitation = 0,
            wind = 80,
            humidity = 57

        )
    }

    override suspend fun getDailyForecast(city: String): List<DailyWeatherForecastBO> {
        localSource.getForecastByLocation(city)
        return listOf(
            DailyWeatherForecastBO(DayOfWeek.MONDAY, LocalDate.of( 2021 , Month.MARCH , 28 ),WeatherStatus.CLOUDY,22,7),
            DailyWeatherForecastBO(DayOfWeek.TUESDAY,LocalDate.of( 2021 , Month.MARCH , 29 ),WeatherStatus.CLOUDY,22,9),
            DailyWeatherForecastBO(DayOfWeek.WEDNESDAY,LocalDate.of( 2021 , Month.MARCH , 30 ),WeatherStatus.CLOUDY,22,7),
            DailyWeatherForecastBO(DayOfWeek.THURSDAY,LocalDate.of( 2021 , Month.MARCH , 31 ),WeatherStatus.CLEAR,23,9),
            DailyWeatherForecastBO(DayOfWeek.FRIDAY,LocalDate.of( 2021 , Month.APRIL , 1 ),WeatherStatus.SHOWERS,21,8),
            DailyWeatherForecastBO(DayOfWeek.SATURDAY,LocalDate.of( 2021 , Month.APRIL , 2 ),WeatherStatus.SHOWERS,18,7),
            DailyWeatherForecastBO(DayOfWeek.SUNDAY,LocalDate.of( 2021 , Month.APRIL , 3 ),WeatherStatus.SHOWERS,18,5),)
    }

    override suspend fun getHourlyForecast(city: String): List<HourlyWeatherForecastBO> {
        localSource.getForecastByLocation(city)
        return listOf(
            HourlyWeatherForecastBO(Hour.TWELVE_AM,WeatherStatus.CLEAR,14),
            HourlyWeatherForecastBO(Hour.ONE_PM,WeatherStatus.CLEAR,16),
            HourlyWeatherForecastBO(Hour.TWO_PM,WeatherStatus.CLEAR,18),
            HourlyWeatherForecastBO(Hour.THREE_PM,WeatherStatus.CLOUDY,19),
            HourlyWeatherForecastBO(Hour.FOUR_PM,WeatherStatus.CLOUDY,20),
            HourlyWeatherForecastBO(Hour.FIVE_PM,WeatherStatus.CLOUDY,21),
            HourlyWeatherForecastBO(Hour.SIX_PM,WeatherStatus.CLOUDY,21),
            HourlyWeatherForecastBO(Hour.SEVEN_PM,WeatherStatus.CLOUDY,21),
            HourlyWeatherForecastBO(Hour.EIGHT_PM,WeatherStatus.CLOUDY,19),
            HourlyWeatherForecastBO(Hour.NINE_PM,WeatherStatus.CLOUDY,17),
            HourlyWeatherForecastBO(Hour.TEN_PM,WeatherStatus.CLOUDY,16),
            HourlyWeatherForecastBO(Hour.ELEVEN_PM,WeatherStatus.CLOUDY,14),
            HourlyWeatherForecastBO(Hour.TWELVE_PM,WeatherStatus.CLOUDY,12),
            HourlyWeatherForecastBO(Hour.ONE_AM,WeatherStatus.CLOUDY,11),
            HourlyWeatherForecastBO(Hour.TWO_AM,WeatherStatus.CLOUDY,10),
            HourlyWeatherForecastBO(Hour.THREE_AM,WeatherStatus.CLOUDY,9),
            HourlyWeatherForecastBO(Hour.FOUR_AM,WeatherStatus.CLOUDY,9),
            HourlyWeatherForecastBO(Hour.FIVE_AM,WeatherStatus.CLOUDY,8),
            HourlyWeatherForecastBO(Hour.SIX_AM,WeatherStatus.CLOUDY,8),
            HourlyWeatherForecastBO(Hour.SEVEN_AM,WeatherStatus.CLOUDY,7),
            HourlyWeatherForecastBO(Hour.EIGHT_AM,WeatherStatus.CLOUDY,7),
            HourlyWeatherForecastBO(Hour.NINE_AM,WeatherStatus.CLOUDY,8),
            HourlyWeatherForecastBO(Hour.TEN_AM,WeatherStatus.CLOUDY,10),
            HourlyWeatherForecastBO(Hour.ELEVEN_AM,WeatherStatus.CLOUDY,13),
            HourlyWeatherForecastBO(Hour.TWELVE_AM,WeatherStatus.CLOUDY,15),


            )
    }
}
