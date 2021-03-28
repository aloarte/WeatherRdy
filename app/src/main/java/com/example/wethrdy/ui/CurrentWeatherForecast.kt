package com.example.wethrdy.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.wethrdy.data.bo.CurrentForecastDetailsBO
import com.example.wethrdy.main.WeatherForecastViewModel
import com.example.wethrdy.ui.theme.MediumDimension
import com.example.wethrdy.ui.theme.SmallDimension


@Composable
fun CurrentForecast(viewModel: WeatherForecastViewModel) {
    val currentWeatherForecast by viewModel.currentForecastDetail.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SmallDimension, start = MediumDimension, end = MediumDimension)
    ) {
        CurrentForecastDetail(currentWeatherForecast)
    }

}

@Composable
fun CurrentForecastDetail(currentDetail: CurrentForecastDetailsBO?) {
    Column {
        currentDetail?.let {
            Text(text = it.status.name, style = MaterialTheme.typography.h2)
            Text(text = it.humidity.toString(), style = MaterialTheme.typography.body1)
            Text(text = it.temperature.toString(), style = MaterialTheme.typography.body1)
            Text(text = it.maxTemperature.toString(), style = MaterialTheme.typography.body1)
            Text(text = it.minTemperature.toString(), style = MaterialTheme.typography.body1)
        }
    }

}