package com.e.progress.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.e.progress.R

class CounterFragment: Fragment() {

    companion object{
        fun newInstance() = CounterFragment()
    }

    lateinit var numCount: TextView
    var num = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.counter_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numCount = view?.findViewById(R.id.num_count_tv)

        numCount.setOnClickListener{

            num++

            numCount.setText(num.toString())

        }

    }
}