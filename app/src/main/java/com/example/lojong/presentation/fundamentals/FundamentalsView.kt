package com.example.lojong.presentation.fundamentals

import android.content.ContentValues.TAG
import android.os.Build
import android.view.MotionEvent
import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import com.example.lojong.base.enums.ButtonOrientationEnum
import com.example.lojong.base.enums.PathEnum
import com.example.lojong.base.enums.ButtonStateEnum
import com.example.lojong.base.enums.ElephantOrientationEnum
import com.example.lojong.presentation.screen_object.*

class FundamentalsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback, View.OnTouchListener {

    //private var character: Elephant
    private var elephants: Elephants
    private var pathTwo: Path
    private var elements: Elements
    private var boat : Boat
    private var tree : Tree
    private var background: Background
    private var topBackground: TopBackground
    private var waterfall: Waterfall
    private var bridge: Bridge
    private var pathOne: Path

    private var buttonsHorizontalPositions = listOf(3, 5, 7, 11, 18, 25, 29)
    private var buttons: MutableList<Button> = mutableListOf()
    private var characters: MutableList<Elephant> = mutableListOf()
    private var currentPosition = 0
    private var clickedPoint = Point(0, 0)

    private var canvas: Canvas? = null
    private var initialClick = Point(0, 0)

    init {
        isFocusable = true
        setOnTouchListener(this)
        holder.addCallback(this)

        background = Background(this, context)
        elements= Elements(this, context)
        tree = Tree(this, context)
        //character = Elephant(this, context, elephantPositions[currentPosition], ElephantOrientationEnum.LEFT)
        pathOne = Path(this, context, PathEnum.FIRST_PATH)

        bridge = Bridge(this, context)
        pathTwo = Path(this, context, PathEnum.SECOND_PATH)
        topBackground = TopBackground(this, context)
        waterfall = Waterfall(this, context)
        elephants = Elephants(this, context)
        boat = Boat(this, context)

        buttonPositions().forEachIndexed { index, pointF ->
            val currentDay = index + 1
            buttons.add(
                Button(
                    this,
                    context,
                    getCurrentButtonStateEnum(index),
                    getButtonOrientation(currentDay),
                    currentDay,
                    pointF
                )
            )
        }

        elephantPositions().forEachIndexed { index, pointF ->
            val currentElephant = index + 1
            characters.add(
                Elephant(this, context, pointF, ElephantOrientationEnum.LEFT)
            )
        }

    }

    fun elephantPositions() : List<PointF> {
        return listOf(
            PointF(0.63f, 7.65f),
            PointF(0.63f, 7.41f),
            PointF(0.6f, 7.19f),
            PointF(0.15f, 7.15f),
            PointF(0.25f, 6.865f),
            PointF(0.68f, 6.865f)
        )
    }

    fun buttonPositions() : List<PointF>{
        return listOf(

            PointF(0.59f, 1.81f),//day 1
            PointF(0.59f, 3.51f),//day 2
            PointF(0.33f, 4.90f),//day 3
            PointF(0.075f, 5.65f),//day 4
            PointF(0.43f, 7.27f),//day 5
            PointF(0.72f, 7.85f),//day 6
            PointF(0.43f, 9.70f),//day 7
            PointF(0.22f, 10.30f),//day 8
            PointF(0.22f, 11.60f),//day 9
            PointF(0.22f, 12.90f),//day 10
            PointF(0.43f, 15.22f),//day 11
            PointF(0.63f, 15.23f),//day 12

            //second path
            PointF(0.197f, 19.50f),//day 13
            PointF(0.197f, 20.90f),//day 14
            PointF(0.52f, 23.30f), //day 15
            PointF(0.092f, 25.50f),//day 16
            PointF(0.092f, 26.90f),//day 17
            PointF(0.36f, 30.51f), //day 18
            PointF(0.57f, 29.3f),  //day 19
            PointF(0.57f, 30.50f), //day 20
            PointF(0.57f, 31.7f),  //day 21
            PointF(0.249f, 33.8f), //day 22
            PointF(0.675f, 36.1f), //day 23
            PointF(0.675f, 37.55f),//day 24
            PointF(0.45f, 42.04f), //day 25
            PointF(0.198f, 40.10f),//day 26
            PointF(0.198f, 41.44f),//day 27
            PointF(0.198f, 42.94f),//day 28
            PointF(0.46f, 48.02f), //day 29
            PointF(0.677f, 45.50f),//day 30
            PointF(0.677f, 46.90f) //day 31
        )
    }

    private fun getButtonOrientation(index: Int): ButtonOrientationEnum {
        return if (buttonsHorizontalPositions.any { it == index })
            ButtonOrientationEnum.HORIZONTAL
        else
            ButtonOrientationEnum.VERTICAL
    }

