package com.example.lojong.presentation.screen_object

import android.content.Context
import android.graphics.Canvas
import com.example.lojong.presentation.fundamentals.FundamentalsView
import androidx.core.content.res.ResourcesCompat
import com.example.lojong.R

class Background(view: FundamentalsView, context: Context) : Screen(view, context) {

    private var backgroundColor = 0

    init {
        width = screenWidth
        height = (canvasHeight - topBarHeight) - bottomBarHeight
        y = - height + screenHeight

        backgroundColor = ResourcesCompat.getColor(context.resources, R.color.fundamentals_background, null)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawColor(backgroundColor)
    }
}