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
package com.example.wethrdy.di

import com.example.wethrdy.data.repository.WeatherForecastRepository
import com.example.wethrdy.data.repository.WeatherForecastRepositoryImpl
import com.example.wethrdy.data.source.LocalWeatherForecastDatasourceImpl
import com.example.wethrdy.data.source.LocalWeatherForecastSource
import com.example.wethrdy.data.source.WeatherForecastDatasourceImpl
import com.example.wethrdy.data.source.WeatherForecastSource
import com.example.wethrdy.data.usecases.DailyWeatherForecastUseCase
import com.example.wethrdy.data.usecases.HourlyWeatherForecastUseCase
import com.example.wethrdy.main.WeatherForecastViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val appModule = module {
}
val viewModelModule = module {
    viewModel { WeatherForecastViewModel(get(), get()) }
}
val dataSourcesModule = module {
    factory<LocalWeatherForecastSource> { (LocalWeatherForecastDatasourceImpl()) }
    factory<WeatherForecastSource> { (WeatherForecastDatasourceImpl()) }
}

val useCaseModule = module {
    factory { DailyWeatherForecastUseCase(get()) }
    factory { HourlyWeatherForecastUseCase(get()) }
}

val repositoriesModule = module {
    factory<WeatherForecastRepository> { WeatherForecastRepositoryImpl(get(), get()) }
}

val networkingModule = module {
}
