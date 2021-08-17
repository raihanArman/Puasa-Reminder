package com.example.puasareminder.util

import android.R.attr
import android.graphics.Canvas
import android.graphics.Paint
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import android.R.attr.bottom

import android.R.attr.right

import android.R.attr.left




class MyCustomSpan(
    val radius: Float,
    val color: Int
): DotSpan() {
    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        charSequence: CharSequence,
        start: Int,
        end: Int,
        lineNum: Int
    ) {
        super.drawBackground(
            canvas,
            paint,
            left,
            right,
            top,
            baseline,
            bottom,
            charSequence,
            start,
            end,
            lineNum
        )
        val oldColor = paint.color
        if (color != 0) {
            paint.color = color
        }

        canvas.drawCircle(
            ((attr.left + attr.right) / 2 - 5).toFloat(),
            attr.bottom + radius,
            radius,
            paint
        )
        paint.color = oldColor
    }
}