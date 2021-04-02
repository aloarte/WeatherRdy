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
package com.example.wethrdy.main.core

import androidx.compose.ui.graphics.Color
import com.example.wethrdy.R
import com.example.wethrdy.data.bo.CurrentForecastDetailsBO
import com.example.wethrdy.data.bo.enums.Hour
import com.example.wethrdy.data.bo.enums.WeatherStatus
import com.example.wethrdy.ui.theme.brownContent
import com.example.wethrdy.ui.theme.darkGrayTransparent20
import com.example.wethrdy.ui.theme.darkGrayTransparent40
import com.example.wethrdy.ui.theme.grayTransparent
import com.example.wethrdy.ui.theme.whiteIntenseTransparent
import com.example.wethrdy.ui.theme.whiteTransparent

object WeatherUtils {

    fun getWeatherIcon(weatherStatus: WeatherStatus, hour: Hour): Int {
        val isDayTime = checkIfDayTime(hour)
        return when (weatherStatus) {
            WeatherStatus.CLEAR -> if (isDayTime) R.drawable.ic_clear_day else R.drawable.ic_clear_night
            WeatherStatus.CLOUDY -> if (isDayTime) R.drawable.ic_cloudy_day else R.drawable.ic_cloudy_night
            WeatherStatus.SHOWERS -> if (isDayTime) R.drawable.ic_showers_day else R.drawable.ic_showers_night
            WeatherStatus.RAIN -> if (isDayTime) R.drawable.ic_rain_day else R.drawable.ic_rain_night
            WeatherStatus.SNOW -> if (isDayTime) R.drawable.ic_snow_day else R.drawable.ic_snow_night
        }
    }

    fun checkIfDayTime(hour: Hour): Boolean {
        return hour.ordinal !in 1..12
    }

    fun getBackground(backgroundWeatherState: WeatherBackground?): Int {
        return when (backgroundWeatherState) {
            WeatherBackground.DAY -> R.mipmap.background_day_8bit
            WeatherBackground.NIGHT -> R.mipmap.background_night_8bit
            WeatherBackground.RAIN -> R.mipmap.background_rainy_8bit
            WeatherBackground.SNOW -> R.mipmap.background_snow_8bit
            WeatherBackground.SNOW_NIGHT -> R.mipmap.background_snow_night_8bit
            WeatherBackground.CLOUDY -> R.mipmap.background_cloudy_8bit
            else -> R.mipmap.background_day_8bit
        }
    }

    fun getBackgroundState(
        forecastDetails: CurrentForecastDetailsBO,
        nightMode: Boolean
    ): WeatherBackground {
        return when (forecastDetails.status) {
            WeatherStatus.CLEAR -> if (nightMode) WeatherBackground.NIGHT else WeatherBackground.DAY
            WeatherStatus.SHOWERS, WeatherStatus.RAIN -> WeatherBackground.RAIN
            WeatherStatus.CLOUDY -> WeatherBackground.CLOUDY
            WeatherStatus.SNOW -> if (nightMode) WeatherBackground.SNOW_NIGHT else WeatherBackground.SNOW
        }
    }

    fun getCardColorByState(backgroundWeatherState: WeatherBackground?): Color {
        return when (backgroundWeatherState) {
            WeatherBackground.DAY -> darkGrayTransparent20
            WeatherBackground.NIGHT -> grayTransparent
            WeatherBackground.RAIN -> whiteTransparent
            WeatherBackground.SNOW -> whiteIntenseTransparent
            WeatherBackground.SNOW_NIGHT -> grayTransparent
            WeatherBackground.CLOUDY -> darkGrayTransparent40
            else -> Color.Black
        }
    }

    fun getContentColorByState(backgroundWeatherState: WeatherBackground?): Color {
        return when (backgroundWeatherState) {
            WeatherBackground.DAY -> Color.Black
            WeatherBackground.NIGHT -> Color.White
            WeatherBackground.RAIN -> Color.White
            WeatherBackground.SNOW -> brownContent
            WeatherBackground.SNOW_NIGHT -> Color.White
            WeatherBackground.CLOUDY -> Color.White
            else -> Color.Black
        }
    }
}
