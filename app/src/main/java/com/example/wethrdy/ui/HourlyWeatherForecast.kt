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
package com.example.wethrdy.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wethrdy.data.bo.HourlyWeatherForecastBO
import com.example.wethrdy.main.WeatherForecastViewModel
import com.example.wethrdy.main.WeatherUtils.getWeatherIcon
import com.example.wethrdy.ui.theme.MediumDimension
import com.example.wethrdy.ui.theme.SmallDimension

@Composable
fun HourlyWeatherForecast(viewModel: WeatherForecastViewModel) {
    val hourlyWeatherForecast by viewModel.hourlyForecast.observeAsState()
    Column(Modifier.padding(MediumDimension)) {
        Text("Hourly Forecast".toUpperCase())
        HourlyWeatherForecastList(hourlyWeatherForecast)

    }

}

@Composable
fun HourlyWeatherForecastList(hourlyWeatherForecastList: List<HourlyWeatherForecastBO>?) {
    hourlyWeatherForecastList?.let {
        LazyRow {
            items(it) { data ->
                HourlyWeatherForecastItem(data)
                if (data != it.last()) Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
fun HourlyWeatherForecastItem(forecastItem: HourlyWeatherForecastBO) {
    Column(
        Modifier.padding(SmallDimension),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(forecastItem.hour.stringValue)
        Icon(
            painter = painterResource(id = getWeatherIcon(forecastItem.status, forecastItem.hour)),
            contentDescription = "",
            Modifier
                .width(30.dp)
                .height(30.dp)
        )
        Text(forecastItem.temperature.toString())
    }
}
