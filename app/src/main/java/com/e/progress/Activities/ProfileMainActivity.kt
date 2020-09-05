package com.e.progress.Activities

import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.e.progress.Fragments.ChatFragment
import com.e.progress.Fragments.CounterFragment
import com.e.progress.Fragments.HomeFragment
import com.e.progress.Fragments.SettingsFragment
import com.e.progress.Pager
import com.e.progress.R
import com.e.progress.ViewPageAdapter
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_profile_main.*
import java.lang.reflect.Type


class ProfileMainActivity : AppCompatActivity() {
    private lateinit var pager: Pager
    private lateinit var pagerAdapter: PagerAdapter
    private var homeFragment: Fragment = HomeFragment()
    private var chatFragment: Fragment = ChatFragment()
    private var counterFragment: Fragment = CounterFragment()
    private var settingsFragment: Fragment = SettingsFragment()

    val fragmentList: MutableList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_main)

        val bottomNavBar: BottomNavigationView = findViewById(R.id.bottom_nav_bar)


        fragmentList.add(homeFragment)
        fragmentList.add(chatFragment)
        fragmentList.add(counterFragment)
        fragmentList.add(settingsFragment)
        pager = findViewById(R.id.pager)
        pager.setSwipable(true)
        pagerAdapter = ViewPageAdapter(supportFragmentManager, fragmentList)

        bottomNavBar.setOnNavigationItemSelectedListener{item ->
            var selectedFragment: Fragment = HomeFragment()

            when(item.itemId){
                R.id.item_home -> selectedFragment = HomeFragment()
                R.id.item_chat -> selectedFragment = ChatFragment()
                R.id.item_counter -> selectedFragment = CounterFragment()
                R.id.item_settings -> selectedFragment = SettingsFragment()
            }

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, selectedFragment)
            transaction.commit()
            return@setOnNavigationItemSelectedListener true

        }

    }


}