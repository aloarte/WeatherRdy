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

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.wethrdy.main.WeatherUtils.getCardColorByState
import com.example.wethrdy.main.WeatherUtils.getContentColorByState
import com.example.wethrdy.main.core.WeatherBackground

@Composable
fun ForecastSurface(
    backgroundWeatherState: WeatherBackground?,
    content: @Composable () -> Unit
) {

    val contentColor = getContentColorByState(backgroundWeatherState)
    val cardColor = getCardColorByState(backgroundWeatherState)

    Surface(
        border = BorderStroke(1.dp, cardColor),
        contentColor = contentColor,
        color = cardColor,
        shape = MaterialTheme.shapes.medium
    ) {
        content()
    }
}
