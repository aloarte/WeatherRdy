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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wethrdy.ui.theme.purple700
import com.example.wethrdy.ui.theme.teal200

@Composable
fun ForecastSurface(nightMode: Boolean, content: @Composable () -> Unit) {
    Surface(
        border = BorderStroke(1.dp, if (nightMode) purple700 else teal200),
        contentColor = if (nightMode) Color.White else Color.Black,
        color = if (nightMode) purple700 else teal200,
        shape = MaterialTheme.shapes.medium
    ) {
        content()
    }
}
