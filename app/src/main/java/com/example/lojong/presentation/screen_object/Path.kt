package com.example.lojong.presentation.screen_object

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.example.lojong.R
import com.example.lojong.base.enums.PathEnum
import com.example.lojong.presentation.fundamentals.FundamentalsView

class Path(fundamentalsView: FundamentalsView, context: Context, pathEnum: PathEnum) :
    Screen(fundamentalsView, context) {

    private var globalDrawable: Drawable? = null

    init {
        getCurrentPath(context, pathEnum)
    }

    private fun getCurrentPath(context: Context, pathEnum: PathEnum) {
        when (pathEnum) {
            PathEnum.FIRST_PATH -> {
                globalDrawable = AppCompatResources.getDrawable(context, R.drawable.first_path)
                height = canvasHeight / 3
                y = - height + screenHeight - topBarHeight - bottomBarHeight
                width = (0.8 * screenWidth).toInt()
                x += (0.1 * screenWidth).toInt()
            }
            PathEnum.SECOND_PATH -> {
                globalDrawable = AppCompatResources.getDrawable(context, R.drawable.second_path)
                height = (2 * canvasHeight) / 3
                y = - height - (canvasHeight / 3) + screenHeight - topBarHeight - bottomBarHeight
                x += (0.04 * screenWidth).toInt()
                width = screenWidth
            }
        }
    }

    override fun draw(canvas: Canvas) {
        dst.set(x, y, x + width, y + height)
        globalDrawable?.bounds = dst
        globalDrawable?.draw(canvas)
    }

}