package com.example.lojong.presentation.screen_object

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.util.TypedValue
import com.example.lojong.presentation.fundamentals.FundamentalsView


abstract class Screen(protected var view: FundamentalsView, context: Context) {

    protected var bitmap: Bitmap? = null

    protected var src: Rect = Rect()
    protected var dst: Rect = Rect()

    protected var col: Byte = 0
    protected var row: Byte = 0

    var height: Int = 0
    var width: Int = 0

    var x: Int = 0
    var y: Int = 0
    var angle: Float = 0F

    //Height
    var screenHeight: Int = 0
    var canvasHeight: Int = 0
    var toolbarHeight: Int = 0
    var statusBarHeight: Int = 0
    var topBarHeight: Int = 0
    var bottomBarHeight: Int = 0

    var density: Float = 0F
    var screenWidth: Int = 0

    init {
        screenHeight = context.resources.displayMetrics.heightPixels
        screenWidth = context.resources.displayMetrics.widthPixels
        density = context.resources.displayMetrics.density
        getToolbarHeight(context)
        getStatusBarHeight(context)
        getBottomBarHeight(context)
        topBarHeight = toolbarHeight + statusBarHeight
        canvasHeight = ((11.5 * screenWidth).toInt() - topBarHeight) - bottomBarHeight
    }

    private fun getToolbarHeight(context: Context) {
        val tv = TypedValue()
        if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true))
            toolbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
    }

    private fun getBottomBarHeight(context: Context) {
        val resId = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resId > 0) {
            bottomBarHeight = context.resources.getDimensionPixelSize(resId)
        }
    }

    private fun getStatusBarHeight(context: Context) {
        val resId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resId > 0) {
            statusBarHeight = context.resources.getDimensionPixelSize(resId)
        }
    }

    open fun move(scrollY: Int) {
        val moveValue = (20 * density).toInt()
        y += if(scrollY > 0 ) moveValue else - moveValue
    }

    open fun rotate(angle: Float) {
        this.angle = angle
    }

    open fun draw(canvas: Canvas) {
        src.set(col * width, row * height, (col + 1) * width, (row + 1) * height)
        dst.set(x, y, x + width, y + height)
        canvas.drawBitmap(bitmap!!, src, dst, null)
        canvas.rotate(angle)
    }



}