package com.vishinx.thewood

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val playImageButton: ImageButton = findViewById(R.id.play_button)
        setScaleAnimation(playImageButton, this)

        mediaPlayer = MediaPlayer.create(this, R.raw.instrumento)
        mediaPlayer.isLooping = true

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        mediaPlayer.start()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}

fun setScaleAnimation(view: View, context: Context) {
    val scaleUp = AnimationUtils.loadAnimation(context, R.anim.scale_up)
    val scaleDown = AnimationUtils.loadAnimation(context, R.anim.scale_down)

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
