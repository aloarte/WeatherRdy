package com.example.wethrdy.main

import com.example.wethrdy.R
import com.example.wethrdy.data.bo.enums.Hour
import com.example.wethrdy.data.bo.enums.WeatherStatus
import com.example.wethrdy.main.core.WeatherBackground

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
            WeatherBackground.Day -> R.mipmap.background_day_8bit
            WeatherBackground.Night -> R.mipmap.background_night_8bit
            WeatherBackground.Rain -> R.mipmap.background_rain_8bit
            else -> R.mipmap.background_day_8bit
        }
    }

}