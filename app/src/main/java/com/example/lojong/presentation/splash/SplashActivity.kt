package com.example.lojong.presentation.splash

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.lojong.R
import com.example.lojong.presentation.main.MainActivity
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_splash.*

import java.util.concurrent.TimeUnit

const val SPLASH_TIME_IN_MILLIS: Long = 4000

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        startAnimation(elephant1, 350)
        startAnimation(elephant2, 549)
        startAnimation(elephant3, 150)
        startAnimation(elephant4, 240)
        startPopcornAnimation()
        startApp()
    }

    /**
     * Animate any view with infinite fade-in animation
     */
    private fun startAnimation(view: View, duration: Long) {
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        anim.duration = duration
        view.startAnimation(anim)
    }

    private fun startPopcornAnimation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in_zoom)
        logo_name.startAnimation(anim)
    }

    private fun startApp() = Single.timer(SPLASH_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
        .map { openMain() }
        .subscribe()

    private fun openMain(){
        startActivity(MainActivity.intent(this))
    }
}
