package com.example.wethrdy.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wethrdy.ui.theme.teal200

@Composable
fun ForecastSurface(content: @Composable () -> Unit) {
    Surface(
        border = BorderStroke(1.dp, teal200),
        contentColor = Color.Black,
        color = teal200,
        shape = MaterialTheme.shapes.medium
    ) {
        content()
    }
}