package com.example.lojong.presentation.screen_object

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.example.lojong.R
import com.example.lojong.presentation.fundamentals.FundamentalsView

class Tree(fundamentalsView: FundamentalsView, context: Context) : Screen(fundamentalsView, context) {

    private var globalDrawable: Drawable? = null

    init {
        globalDrawable = AppCompatResources.getDrawable(context, R.drawable.ic_tree)
        width = (0.28 * screenWidth).toInt()
        height = (1.2 * width).toInt()
        x += (0.15 * screenWidth).toInt()
        y = - (1.48 * height).toInt() + screenHeight - topBarHeight- bottomBarHeight
    }

    override fun draw(canvas: Canvas) {
        dst.set(x, y, x + width, y + height)
        globalDrawable?.bounds = dst
        globalDrawable?.draw(canvas)
    }

}