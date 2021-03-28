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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.wethrdy.R
import com.example.wethrdy.main.WeatherForecastViewModel

@Composable
fun WeatherRdySurface(viewModel: WeatherForecastViewModel, content: @Composable () -> Unit) {

    val backgroundWeatherState by viewModel.backgroundState.observeAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            contentScale = ContentScale.FillBounds,
            painter = painterResource(getBackground(backgroundWeatherState)),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        )
        Column(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

fun getBackground(backgroundWeatherState: WeatherBackground?): Int {
    return when (backgroundWeatherState) {
        WeatherBackground.Day -> R.mipmap.background_day_8bit
        WeatherBackground.Night -> R.mipmap.background_night_8bit
        WeatherBackground.Rain -> R.mipmap.background_rain_8bit
        else -> R.mipmap.background_day_8bit
    }
}
