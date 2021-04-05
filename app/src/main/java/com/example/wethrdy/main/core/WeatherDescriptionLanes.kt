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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wethrdy.R

@Composable
fun HumidityLane(humidity: Int, size: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_drop,
            ),
            contentDescription = "",
            Modifier
                .width(size.dp)
                .height(size.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = humidity.toString(), style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.width(2.dp))
        Text(text = "%", style = MaterialTheme.typography.body1)
    }
}

@Composable
fun WindLane(wind: Int, size: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_wind,
            ),
            contentDescription = "",
            Modifier
                .width(size.dp)
                .height(size.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = wind.toString(), style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.width(2.dp))
        Text(text = "km/h", style = MaterialTheme.typography.caption)
    }
}

@Composable
fun MaxTemperatureLane(temperature: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_arrow_up
            ),
            contentDescription = "",
            Modifier
                .width(20.dp)
                .height(20.dp)
        )
        Text(
            text = "$temperature º",
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun MinTemperatureLane(temperature: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_arrow_down
            ),
            contentDescription = "",
            Modifier
                .width(20.dp)
                .height(20.dp)
        )
        Text(
            text = "$temperature º",
            style = MaterialTheme.typography.body1
        )
    }
}
