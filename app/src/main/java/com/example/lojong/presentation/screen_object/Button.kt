package com.example.lojong.presentation.screen_object

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.lojong.R
import com.example.lojong.base.enums.ButtonOrientationEnum
import com.example.lojong.base.enums.ButtonStateEnum
import com.example.lojong.presentation.fundamentals.FundamentalsView

class Button(
    fundamentalsView: FundamentalsView,
    context: Context,
    private var buttonStateEnum: ButtonStateEnum,
    private var buttonOrientationEnum: ButtonOrientationEnum,
    day: Int,
    point: PointF
) :
    Screen(fundamentalsView, context) {

    private var globalDrawable: Drawable? = null
    private var currentDay: String = ""
    private var textPaint: Paint = Paint()
    private var textX = 0f
    private var textY = 0f

    init {
        setCurrentStateImage(context)
        setValues(point)
        setDay(context, day)
    }


    private fun setValues(point: PointF) {
        if(buttonOrientationEnum == ButtonOrientationEnum.VERTICAL) {
            width = (0.21 * screenWidth).toInt()
            height = (1.06 * width).toInt()
        } else {
            width = (0.21 * screenWidth).toInt()
            height = (0.98 * width).toInt()
        }
        y = -(point.y * height).toInt() + screenHeight - topBarHeight - bottomBarHeight
        x = (point.x * screenWidth).toInt()

        textX = x + (width / 2f)
        textY = (2.1f * height / 3f)
    }

    private fun setCurrentStateImage(context: Context) {
        changeStateButton(context, buttonStateEnum)
    }

    fun getState(): ButtonStateEnum {
        return buttonStateEnum
    }

    fun changeStateButton(context: Context, buttonStateEnum: ButtonStateEnum) {
        this.buttonStateEnum = buttonStateEnum
        when (this.buttonStateEnum) {
            ButtonStateEnum.FIRST_UNLOCKED -> { globalDrawable = AppCompatResources.getDrawable(
                    context,
                    if (buttonOrientationEnum == ButtonOrientationEnum.VERTICAL)
                        R.drawable.ic_first_button_unlocked
                    else
                        R.drawable.ic_first_button_horizontal_unlocked
                )
            }
            ButtonStateEnum.FIRST_LOCKED -> {
                globalDrawable =
                    AppCompatResources.getDrawable(
                        context,
                        if (buttonOrientationEnum == ButtonOrientationEnum.VERTICAL)
                            R.drawable.ic_first_button_locked
                        else
                            R.drawable.ic_first_button_horizontal_locked
                    )
            }
            ButtonStateEnum.SECOND_UNLOCKED -> {
                globalDrawable =
                AppCompatResources.getDrawable(
                    context,
                    if (buttonOrientationEnum == ButtonOrientationEnum.VERTICAL)
                        R.drawable.ic_secund_button_unlocked
                    else
                        R.drawable.ic_secund_button_horizontal_unlocked
                )
            }
            ButtonStateEnum.SECOND_LOCKED -> {
                globalDrawable =
                AppCompatResources.getDrawable(
                    context,
                    if (buttonOrientationEnum == ButtonOrientationEnum.VERTICAL)
                        R.drawable.ic_secund_buttonl_locked
                    else
                        R.drawable.ic_group_2
                )
            }
        }
    }

    fun setClickListener(xAxis: Int, yAxis: Int, onClickListener: () -> Unit) {
        if (xAxis >= x && xAxis <= x + width && yAxis >= y && yAxis <= y + height)
            onClickListener.invoke()
    }

    private fun setDay(context: Context, day: Int) {
        currentDay = context.getString(R.string.day, day)
        textPaint.textSize = (0.15f * height)
        if(day <= 12)
            textPaint.color = ContextCompat.getColor(context, R.color.brown)
        else
            textPaint.color = ContextCompat.getColor(context, R.color.white)

        textPaint.textAlign = Paint.Align.CENTER
    }

    override fun draw(canvas: Canvas) {
        dst.set(x, y, x + width, y + height)
        globalDrawable?.bounds = dst
        globalDrawable?.draw(canvas)
        canvas.drawText(currentDay, textX, y + textY, textPaint)
    }

}