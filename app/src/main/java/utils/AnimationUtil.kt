package utils

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.vishinx.thewood.R

object AnimationUtil {

    private lateinit var scaleUp: Animation
    private lateinit var scaleDown: Animation

    fun initialize(context: Context) {
        scaleUp = AnimationUtils.loadAnimation(context, R.anim.scale_up)
        scaleDown = AnimationUtils.loadAnimation(context, R.anim.scale_down)
    }

    fun setScaleAnimation(view: View) {
        view.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.startAnimation(scaleDown)
                    v.isPressed = true
                }
                MotionEvent.ACTION_UP -> {
                    v.startAnimation(scaleUp)
                    v.performClick()
                    v.isPressed = false
                }
                MotionEvent.ACTION_CANCEL -> {
                    v.startAnimation(scaleUp)
                    v.isPressed = false
                }
            }
            true
        }
    }
}
