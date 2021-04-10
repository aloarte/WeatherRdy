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
import com.example.wethrdy.data.bo.WeatherForecastBO
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
        val isNightTime = checkIfNightTime(hour)
        return when (weatherStatus) {
            WeatherStatus.CLEAR -> if (isNightTime) R.drawable.ic_clear_night else R.drawable.ic_clear_day
            WeatherStatus.CLOUDY -> if (isNightTime) R.drawable.ic_cloudy_night else R.drawable.ic_cloudy_day
            WeatherStatus.SHOWERS -> if (isNightTime) R.drawable.ic_showers_night else R.drawable.ic_showers_day
            WeatherStatus.RAIN -> if (isNightTime) R.drawable.ic_rain_night else R.drawable.ic_rain_day
            WeatherStatus.SNOW -> if (isNightTime) R.drawable.ic_snow_night else R.drawable.ic_snow_day
        }
    }

    fun checkIfNightTime(hour: Hour) = hour.ordinal !in 9..21

    fun getBackground(backgroundWeatherState: WeatherBackground?) = when (backgroundWeatherState) {
        WeatherBackground.DAY -> R.mipmap.background_day
        WeatherBackground.NIGHT -> R.mipmap.background_night
        WeatherBackground.RAIN -> R.mipmap.background_rainy
        WeatherBackground.SNOW -> R.mipmap.background_snow_day
        WeatherBackground.SNOW_NIGHT -> R.mipmap.background_snow_night
        WeatherBackground.CLOUDY -> R.mipmap.background_cloudy_day
        WeatherBackground.CLOUDY_NIGHT -> R.mipmap.background_cloudy_night

        else -> R.mipmap.background_day
    }

    fun getBackgroundState(
        forecastDetails: WeatherForecastBO,
        nightMode: Boolean
    ): WeatherBackground = when (forecastDetails.status) {
        WeatherStatus.CLEAR -> if (nightMode) WeatherBackground.NIGHT else WeatherBackground.DAY
        WeatherStatus.SHOWERS, WeatherStatus.RAIN -> WeatherBackground.RAIN
        WeatherStatus.CLOUDY -> if (nightMode) WeatherBackground.CLOUDY_NIGHT else WeatherBackground.CLOUDY
        WeatherStatus.SNOW -> if (nightMode) WeatherBackground.SNOW_NIGHT else WeatherBackground.SNOW
    }

    fun getCardColorByState(backgroundWeatherState: WeatherBackground?) =
        when (backgroundWeatherState) {
            WeatherBackground.DAY -> darkGrayTransparent20
            WeatherBackground.NIGHT -> grayTransparent
            WeatherBackground.RAIN -> darkGrayTransparent40
            WeatherBackground.SNOW -> whiteIntenseTransparent
            WeatherBackground.SNOW_NIGHT -> grayTransparent
            WeatherBackground.CLOUDY -> whiteTransparent
            else -> Color.Black
        }

    fun getContentColorByState(backgroundWeatherState: WeatherBackground?) =
        when (backgroundWeatherState) {
            WeatherBackground.DAY -> Color.Black
            WeatherBackground.NIGHT -> Color.White
            WeatherBackground.RAIN -> Color.White
            WeatherBackground.SNOW -> brownContent
            WeatherBackground.SNOW_NIGHT -> Color.White
            WeatherBackground.CLOUDY -> Color.White
            else -> Color.Black
        }

    fun getWeatherAnimation(weatherStatus: WeatherStatus, hour: Hour): WeatherAnimationState {
        val isNightTime = checkIfNightTime(hour)
        return when (weatherStatus) {
            WeatherStatus.CLEAR -> if (isNightTime) WeatherAnimationState.CLEAR_NIGHT else WeatherAnimationState.SUNNY
            WeatherStatus.CLOUDY -> WeatherAnimationState.CLOUDY
            WeatherStatus.SHOWERS -> WeatherAnimationState.RAIN_SHOWERS
            WeatherStatus.RAIN -> WeatherAnimationState.RAINY
            WeatherStatus.SNOW -> WeatherAnimationState.SNOWY
        }
    }
}
