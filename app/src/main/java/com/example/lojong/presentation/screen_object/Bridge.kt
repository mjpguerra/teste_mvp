package com.example.lojong.presentation.screen_object

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.example.lojong.R
import com.example.lojong.presentation.fundamentals.FundamentalsView

class Bridge(fundamentalsView: FundamentalsView, context: Context) :
    Screen(fundamentalsView, context) {

    private var globalDrawable: Drawable? = null

    init {
        globalDrawable = AppCompatResources.getDrawable(context, R.drawable.ic_bridge)
        width = (0.2 * screenWidth).toInt()
        height = (1.495 * width).toInt()
        x += (0.636 * screenWidth).toInt()
        y = - height - (canvasHeight / 3 - 240) + screenHeight - topBarHeight- bottomBarHeight
    }

    override fun draw(canvas: Canvas) {
        dst.set(x, y, x + width, y + height)
        globalDrawable?.bounds = dst
        globalDrawable?.draw(canvas)
    }

}