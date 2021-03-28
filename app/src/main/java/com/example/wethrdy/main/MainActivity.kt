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
package com.example.wethrdy.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.example.wethrdy.main.core.WeatherBackground
import com.example.wethrdy.main.core.WeatherRdySurface
import com.example.wethrdy.ui.CurrentForecast
import com.example.wethrdy.ui.DailyWeatherForecast
import com.example.wethrdy.ui.HourlyWeatherForecast
import com.example.wethrdy.ui.theme.MyTheme
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherForecastViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                WeatherRdy(viewModel)
            }
        }
        viewModel.setState(WeatherBackground.Day)
        viewModel.getCurrentWeatherForecast("Madrid")
    }
}

@Composable
fun WeatherRdy(forecastViewModel: WeatherForecastViewModel) {
    WeatherRdySurface(viewModel = forecastViewModel) {
        CurrentForecast(viewModel = forecastViewModel)
        HourlyWeatherForecast(viewModel = forecastViewModel)
        DailyWeatherForecast(viewModel = forecastViewModel)
    }
}



