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

import com.example.wethrdy.data.bo.WeatherForecastBO

interface WeatherForecastRepository {

    suspend fun getCurrentForecastDetails(city: String): WeatherForecastBO

    suspend fun getDailyForecast(city: String): List<WeatherForecastBO>

    suspend fun getHourlyForecast(city: String): List<WeatherForecastBO>
}
