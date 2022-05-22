package com.ignation.thexeffect.components

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas

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
        color = Color.Gray,
        style = Stroke(width = size.width / 12f),
        radius = size.width / 2.5f,
    )
}

fun DrawScope.drawDateLabel(dateText: String) {
    drawContext.canvas.nativeCanvas.apply {
        drawText(
            dateText,
            size.width / 2,
            size.height / 2,
            Paint().apply {
                isAntiAlias = true
                textSize = 40f
                color = (0xFF0e00a6).toInt()
                textAlign = Paint.Align.LEFT
                typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
            }
        )
    }
}