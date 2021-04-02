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

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wethrdy.data.bo.DailyWeatherForecastBO
import com.example.wethrdy.data.bo.enums.Hour
import com.example.wethrdy.main.WeatherForecastViewModel
import com.example.wethrdy.main.core.WeatherUtils
import com.example.wethrdy.main.graph.GraphUtils
import com.example.wethrdy.ui.TemperatureGraph
import com.example.wethrdy.ui.theme.MediumDimension
import com.example.wethrdy.ui.theme.TinyDimension

@Composable
fun DailyWeatherForecast(viewModel: WeatherForecastViewModel) {
    val dailyWeatherForecast by viewModel.dailyForecast.observeAsState()
    val backgroundWeatherState by viewModel.backgroundState.observeAsState()

    ForecastSurface(backgroundWeatherState = backgroundWeatherState) {
        Column(modifier = Modifier.padding(MediumDimension)) {
            Text("Daily Forecast".toUpperCase(), style = MaterialTheme.typography.h2)
            Spacer(modifier = Modifier.height(10.dp))
            DailyWeatherForecastList(dailyWeatherForecast)
            DailyWeatherGraph(dailyWeatherForecast)
        }
    }
}

@Composable
fun DailyWeatherGraph(dailyWeatherForecast: List<DailyWeatherForecastBO>?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        dailyWeatherForecast?.let { dailyForecast ->
            val temperaturePairs = dailyForecast.map { it.temperature }
            TemperatureGraph(
                temperaturePairs = temperaturePairs,
                maxTemperaturePoints = GraphUtils.computeDailyWeatherCurvePoints(
                    temperaturePairs,
                    true
                ),
                minTemperaturePoints = GraphUtils.computeDailyWeatherCurvePoints(
                    temperaturePairs,
                    false
                )
            )
        }
    }
}

@Composable
fun DailyWeatherForecastList(dailyWeatherForecastList: List<DailyWeatherForecastBO>?) {
    dailyWeatherForecastList?.let {
        LazyRow {
            items(it) { data ->
                DailyWeatherForecastItem(data)
                if (data != it.last()) Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}

@Composable
fun DailyWeatherForecastItem(forecastItem: DailyWeatherForecastBO) {
    Column(
        Modifier.padding(TinyDimension),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(forecastItem.day.name, style = MaterialTheme.typography.h3)
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
        Text(
            forecastItem.temperature.maxTemperature.toString(),
            style = MaterialTheme.typography.body1
        )
        Text(
            forecastItem.temperature.minTemperature.toString(),
            style = MaterialTheme.typography.body1
        )
    }
}
