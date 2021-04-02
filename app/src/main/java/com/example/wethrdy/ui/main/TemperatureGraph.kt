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

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wethrdy.data.bo.TemperaturePair
import com.example.wethrdy.main.graph.GraphCurvePoints
import com.example.wethrdy.main.graph.GraphUtils.TemperatureGraphParameters.cellSize
import com.example.wethrdy.main.graph.GraphUtils.TemperatureGraphParameters.heightInterval
import com.example.wethrdy.main.graph.GraphUtils.computeDailyWeatherCurvePoints
import com.example.wethrdy.ui.theme.colorMaxTemperature
import com.example.wethrdy.ui.theme.colorMinTemperature

@Composable
fun TemperatureGraph(
    temperaturePairs: List<TemperaturePair>,
    minTemperaturePoints: GraphCurvePoints,
    maxTemperaturePoints: GraphCurvePoints

) {

    val curveColorMax = colorMaxTemperature
    val curveColorMin = colorMinTemperature
    val onBackgroundColor = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
    val chartHeight = heightInterval
    val dpPerCell = cellSize
    val canvasWidth = dpPerCell.dp * (temperaturePairs.size + 1)
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
            if (maxTPoints.isNotEmpty() && connectionMaxTPoints1.isNotEmpty() && connectionMaxTPoints2.isNotEmpty()) {
                val dashIntervals = floatArrayOf(10f, 20f)
                val dashPhase = 25f
                val dashVerticalPadding = 20f

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
                        color = onBackgroundColor,
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

                    val hourWeather = temperaturePairs.getOrNull(i - 1)

                    if (hourWeather != null) {
                        val paint = Paint()
                        paint.textAlign = Paint.Align.CENTER
                        paint.textSize = 54f
                        paint.color = curveColorMax.toArgb()
                        drawIntoCanvas { canvas ->
                            canvas.nativeCanvas.drawText(
                                hourWeather.maxTemperature.toString() + "°",
                                maxTPoints[i].x,
                                maxTPoints[i].y - 50,
                                paint
                            )
                        }
                    }
                }
                drawPath(curvePathMax, curveColorMax, style = Stroke(width = 4.dp.toPx()))

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
                        color = onBackgroundColor,
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

                    val hourWeather = temperaturePairs.getOrNull(i - 1)

                    if (hourWeather != null) {
                        val paint = Paint()
                        paint.textAlign = Paint.Align.CENTER
                        paint.textSize = 54f
                        paint.color = curveColorMin.toArgb()
                        drawIntoCanvas { canvas ->
                            canvas.nativeCanvas.drawText(
                                hourWeather.minTemperature.toString() + "°",
                                minTPoints[i].x,
                                minTPoints[i].y + 50f,
                                paint
                            )
                        }
                    }
                }
                drawPath(curvePathMin, curveColorMin, style = Stroke(width = 4.dp.toPx()))

//                drawPath(curveBackgroundPath, curveBackgroundColor, style = Fill)
            }
        }
    )
}

@Preview
@Composable
fun HourlyWeatherCurvePreview() {
    val weatherPerHour = mutableListOf<TemperaturePair>()
    (1..3).forEach { _ ->
        weatherPerHour.add(
            TemperaturePair(
                minTemperature = (-1..10).random(),
                maxTemperature = (12..30).random()
            )
        )
    }

    TemperatureGraph(
        weatherPerHour,
        maxTemperaturePoints = computeDailyWeatherCurvePoints(weatherPerHour, true),
        minTemperaturePoints = computeDailyWeatherCurvePoints(weatherPerHour, false)
    )
}
