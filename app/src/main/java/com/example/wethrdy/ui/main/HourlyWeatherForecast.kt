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
package com.example.wethrdy.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wethrdy.R
import com.example.wethrdy.data.bo.WeatherForecastBO
import com.example.wethrdy.data.bo.enums.Hour
import com.example.wethrdy.main.WeatherForecastViewModel
import com.example.wethrdy.main.core.HumidityLane
import com.example.wethrdy.main.core.WeatherUtils
import com.example.wethrdy.main.core.WindLane
import com.example.wethrdy.ui.theme.MediumDimension
import com.example.wethrdy.ui.theme.TinyDimension
import java.util.Locale

@Composable
fun HourlyWeatherForecast(viewModel: WeatherForecastViewModel) {
    val hourlyWeatherForecast by viewModel.hourlyForecast.observeAsState()
    Column(Modifier.padding(MediumDimension)) {
        Text(stringResource(id = R.string.hourly_row_title).toUpperCase(Locale.ROOT), style = MaterialTheme.typography.h2)
        Spacer(modifier = Modifier.height(15.dp))
        HourlyWeatherForecastList(hourlyWeatherForecast)
    }
}

@Composable
fun HourlyWeatherForecastList(hourlyWeatherForecast: List<WeatherForecastBO>?) {
    hourlyWeatherForecast?.let {
        LazyRow {
            items(it) { data ->
                HourlyWeatherForecastItem(data)
                if (data != it.last()) Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}

@Composable
fun HourlyWeatherForecastItem(forecastItem: WeatherForecastBO) {
    Column(
        Modifier.padding(TinyDimension),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(forecastItem.hour.stringValue, style = MaterialTheme.typography.h3)
        Spacer(modifier = Modifier.height(4.dp))
        Icon(
            painter = painterResource(
                id = WeatherUtils.getWeatherIcon(
                    forecastItem.status,
                    Hour.TWELVE_AM
                )
            ),
            contentDescription = "",
            Modifier
                .width(50.dp)
                .height(50.dp)
        )
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            HumidityLane(humidity = forecastItem.humidity, size = 15)
            WindLane(wind = forecastItem.wind, size = 15)

        }

        Text(
            text = "${forecastItem.currentTemperature} º",
            style = MaterialTheme.typography.body1
        )
    }
}

