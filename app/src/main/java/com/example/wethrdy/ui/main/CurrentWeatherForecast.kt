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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wethrdy.data.bo.WeatherForecastBO
import com.example.wethrdy.main.WeatherForecastViewModel
import com.example.wethrdy.main.core.HumidityLane
import com.example.wethrdy.main.core.WeatherAnimation
import com.example.wethrdy.main.core.WeatherUtils.getWeatherAnimation
import com.example.wethrdy.main.core.WindLane
import com.example.wethrdy.ui.theme.MediumDimension

@Composable
fun CurrentForecast(viewModel: WeatherForecastViewModel) {
    val currentWeatherForecast by viewModel.currentForecastDetail.observeAsState()
    val backgroundWeatherState by viewModel.backgroundState.observeAsState()
    ForecastSurface(Modifier, backgroundWeatherState) {
        Column {
            CurrentForecastDetail(currentWeatherForecast)
            HourlyWeatherForecast(viewModel)
        }
    }
}

@Composable
fun CurrentForecastDetail(currentDetail: WeatherForecastBO?) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(MediumDimension)
    ) {
        currentDetail?.let {
            Column(Modifier.weight(2f)) {
                CurrentDetailAnimationColumnContent(currentDetail)
            }
            Column(Modifier.weight(1f)) {
                CurrentDetailSummaryContent(currentDetail)
            }
        }
    }
}

@Composable
fun CurrentDetailAnimationColumnContent(currentDetail: WeatherForecastBO) {
    Text(text = currentDetail.status.name, style = MaterialTheme.typography.h1)
    Spacer(modifier = Modifier.height(20.dp))
    WeatherAnimation(
        weather = getWeatherAnimation(
            currentDetail.status,
            currentDetail.hour
        ),
        animationSize = 120.dp
    )
}

@Composable
fun CurrentDetailSummaryContent(currentDetail: WeatherForecastBO) {
    Spacer(modifier = Modifier.height(50.dp))
    HumidityLane(humidity = currentDetail.humidity, size = 20)

    Spacer(modifier = Modifier.height(5.dp))
    WindLane(wind = currentDetail.wind, size = 20)

    Spacer(modifier = Modifier.height(5.dp))
    Text(
        text = "${currentDetail.temperature.maxTemperature} º",
        style = MaterialTheme.typography.body1
    )
}
