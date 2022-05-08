package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Task

class TaskRVAdapter(val context: Context,
                    private val taskClickDeleteInterface: TaskClickDeleteInterface,
                    private val taskClickUpdateInterface: TaskClickUpdateInterface,
                    private val taskClickInterface: TaskClickInterface) :
    RecyclerView.Adapter<TaskRVAdapter.ViewHolder>() {

    private val allTasks = ArrayList<Task>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTV: TextView = itemView.findViewById<TextView>(R.id.idTVTaskTitle)
        val timeTV: TextView = itemView.findViewById<TextView>(R.id.idTVTimeStamp)
        val descriptionTV: TextView = itemView.findViewById<TextView>(R.id.idTVDescription)
        val deleteIV: ImageView = itemView.findViewById<ImageView>(R.id.idIVDelete)
        val updateIV: ImageView = itemView.findViewById<ImageView>(R.id.idIVUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskTV.text = allTasks.get(position).taskName
        //holder.timeTV.setText("Due date: " + allTasks.get(position).dueDate)
        holder.descriptionTV.setText(allTasks.get(position).description)

        holder.deleteIV.setOnClickListener {
            taskClickDeleteInterface.onDeleteIconClick(allTasks.get(position))
        }

        holder.updateIV.setOnClickListener {
            taskClickUpdateInterface.onUpdateIconClick(allTasks.get(position))
        }

        holder.itemView.setOnClickListener {
            taskClickInterface.onTaskClick(allTasks.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allTasks.size
    }

    fun updateList(newList: List<Task>) {
        allTasks.clear()
        allTasks.addAll(newList)
        notifyDataSetChanged()
    }
}

interface TaskClickDeleteInterface {
    fun onDeleteIconClick(task: Task)
}

interface TaskClickUpdateInterface {
    fun onUpdateIconClick(task: Task)
}

interface TaskClickInterface {
    fun onTaskClick(task: Task)
}