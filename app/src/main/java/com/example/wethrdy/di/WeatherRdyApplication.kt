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
package com.p4r4d0x.edmclassifier.di

import android.app.Application
import com.example.wethrdy.di.appModule
import com.example.wethrdy.di.dataSourcesModule
import com.example.wethrdy.di.networkingModule
import com.example.wethrdy.di.repositoriesModule
import com.example.wethrdy.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherRdyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidContext(this@WeatherRdyApplication)
            modules(
                listOf(
                    appModule,
                    networkingModule,
                    viewModelModule,
//                    useCaseModule,
                    dataSourcesModule,
                    repositoriesModule
                )
            )
        }
    }
}
