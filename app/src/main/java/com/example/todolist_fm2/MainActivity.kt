package com.example.todolist_fm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toDoAdapter = ToDoAdapter(mutableListOf())

        val rvToDoItems = findViewById<RecyclerView>(R.id.rvToDoItems)
        val etToDoTitle = findViewById<EditText>(R.id.etToDoTitle)
        val btnAddToDo = findViewById<Button>(R.id.btnAddToDo)
        val btnDeleteDoneToDos = findViewById<Button>(R.id.btnDeleteDoneToDos)

        rvToDoItems.adapter = toDoAdapter
        rvToDoItems.layoutManager = LinearLayoutManager(this)

        btnAddToDo.setOnClickListener {
            val toDoTitle = etToDoTitle.text.toString()
            if (toDoTitle.isNotEmpty()) {
                val toDo = ToDo(toDoTitle)
                toDoAdapter.addToDo(toDo)
                etToDoTitle.text.clear()
            }
        }
        btnDeleteDoneToDos.setOnClickListener {
            toDoAdapter.deleteDoneToDos()
        }
    }
}