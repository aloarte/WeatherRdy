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
package com.example.wethrdy.main.graph

import com.example.wethrdy.data.bo.WeatherForecastBO
import com.example.wethrdy.main.graph.GraphUtils.TemperatureGraphParameters.cellSize
import com.example.wethrdy.main.graph.GraphUtils.TemperatureGraphParameters.heightInterval
import com.example.wethrdy.main.graph.GraphUtils.TemperatureGraphParameters.offsetBottom
import com.example.wethrdy.main.graph.GraphUtils.TemperatureGraphParameters.offsetTop

object GraphUtils {

    object TemperatureGraphParameters {
        const val cellSize = 90
        const val offsetTop = 120
        const val offsetBottom = 10
        const val heightInterval = 260
    }

    fun computeTemperaturePairCurvePoints(
        temperaturePairs: List<WeatherForecastBO>,
        maxTemperature: Boolean
    ): GraphCurvePoints {
        val cellSize = cellSize
        val offsetY = offsetTop
        val chartHeight = heightInterval
        val chartTopPadding = offsetTop
        val curveBottomOffset = offsetBottom
        val minY = temperaturePairs.minOf { it.temperature.minTemperature }
        val maxY = temperaturePairs.maxOf { it.temperature.maxTemperature }
        val heightStep =
            (chartHeight - (chartTopPadding + curveBottomOffset)) / (maxY - minY)

        val points = temperaturePairs.mapIndexed { index, item ->
            GraphPoint(
                (index.toFloat() + 1) * cellSize,
                ((maxY - if (maxTemperature) item.temperature.maxTemperature else item.temperature.minTemperature) * heightStep + offsetY).toFloat()
            )
        }.toMutableList()
        points.add(0, points.first().copy(x = 0f))
        val lastPoint = points.last()
        points.add(lastPoint.copy(x = lastPoint.x + cellSize))

        return computeConnectionPoints(points)
    }

    private fun computeConnectionPoints(points: MutableList<GraphPoint>): GraphCurvePoints {
        val connectionPoints1 = mutableListOf<GraphPoint>()
        val connectionPoints2 = mutableListOf<GraphPoint>()

        try {
            for (i in 1 until points.size) {
                connectionPoints1.add(
                    GraphPoint(
                        (points[i].x + points[i - 1].x) / 2,
                        points[i - 1].y
                    )
                )
                connectionPoints2.add(
                    GraphPoint(
                        (points[i].x + points[i - 1].x) / 2,
                        points[i].y
                    )
                )
            }
        } catch (e: Exception) {
        }

        return GraphCurvePoints(
            points,
            connectionPoints1,
            connectionPoints2
        )
    }
}
