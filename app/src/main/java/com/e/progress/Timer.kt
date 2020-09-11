package com.e.progress

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.Character.valueOf
import java.lang.String.valueOf
import java.util.concurrent.TimeUnit
import javax.xml.namespace.QName.valueOf

class Timer : AppCompatActivity() {
    lateinit var startBookTime: Button
    lateinit var stopBookTime: Button
    lateinit var pauseBookTime: Button
    lateinit var resumeBookTime: Button
    lateinit var timeNum: TextView
    private var FORMAT: String = "%02d:%02d:%02d"


    var isPaused = false
    var isCanceled = false
    var resumeFromMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val millisInFuture: Long = 1800000
        val countDownInterval: Long = 1000


        var constraintLayout: ConstraintLayout = findViewById(R.id.timer_layout)
        var animationDrawable: AnimationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        timeNum = findViewById(R.id.time_count)
        startBookTime = findViewById(R.id.start_timer_tv)
        startBookTime.setOnClickListener(){
            timer(millisInFuture, countDownInterval).start()
            it.isEnabled = false
            stopBookTime.isEnabled = true
            pauseBookTime.isEnabled = true
        }

        stopBookTime = findViewById(R.id.stop_timer_tv)
        stopBookTime.setOnClickListener{
            isCanceled = true
            isPaused = false

            it.isEnabled = false
            startBookTime.isEnabled = true
            pauseBookTime.isEnabled = false


        }

        pauseBookTime = findViewById(R.id.pause_timer_tv)
        pauseBookTime.setOnClickListener{
            isPaused = true
            isCanceled = false

            it.isEnabled = false
            startBookTime.isEnabled = false
            stopBookTime.isEnabled = false
            pauseBookTime.isEnabled = true

            resumeBookTime.visibility = View.VISIBLE
            pauseBookTime.visibility = View.INVISIBLE

        }

        resumeBookTime = findViewById(R.id.resume_timer_tv)
        resumeBookTime.setOnClickListener{
            timer(resumeFromMillis, countDownInterval).start()

            isPaused = false
            isCanceled = false

            it.isEnabled = false
            startBookTime.isEnabled = false
            stopBookTime.isEnabled = true
            pauseBookTime.isEnabled = true

            pauseBookTime.visibility = View.VISIBLE
            resumeBookTime.visibility = View.INVISIBLE
        }


    }


    private fun timer(millisInFuture: Long, countDownInterval: Long): CountDownTimer {
        return object: CountDownTimer(millisInFuture, countDownInterval){
            override fun onFinish() {
                var notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                var mediaPlayer: MediaPlayer = MediaPlayer.create(applicationContext, notification)
                mediaPlayer.start()
                timeNum.setText("АЯҚТАЛДЫ")

            }

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val timeRemaining = "" + String.format(FORMAT,
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))

                if(isPaused){
                    timeNum.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))))
                    resumeFromMillis = millisUntilFinished
                    cancel()



                }

                else if(isCanceled){
                    timeNum.setText("АЯҚТАЛДЫ")
                    cancel()
                }
                else{
                    timeNum.text = timeRemaining
                }
            }

        }
    }
}