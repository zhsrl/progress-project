package com.e.progress.Fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.e.progress.*
import com.e.progress.Timer
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    lateinit var currentTimeShow: TextView
    lateinit var userName: TextView

    lateinit var namazTime: ImageButton
    lateinit var bookTime: ImageButton
    lateinit var qiblaFinder: ImageButton
    lateinit var toDo: ImageButton

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


        // NAMAZ TIME LOGIC
        namazTime = view?.findViewById(R.id.namaz_time_btn) ?: ImageButton(context)

        namazTime.setOnClickListener {
            val intent = Intent(activity, NamazTime::class.java)
            startActivity(intent)
            activity!!.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        //BOOK TIME LOGIC
        bookTime = view?.findViewById(R.id.book_time_btn)

        bookTime.setOnClickListener(){
            val intent = Intent(activity, Timer::class.java)
            startActivity(intent)
            activity!!.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }


        someHandler.postDelayed(object : Runnable {
            override fun run() {
                currentTimeShow.setText(SimpleDateFormat("HH:mm", Locale.US).format(Date()))
                someHandler.postDelayed(this, 1000)
            }
        }, 10)


        //QIBLA FINDER LOGIC
        qiblaFinder = view?.findViewById(R.id.qaaba_finder_btn)

        qiblaFinder.setOnClickListener{
            val intent = Intent(activity, QiblaFind::class.java)
            intent.putExtra("key","QiblaFinder")
            startActivity(intent)
        }

        //PLANNER_TODO LOGIC
        toDo = view?.findViewById(R.id.planner_todo_btn)

        toDo.setOnClickListener{
            val intent = Intent(activity, ToDoPlanner::class.java)
            intent.putExtra("key","toDoPlanner")
            startActivity(intent)
        }

    }




}

