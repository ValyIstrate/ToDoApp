package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.fragments.PersonalTasksFragment
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskViewModel
import java.sql.Time
import java.time.Clock
import java.util.*
import kotlin.text.StringBuilder

class AddTaskActivity : AppCompatActivity() {

    lateinit var taskTitleEdit: EditText
    lateinit var taskDescriptionEdit: EditText
    lateinit var addBtn: Button
    lateinit var viewModel: TaskViewModel
    lateinit var pickDate: TextView
    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var pickTime: TextView
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

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
            addBtn.setText("Save Task")
        }

        // for the deadline
        var dueDate = "ASAP"
        pickDate = findViewById(R.id.idSetDate)
        pickDate.setOnClickListener(View.OnClickListener { view ->
            val calendar: Calendar = Calendar.getInstance()
            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

            val dialog: DatePickerDialog = DatePickerDialog(this,
                                                            android.R.style.Theme_Material_Wallpaper,
                                                            dateSetListener, year, month, day)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.GRAY))
            dialog.show()
        })

        dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month_of_year, day_of_month ->
            val builder = StringBuilder()
            builder.append(month_of_year)
                .append("/")
                .append(day_of_month)
                .append("/")
                .append(year)
            pickDate.setText(builder)
            dueDate = builder.toString()
        }

        var dueTime = "Any Time"
        pickTime = findViewById(R.id.idSetTime)
        pickTime.setOnClickListener {
            val timePicker: TimePickerDialog = TimePickerDialog(this, timeSetListener, 12, 10, true)
            timePicker.show()
        }

        timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
            val builder2 = StringBuilder()
            builder2.append(i)
            builder2.append(":")
            builder2.append(i2)
            pickTime.setText(builder2)
            dueTime = builder2.toString()
        }
        // for the deadline

        addBtn.setOnClickListener {

            // For choosing the task type
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
            // We gave taskTypeSelect the value we choose from the radio group

            if(taskType.equals("Edit")) {
                if(taskTitle.isNotEmpty() && taskDescription.isNotEmpty()) {
                    val updateTask = Task(taskTitle, taskTypeSelect, taskStatus, taskDescription, dueDate + " at " + dueTime)
                    updateTask.taskId = taskID
                    viewModel.updateTask(updateTask)
                    Toast.makeText(this, "Task Updated...", Toast.LENGTH_LONG).show()
                }
            }
            else {
                if(taskTitle.isNotEmpty() && taskDescription.isNotEmpty()) {
                    viewModel.addTask(Task(taskTitle, taskTypeSelect, taskStatus, taskDescription, dueDate + " at " + dueTime))
                    Toast.makeText(this, "Task Added...", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}