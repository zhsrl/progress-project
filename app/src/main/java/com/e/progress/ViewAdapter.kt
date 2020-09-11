package com.e.progress

import android.content.Context
import android.view.ContextMenu
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ViewAdapter : BaseAdapter() {

    lateinit var mMockView: TextView
    lateinit var mContext: Context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return mMockView
    }

    override fun getItem(position: Int): Any {
        return mMockView
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return 0
    }
}