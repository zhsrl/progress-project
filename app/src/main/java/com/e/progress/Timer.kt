package com.e.progress

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.Character.valueOf
import java.lang.String.valueOf
import java.util.concurrent.TimeUnit
import javax.xml.namespace.QName.valueOf

class Timer : AppCompatActivity() {
    lateinit var startBookTime: TextView
    lateinit var timeNum: TextView
    private var FORMAT: String = "%02d:%02d:%02d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        var constraintLayout: ConstraintLayout = findViewById(R.id.timer_layout)
        var animationDrawable: AnimationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()


        startBookTime = findViewById(R.id.start_timer_tv)
        startBookTime.setOnClickListener(){
            startTime()
        }

    }

    //START TIMER LOGIC
    fun startTime(){
        var timer: CountDownTimer? = null

        timeNum = findViewById(R.id.time_count)

        timer = object : CountDownTimer(1810000, 1000){
            override fun onFinish() {
                timeNum.setText("Аяқталды")
            }

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                timeNum.setText("" + String.format(FORMAT,
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))))

            }


        }
        timer.start()
    }
}