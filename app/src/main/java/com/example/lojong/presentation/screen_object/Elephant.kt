package com.example.lojong.presentation.screen_object

import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.example.lojong.R
import com.example.lojong.base.enums.ElephantOrientationEnum
import com.example.lojong.presentation.fundamentals.FundamentalsView

class Elephant(
    fundamentalsView: FundamentalsView,
    context: Context,
    private var point: PointF,
    private var elephantOrientationEnum: ElephantOrientationEnum
) : Screen(fundamentalsView, context) {

    var initY = 0
    private var elephantToRightPositions = listOf(4, 5, 8, 9, 10, 11)
    private var globalDrawable: Drawable? = null

    init {
        changeElephantOrientation(context, 0)
        width = (0.1276 * screenWidth).toInt()
        height = (0.7682 * width).toInt()
        setPosition()
    }

    private fun setPosition() {
        //y = -(point.y * height).toInt() + screenHeight - topBarHeight- bottomBarHeight
        y = - canvasHeight + (point.y * screenHeight).toInt() - topBarHeight - bottomBarHeight
        x = (point.x * screenWidth).toInt()
       // initY = y
    }

    fun moveToPosition(point: PointF) {
        //point.y += (y - initY)
        this.point = point
        setPosition()
    }

    fun changeElephantOrientation(context: Context, index: Int) {
        elephantOrientationEnum = getElephantOrientation(index)
        globalDrawable = AppCompatResources.getDrawable(
            context,
            if (elephantOrientationEnum == ElephantOrientationEnum.LEFT)
                R.drawable.ic_elephant_to_left
            else
                R.drawable.ic_elephant_to_right
        )
    }

    private fun getElephantOrientation(index: Int): ElephantOrientationEnum {
        return if (elephantToRightPositions.any { it == index })
            ElephantOrientationEnum.RIGHT
        else
            ElephantOrientationEnum.LEFT
    }

    override fun draw(canvas: Canvas) {
        dst.set(x, y, x + width, y + height)
        globalDrawable?.bounds = dst
        globalDrawable?.draw(canvas)
    }

}