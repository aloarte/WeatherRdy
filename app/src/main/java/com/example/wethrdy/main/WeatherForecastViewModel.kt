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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wethrdy.data.bo.WeatherForecastBO
import com.example.wethrdy.data.usecases.DailyWeatherForecastUseCase
import com.example.wethrdy.data.usecases.DetailCurrentWeatherForecastUseCase
import com.example.wethrdy.data.usecases.HourlyWeatherForecastUseCase
import com.example.wethrdy.main.core.WeatherBackground
import com.example.wethrdy.main.core.WeatherUtils.getBackgroundState
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val detailCurrentWeatherUseCase: DetailCurrentWeatherForecastUseCase,
    private val hourlyWeatherUseCase: HourlyWeatherForecastUseCase,
    private val dailyWeatherUseCase: DailyWeatherForecastUseCase,

) : ViewModel() {

    private val _cities = MutableLiveData<List<String>>(listOf())
    val cities: LiveData<List<String>> = _cities

    private val _currentForecastDetail = MutableLiveData<WeatherForecastBO>()
    val currentForecastDetail: LiveData<WeatherForecastBO>
        get() = _currentForecastDetail

    private val _hourlyForecast = MutableLiveData<List<WeatherForecastBO>>()
    val hourlyForecast: LiveData<List<WeatherForecastBO>>
        get() = _hourlyForecast

    private val _dailyForecast = MutableLiveData<List<WeatherForecastBO>>()
    val dailyForecast: LiveData<List<WeatherForecastBO>>
        get() = _dailyForecast

    private val _backgroundState = MutableLiveData<WeatherBackground>()
    val backgroundState: LiveData<WeatherBackground>
        get() = _backgroundState

    fun setState(newState: WeatherBackground) = viewModelScope.launch {
        _backgroundState.postValue(newState)
    }

    fun getCurrentWeatherForecast(country: String, nightMode: Boolean) {

        detailCurrentWeatherUseCase.invoke(viewModelScope, params = country) {
            _currentForecastDetail.value = it
            _backgroundState.postValue(getBackgroundState(it, nightMode))
        }
        hourlyWeatherUseCase.invoke(viewModelScope, params = country) {
            _hourlyForecast.value = it
        }
        dailyWeatherUseCase.invoke(viewModelScope, params = country) {
            _dailyForecast.value = it
        }
    }
}
