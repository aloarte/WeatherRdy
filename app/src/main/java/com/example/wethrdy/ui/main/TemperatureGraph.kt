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
package com.example.wethrdy.ui.main

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.wethrdy.R
import com.example.wethrdy.data.bo.TemperaturePairBO
import com.example.wethrdy.data.bo.WeatherForecastBO
import com.example.wethrdy.data.bo.enums.Hour
import com.example.wethrdy.data.bo.enums.WeatherStatus
import com.example.wethrdy.main.core.WeatherBackground
import com.example.wethrdy.main.core.WeatherUtils
import com.example.wethrdy.main.core.WeatherUtils.getContentColorByState
import com.example.wethrdy.main.core.WeatherUtils.getMaxTemperatureColorByState
import com.example.wethrdy.main.core.WeatherUtils.getMinTemperatureColorByState
import com.example.wethrdy.main.graph.GraphCurvePoints
import com.example.wethrdy.main.graph.GraphUtils.TemperatureGraphParameters.cellSize
import com.example.wethrdy.main.graph.GraphUtils.TemperatureGraphParameters.heightInterval
import com.example.wethrdy.main.graph.GraphUtils.computeTemperaturePairCurvePoints
import java.time.DayOfWeek

@Composable
fun TemperatureGraph(
    weatherValues: List<WeatherForecastBO>,
    minTemperaturePoints: GraphCurvePoints,
    maxTemperaturePoints: GraphCurvePoints,
    weatherBackground: WeatherBackground
) {

    val curveColorMax = getMaxTemperatureColorByState(weatherBackground)

    val curveColorMin = getMinTemperatureColorByState(weatherBackground)
    val weatherColor = getContentColorByState(weatherBackground)
    val chartHeight = heightInterval
    val dpPerCell = cellSize
    val canvasWidth = dpPerCell.dp * (weatherValues.size + 1)
    val context = LocalContext.current
    val font = ResourcesCompat.getFont(context, R.font.turretroad_regular)

    Canvas(
        modifier = Modifier
            .height(chartHeight.dp)
            .width(canvasWidth),
        onDraw = {

            val maxTPoints = maxTemperaturePoints.points.map {
                it.copy(
                    x = it.x.dp.toPx(),
                    y = it.y.dp.toPx()
                )
            }
            val connectionMaxTPoints1 =
                maxTemperaturePoints.connectionPoints1.map {
                    it.copy(
                        x = it.x.dp.toPx(),
                        y = it.y.dp.toPx()
                    )
                }
            val connectionMaxTPoints2 =
                maxTemperaturePoints.connectionPoints2.map {
                    it.copy(
                        x = it.x.dp.toPx(),
                        y = it.y.dp.toPx()
                    )
                }
            val minTPoints = minTemperaturePoints.points.map {
                it.copy(
                    x = it.x.dp.toPx(),
                    y = it.y.dp.toPx()
                )
            }
            val connectionMinTPoints1 =
                minTemperaturePoints.connectionPoints1.map {
                    it.copy(
                        x = it.x.dp.toPx(),
                        y = it.y.dp.toPx()
                    )
                }
            val connectionMinTPoints2 =
                minTemperaturePoints.connectionPoints2.map {
                    it.copy(
                        x = it.x.dp.toPx(),
                        y = it.y.dp.toPx()
                    )
                }

            val curvePathMax = Path()
            val curvePathMin = Path()
            val curveBackgroundPath = Path()
            val dashIntervals = floatArrayOf(10f, 20f)
            val dashPhase = 25f
            val dashVerticalPadding = 20f

            if (maxTPoints.isNotEmpty() && connectionMaxTPoints1.isNotEmpty() && connectionMaxTPoints2.isNotEmpty()) {
                curvePathMax.reset()
                curvePathMax.moveTo(maxTPoints.first().x, maxTPoints.first().y)
                for (i in 1 until maxTPoints.size) {
                    curvePathMax.cubicTo(
                        connectionMaxTPoints1[i - 1].x,
                        connectionMaxTPoints1[i - 1].y,
                        connectionMaxTPoints2[i - 1].x,
                        connectionMaxTPoints2[i - 1].y,
                        maxTPoints[i].x,
                        maxTPoints[i].y
                    )
                    curveBackgroundPath.cubicTo(
                        connectionMaxTPoints1[i - 1].x,
                        connectionMaxTPoints1[i - 1].y,
                        connectionMaxTPoints2[i - 1].x,
                        connectionMaxTPoints2[i - 1].y,
                        maxTPoints[i].x,
                        maxTPoints[i].y
                    )

                    drawLine(
                        color = weatherColor,
                        pathEffect = PathEffect.dashPathEffect(
                            dashIntervals,
                            dashPhase
                        ),
                        start = Offset(
                            maxTPoints[i].x,
                            maxTPoints[i].y + dashVerticalPadding
                        ),
                        end = Offset(
                            maxTPoints[i].x,
                            chartHeight.dp.toPx() - dashVerticalPadding
                        )
                    )

                    if (i == maxTPoints.size - 1) {
                        curveBackgroundPath.lineTo(
                            maxTPoints[i].x,
                            chartHeight.dp.toPx()
                        )
                        curveBackgroundPath.lineTo(
                            maxTPoints.first().x,
                            chartHeight.dp.toPx()
                        )
                    }

                    with(weatherValues.getOrNull(i - 1)) {
                        if (this != null) {
                            val paint = Paint().apply {
                                typeface = font
                                textAlign = Paint.Align.CENTER
                                textSize = 54f
                                color = curveColorMax.toArgb()
                            }
                            val weatherPaint = Paint().apply {
                                typeface = font
                                textAlign = Paint.Align.CENTER
                                textSize = 40f
                                color = weatherColor.toArgb()
                            }
                            val iconPaint = Paint().apply {
                                style = Paint.Style.FILL
                                colorFilter = PorterDuffColorFilter(
                                    weatherColor.toArgb(),
                                    PorterDuff.Mode.SRC_IN
                                )
                            }
                            drawIntoCanvas { canvas ->
                                // Draw hour
                                canvas.nativeCanvas.drawText(
                                    day.name,
                                    maxTPoints[i].x,
                                    50F,
                                    weatherPaint
                                )
                                // Draw hour
                                canvas.nativeCanvas.drawBitmap(
                                    getDrawable(
                                        context,
                                        WeatherUtils.getWeatherIcon(
                                            status,
                                            Hour.TWELVE_AM
                                        )
                                    )!!.toBitmap(140, 140),
                                    maxTPoints[i].x - 70,
                                    80F,
                                    iconPaint
                                )
                                // Draw max temperature
                                canvas.nativeCanvas.drawText(
                                    temperature.maxTemperature.toString() + "°",
                                    maxTPoints[i].x,
                                    maxTPoints[i].y - 50,
                                    paint
                                )
                            }
                        }
                    }
                }
                drawPath(curvePathMax, curveColorMax, style = Stroke(width = 4.dp.toPx()))
            }
            if (minTPoints.isNotEmpty() && connectionMinTPoints1.isNotEmpty() && connectionMinTPoints2.isNotEmpty()) {
                curvePathMin.reset()
                curvePathMin.moveTo(minTPoints.first().x, minTPoints.first().y)
                for (i in 1 until minTPoints.size) {
                    curvePathMin.cubicTo(
                        connectionMinTPoints1[i - 1].x,
                        connectionMinTPoints1[i - 1].y,
                        connectionMinTPoints2[i - 1].x,
                        connectionMinTPoints2[i - 1].y,
                        minTPoints[i].x,
                        minTPoints[i].y
                    )
                    curveBackgroundPath.cubicTo(
                        connectionMinTPoints1[i - 1].x,
                        connectionMinTPoints1[i - 1].y,
                        connectionMinTPoints2[i - 1].x,
                        connectionMinTPoints2[i - 1].y,
                        minTPoints[i].x,
                        minTPoints[i].y
                    )

                    drawLine(
                        color = weatherColor,
                        pathEffect = PathEffect.dashPathEffect(
                            dashIntervals,
                            dashPhase
                        ),
                        start = Offset(
                            minTPoints[i].x,
                            minTPoints[i].y + dashVerticalPadding
                        ),
                        end = Offset(
                            minTPoints[i].x,
                            chartHeight.dp.toPx() - dashVerticalPadding
                        )
                    )

                    if (i == minTPoints.size - 1) {
                        curveBackgroundPath.lineTo(
                            minTPoints[i].x,
                            chartHeight.dp.toPx()
                        )
                        curveBackgroundPath.lineTo(
                            minTPoints.first().x,
                            chartHeight.dp.toPx()
                        )
                    }

                    with(weatherValues.getOrNull(i - 1)) {
                        if (this != null) {
                            val paint = Paint().apply {
                                typeface = font
                                textAlign = Paint.Align.CENTER
                                textSize = 54f
                                color = curveColorMin.toArgb()
                            }
                            drawIntoCanvas { canvas ->
                                canvas.nativeCanvas.drawText(
                                    temperature.minTemperature.toString() + "°",
                                    minTPoints[i].x,
                                    minTPoints[i].y + 60f,
                                    paint
                                )
                            }
                        }
                    }
                }
                drawPath(curvePathMin, curveColorMin, style = Stroke(width = 4.dp.toPx()))
            }
        }
    )
}

@Preview
@Composable
fun HourlyWeatherCurvePreview() {
    val weatherPerHour = mutableListOf<WeatherForecastBO>()
    (1..3).forEach { _ ->
        weatherPerHour.add(
            WeatherForecastBO(
                day = DayOfWeek.MONDAY,
                hour = Hour.TWELVE_AM,
                status = WeatherStatus.RAIN,
                temperature = TemperaturePairBO(
                    minTemperature = (-1..10).random(),
                    maxTemperature = (12..30).random()
                )
            )
        )
    }

    TemperatureGraph(
        weatherPerHour,
        maxTemperaturePoints = computeTemperaturePairCurvePoints(weatherPerHour, true),
        minTemperaturePoints = computeTemperaturePairCurvePoints(weatherPerHour, false),
        weatherBackground = WeatherBackground.DAY
    )
}
