package com.e.progress.Fragments

import android.content.Intent.getIntent
import android.content.Intent.getIntentOld
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.e.progress.Activities.MainActivity
import com.e.progress.Activities.SignUp
import com.e.progress.R
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    lateinit var currentTimeShow: TextView
    lateinit var userName: TextView

    var someHandler: Handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentTimeShow = view?.findViewById(R.id.current_time_tv) ?: TextView(context)
        userName = view?.findViewById(R.id.user_name_tv) ?: TextView(context)

        var name: String = activity!!.intent.getStringExtra("username")
        userName.setText(name)


        someHandler.postDelayed(object : Runnable {
            override fun run() {
                currentTimeShow.setText(SimpleDateFormat("HH:mm", Locale.US).format(Date()))
                someHandler.postDelayed(this, 1000)
            }
        }, 10)

    }




}

