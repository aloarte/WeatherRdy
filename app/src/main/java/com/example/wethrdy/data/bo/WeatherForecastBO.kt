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
package com.example.wethrdy.data.bo

import com.example.wethrdy.data.bo.enums.Hour
import com.example.wethrdy.data.bo.enums.WeatherStatus
import java.time.DayOfWeek
import java.time.LocalDate

class WeatherForecastBO(
    val day: DayOfWeek = DayOfWeek.MONDAY,
    val date: LocalDate = LocalDate.now(),
    val hour: Hour = Hour.TWELVE_AM,
    val status: WeatherStatus = WeatherStatus.CLEAR,
    val temperature: TemperaturePairBO = TemperaturePairBO(0, 0),
    val currentTemperature: Int = 0,
    var wind: Int = 0,
    var humidity: Int = 0
)
