package com.example.lojong.presentation.screen_object

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.example.lojong.R
import com.example.lojong.presentation.fundamentals.FundamentalsView

class Boat(fundamentalsView: FundamentalsView, context: Context) : Screen(fundamentalsView, context) {

    private var globalDrawable: Drawable? = null

    init {
        globalDrawable = AppCompatResources.getDrawable(context, R.drawable.ic_boat)
        width = (0.12 * screenWidth).toInt()
        height = (0.76 * width).toInt()
        x += (0.28 * screenWidth).toInt()
        y = - canvasHeight + (1.02 * screenHeight).toInt() - topBarHeight - bottomBarHeight
    }

    override fun draw(canvas: Canvas) {
        dst.set(x, y, x + width, y + height)
        globalDrawable?.bounds = dst
        globalDrawable?.draw(canvas)
    }

}