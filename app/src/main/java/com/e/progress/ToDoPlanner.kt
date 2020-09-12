package com.e.progress

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.*
import androidx.core.view.get

class ToDoPlanner : AppCompatActivity() {
    lateinit var toDoText: EditText
    lateinit var addButton: Button
    lateinit var deleteButton: Button
    lateinit var clearButton: Button
    lateinit var toDoListView: ListView


    private lateinit var preferences: SharedPreferences
    private val APP_PREFRENCES_TASKS = "tasks"
    private var itemList = arrayListOf<String>()
    var count: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_planner)

        toDoListView = findViewById(R.id.taskList_lv)


        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, itemList)
        toDoListView.adapter = arrayAdapter

        preferences = getSharedPreferences("tasks", Context.MODE_PRIVATE)


        toDoText = findViewById(R.id.task_et)

        addButton = findViewById(R.id.add_btn)
        addButton.setOnClickListener{
            itemList.add(toDoText.text.toString())
            toDoListView.adapter = arrayAdapter
            arrayAdapter.notifyDataSetChanged()

            toDoText.text.clear()
        }

        deleteButton = findViewById(R.id.del_btn)
        deleteButton.setOnClickListener{
            val position: SparseBooleanArray = toDoListView.checkedItemPositions
            val count = toDoListView.count
            var item = count - 1

            while (item >= 0){
                if(position.get(item)){
                    arrayAdapter.remove(toDoListView.get(item).toString())
                }
                item--
            }
            position.clear()
            arrayAdapter.notifyDataSetChanged()
        }

        clearButton = findViewById(R.id.clear_btn)
        clearButton.setOnClickListener{
            itemList.clear()
            arrayAdapter.notifyDataSetChanged()
        }
    }

    override fun onPause() {
        super.onPause()

        val editor = preferences.edit()
        editor.putString(APP_PREFRENCES_TASKS, itemList.toString()).apply()
    }

    override fun onResume() {
        super.onResume()

        preferences.contains(APP_PREFRENCES_TASKS)
    }

}


