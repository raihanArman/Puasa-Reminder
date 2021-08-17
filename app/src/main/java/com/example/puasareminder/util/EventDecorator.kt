package com.example.puasareminder.util

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import android.content.Context
import android.graphics.Color

import androidx.core.content.ContextCompat

import android.graphics.drawable.Drawable
import android.text.style.ForegroundColorSpan
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import com.example.puasareminder.R

class EventDecorator(
    val drawable: Drawable, val dates: Collection<CalendarDay>
): DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates.contains(day);
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.setBackgroundDrawable(drawable)
        view.addSpan( ForegroundColorSpan(Color.WHITE))
    }
}