    private fun getCurrentButtonStateEnum(index: Int): ButtonStateEnum {
        return when {
            index <= 11 -> return if (index <= currentPosition) ButtonStateEnum.FIRST_UNLOCKED
            else ButtonStateEnum.FIRST_LOCKED
            index <= currentPosition -> ButtonStateEnum.SECOND_UNLOCKED
            else -> ButtonStateEnum.SECOND_LOCKED
        }
    }

    private fun goToNextPosition(index: Int) {
        if (index < buttonPositions().size) {
            val nextPosition = index + 1
            buttons[nextPosition].changeStateButton(context, unlockIndexButtonEnum(nextPosition))
            if (nextPosition > currentPosition) {
               // character.moveToPosition(elephantPositions[nextPosition])
            //    character.changeElephantOrientation(context, nextPosition)
                currentPosition = nextPosition
            }
            draw()
        }
    }

    private fun moveScreen(pt: Point) {
        val movingFactor = (pt.y - initialClick.y)
        pathOne.move(movingFactor)
        pathTwo.move(movingFactor)
        boat.move(movingFactor)
        elements.move(movingFactor)
        topBackground.move(movingFactor)
        waterfall.move(movingFactor)
        elephants.move(movingFactor)
        buttons.forEach {
            it.move(movingFactor)
        }
        characters.forEach {
            it.move(movingFactor)
        }
        bridge.move(movingFactor)
        tree.move(movingFactor)
        //character.move(movingFactor)
        if ((pathOne.y + pathOne.height) >= (pathOne.screenHeight - pathOne.topBarHeight) - pathOne.bottomBarHeight && (topBackground.y) <= 0) {
            draw()
        } else {
            pathOne.move(if ((pathTwo.y) >= (pathTwo.topBarHeight)) -1 else 1)
            elements.move(if ((pathTwo.y) >= (pathTwo.topBarHeight)) -1 else 1)
            pathTwo.move(if ((pathTwo.y) >= (pathTwo.topBarHeight)) -1 else 1)
            topBackground.move(if ((topBackground.y) >= 0) -1 else 1)
            waterfall.move(if ((waterfall.y) >= 0) -1 else 1)
            boat.move(if ((boat.y) >= 0) -1 else 1)
            bridge.move(if ((bridge.y) >= 0) -1 else 1)
            tree.move(if ((pathTwo.y) >= (pathTwo.topBarHeight)) -1 else 1)
            elephants.move(if ((elephants.y) >= 0) -1 else 1)
            //character.move(if ((character.y) >= (character.screenHeight - character.topBarHeight) - character.bottomBarHeight) -1 else 1)
            characters.forEach { it.move(if (it.y >= (it.screenHeight - it.topBarHeight) - it.bottomBarHeight) -1 else 1) }
            buttons.forEach { it.move(if (it.y >= (it.screenHeight - it.topBarHeight) - it.bottomBarHeight) -1 else 1) }
        }
    }



    private fun draw() {
        canvas = null
        try {
            synchronized(holder) {
                canvas = getCanvas()
                drawCanvas(canvas!!)
            }
        } catch (ex: Exception) {
            Log.d(TAG, "Error", ex)
        } finally {
            if (canvas != null) {
                holder.surface.unlockCanvasAndPost(canvas)
            }
        }
    }

    private fun getCanvas(): Canvas {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.surface.lockHardwareCanvas()
        } else {
            holder.lockCanvas()
        }
    }

    private fun drawCanvas(canvas: Canvas) {
        background.draw(canvas)
        topBackground.draw(canvas)
        pathOne.draw(canvas)
        pathTwo.draw(canvas)
        waterfall.draw(canvas)
        elephants.draw(canvas)
        bridge.draw(canvas)
        elements.draw(canvas)
        boat.draw(canvas)
        tree.draw(canvas)
       // character.draw(canvas)
        buttons.forEach { it.draw(canvas) }
        characters.forEach { it.draw(canvas) }
    }

    private fun unlockIndexButtonEnum(index: Int): ButtonStateEnum {
        return if (index <= 11) ButtonStateEnum.FIRST_UNLOCKED else ButtonStateEnum.SECOND_UNLOCKED
    }

    private fun isButtonLocked(button: Button): Boolean {
        return button.getState() == ButtonStateEnum.FIRST_LOCKED || button.getState() == ButtonStateEnum.SECOND_LOCKED
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        performClick()
        clickedPoint = Point(event.x.toInt(), event.y.toInt())

        if (event.action == MotionEvent.ACTION_DOWN)
            initialClick = clickedPoint

        buttons.forEachIndexed { index, button ->

            if (isButtonLocked(button))
                return@forEachIndexed

            button.setClickListener(initialClick.x, initialClick.y) {
                goToNextPosition(index)
            }
        }

        if (event.action == MotionEvent.ACTION_MOVE)
            moveScreen(clickedPoint)

        return true
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        draw()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {

    }
}