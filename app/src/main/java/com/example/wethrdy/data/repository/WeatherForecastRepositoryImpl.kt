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

class WeatherForecastRepositoryImpl(
//    val localDatasource: WeatherForecastDatasourceLocalImpl,
//    val remoteDatasource: WeatherForecastDatasourceLocalImpl

) : WeatherForecastRepository {

    override suspend fun getCurrentForecastDetails(city: String): CurrentForecastDetailsBO {
        TODO("Not yet implemented")
    }

    override suspend fun getDailyForecast(city: String): List<DailyWeatherForecastBO> {
        TODO("Not yet implemented")
    }

    override suspend fun getHourlyForecast(city: String): List<HourlyWeatherForecastBO> {
        TODO("Not yet implemented")
    }
}
