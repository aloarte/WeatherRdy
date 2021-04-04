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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wethrdy.data.bo.WeatherForecastBO
import com.example.wethrdy.main.WeatherForecastViewModel

@Composable
fun CurrentForecast(viewModel: WeatherForecastViewModel) {
    val currentWeatherForecast by viewModel.currentForecastDetail.observeAsState()
    val backgroundWeatherState by viewModel.backgroundState.observeAsState()
    ForecastSurface(Modifier, backgroundWeatherState) {
        CurrentForecastDetail(currentWeatherForecast)
    }
}

@Composable
fun CurrentForecastDetail(currentDetail: WeatherForecastBO?) {
    Column(Modifier.fillMaxWidth().height(150.dp)) {
        currentDetail?.let {
            Text(text = it.status.name, style = MaterialTheme.typography.h2)
            Text(text = it.humidity.toString(), style = MaterialTheme.typography.body1)
            Text(text = it.temperature.maxTemperature.toString(), style = MaterialTheme.typography.body1)
            Text(text = it.temperature.minTemperature.toString(), style = MaterialTheme.typography.body1)
        }
    }
}
