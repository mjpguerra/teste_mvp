package com.example.lojong.presentation.screen_object

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.example.lojong.R
import com.example.lojong.presentation.fundamentals.FundamentalsView

class Elements(fundamentalsView: FundamentalsView, context: Context) :
    Screen(fundamentalsView, context) {

    private var globalDrawable: Drawable? = null

    init {
        globalDrawable = AppCompatResources.getDrawable(context, R.drawable.ic_elementos_floresta)
        width = screenWidth
        height = canvasHeight
        y = - (1.06 * height).toInt() + screenHeight - topBarHeight- bottomBarHeight
        x += (0.01 * screenWidth).toInt()
    }

    override fun draw(canvas: Canvas) {
        dst.set(x, y, x + width, y + height)
        globalDrawable?.bounds = dst
        globalDrawable?.draw(canvas)
    }

}