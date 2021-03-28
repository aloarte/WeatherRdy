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
package com.example.wethrdy.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.wethrdy.R

private val turretRoadFonts = FontFamily(
    Font(R.font.turretroad_bold, FontWeight.Bold),
    Font(R.font.turretroad_medium, FontWeight.Medium),
    Font(R.font.turretroad_regular, FontWeight.Normal),

)

// Set of Material typography styles to start with
val typography = Typography(
    h1 = TextStyle(
        fontFamily = turretRoadFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        letterSpacing = 1.15.sp

    ),
    h2 = TextStyle(
        fontFamily = turretRoadFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        letterSpacing = 1.15.sp
    ),
    body1 = TextStyle(
        fontFamily = turretRoadFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = turretRoadFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 1.15.sp

    ),
    caption = TextStyle(
        fontFamily = turretRoadFonts,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        letterSpacing = 1.15.sp,

    )

)
