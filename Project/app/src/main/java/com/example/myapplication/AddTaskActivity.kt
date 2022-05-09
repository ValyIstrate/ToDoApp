package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.fragments.PersonalTasksFragment
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskViewModel
import java.util.*

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


        val radioButtonPers: RadioButton = findViewById(R.id.radioPersonal)
        val radioButtonWork: RadioButton = findViewById(R.id.radioWork)
        val radioButtonSch: RadioButton = findViewById(R.id.radioSchool)



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
            val taskDescription = taskDescriptionEdit.text.toString()
            val taskStatus = "TO DO"

            var taskTypeSelect = "Personal"
            val checkedPers = radioButtonPers.isChecked
            val checkedWork = radioButtonWork.isChecked
            val checkedSch = radioButtonSch.isChecked

           if(checkedPers) {
                taskTypeSelect = "Personal"
            }
            else if (checkedWork) {
                taskTypeSelect = "Work"
            }
            else if(checkedSch) {
                taskTypeSelect = "School"
            }

            if(taskType.equals("Edit")) {
                if(taskTitle.isNotEmpty() && taskDescription.isNotEmpty()) {
                    val updateTask = Task(taskTitle, taskTypeSelect, taskStatus, taskDescription, Calendar.getInstance().time)
                    updateTask.taskId = taskID
                    viewModel.updateTask(updateTask)
                    Toast.makeText(this, "Task Updated...", Toast.LENGTH_LONG).show()
                }
            }
            else {
                if(taskTitle.isNotEmpty() && taskDescription.isNotEmpty()) {
                    viewModel.addTask(Task(taskTitle, taskTypeSelect, taskStatus, taskDescription, Calendar.getInstance().time))
                    Toast.makeText(this, "Task Added...", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}