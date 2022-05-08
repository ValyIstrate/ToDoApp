package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.fragments.PersonalTasksFragment
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskViewModel

class AddTaskActivity : AppCompatActivity() {

    lateinit var taskTitleEdit: EditText
    lateinit var taskDescriptionEdit: EditText
    lateinit var addBtn: Button
    lateinit var viewModel: TaskViewModel
    var taskID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        taskTitleEdit = findViewById(R.id.idEditTaskTitle)
        taskDescriptionEdit = findViewById(R.id.idEditTaskDesc)
        addBtn = findViewById(R.id.idBtn)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TaskViewModel::class.java)

        val taskType = intent.getStringExtra("taskType")
        if(taskType.equals("Edit")) {
            val taskTitle = intent.getStringExtra("taskTitle")
            val taskDescription = intent.getStringExtra("taskDescription")
            taskID = intent.getIntExtra("taskID", -1)
            addBtn.setText("Update Task")
            taskTitleEdit.setText(taskTitle)
            taskDescriptionEdit.setText(taskDescription)
        }
        else {
            addBtn.setText("Save Note")
        }

        addBtn.setOnClickListener {
            val taskTitle = taskTitleEdit.text.toString()
            val taskDescription= taskDescriptionEdit.text.toString()

            if(taskType.equals("Edit")) {
                if(taskTitle.isNotEmpty() && taskDescription.isNotEmpty()) {
                    val updateTask = Task(taskTitle, "Personal", "Tomorrow", true, false, false, taskDescription)
                    updateTask.taskId = taskID
                    viewModel.updateTask(updateTask)
                    Toast.makeText(this, "Task Updated...", Toast.LENGTH_LONG).show()
                }
            }
            else {
                if(taskTitle.isNotEmpty() && taskDescription.isNotEmpty()) {
                    viewModel.addTask(Task(taskTitle, "Personal", "Tomorrow", true, false, false , taskDescription))
                    Toast.makeText(this, "Task Added...", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}