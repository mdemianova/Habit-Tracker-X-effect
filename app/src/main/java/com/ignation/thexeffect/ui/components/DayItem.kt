package com.ignation.thexeffect.ui.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.DayStatus
import com.ignation.thexeffect.domain.models.statusChange
import com.ignation.thexeffect.ui.theme.BoxStandard

const val DAY_COMPONENT_SIZE = 34

@Composable
fun DayItem(
    day: Day,
    modifier: Modifier = Modifier
) {
    Box(
        modifier.clickable(enabled = day.status != DayStatus.UNAVAILABLE) {
            day.statusChange()
            Log.d("CustomComponent", "Day status: ${day.status}")
        }
    ) {
        Canvas(
            modifier = Modifier
                .size(DAY_COMPONENT_SIZE.dp)
        ) {
            when (day.status) {
                DayStatus.COMPLETED -> {
                    drawBox()
                    drawX()
                }
                DayStatus.MISSED -> {
                    drawBox()
                    drawO()
                }
                else -> drawBox()
            }
        }
    }
}

fun DrawScope.drawBox(
    componentSize: Size = size,
    componentColor: Color = BoxStandard,
    componentStrokeWidth: Float = size.width / 13f
) {
    val stroke = Stroke(componentStrokeWidth)
    drawRect(
        size = componentSize,
        color = componentColor,
        style = stroke
    )
}

fun DrawScope.drawX(
    componentColor: Color = Color.Black
) {
    val width = size.width
    val height = size.height
    val pathOne = Path().apply {
        moveTo(width.times(0.1f), height.times(0.9f))
        quadraticBezierTo(
            width.times(0.3f),
            height.times(0.5f),
            width.times(0.9f),
            height.times(0.1f)
        )
        quadraticBezierTo(
            width.times(0.6f),
            height.times(0.5f),
            width.times(0.1f),
            height.times(0.9f)
        )
        close()
    }
    drawPath(
        pathOne,
        color = componentColor,
        style = Stroke(width = 2f, cap = StrokeCap.Butt),
    )
    drawPath(
        pathOne,
        color = componentColor,
        style = Fill
    )

    val pathTwo = Path().apply {
        moveTo(width.times(0.1f), height.times(0.1f))
        quadraticBezierTo(
            width.times(0.2f),
            height.times(0.5f),
            width.times(0.9f),
            height.times(0.9f)
        )
        quadraticBezierTo(
            width.times(0.45f),
            height.times(0.5f),
            width.times(0.1f),
            height.times(0.1f)
        )
        close()
    }
    drawPath(
        pathTwo,
        color = componentColor,
        style = Stroke(width = 2f, cap = StrokeCap.Butt),
    )
    drawPath(
        pathTwo,
        color = componentColor,
        style = Fill
    )
}

fun DrawScope.drawO() {
    drawCircle(
        color = Color.Black,
        style = Stroke(width = size.width / 10f),
        radius = size.width / 2.5f,
    )
}