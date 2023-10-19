package com.example.todolist_fm2

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(
    private val toDos: MutableList<ToDo>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){

    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addToDo(todo: ToDo) {
        toDos.add(todo)
        notifyItemInserted(toDos.size -1 )
    }

    fun deleteDoneToDos() {
        toDos.removeAll { toDo ->
            toDo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvToDoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and  STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun getItemCount(): Int {
        return toDos.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentToDo = toDos[position]
        holder.itemView.apply {
            val tvToDoTitle = findViewById<TextView>(R.id.tvToDoTitle)
            val cbDone = findViewById<CheckBox>(R.id.cbDone)

            tvToDoTitle.text = currentToDo.title
            cbDone.isChecked = currentToDo.isChecked
            toggleStrikeThrough(tvToDoTitle, currentToDo.isChecked)
            cbDone.setOnCheckedChangeListener{ _, isChecked ->
                toggleStrikeThrough(tvToDoTitle, isChecked)
                currentToDo.isChecked= !currentToDo.isChecked
            }
        }
    }


}