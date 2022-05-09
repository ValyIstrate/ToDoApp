package com.example.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 * Use the [SchoolTasksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SchoolTasksFragment : Fragment(), TaskClickDeleteInterface, TaskClickUpdateInterface,
    TaskClickInterface {

    private lateinit var tasksRV: RecyclerView
    private lateinit var addFAB: FloatingActionButton
    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasksRV = requireView().findViewById<RecyclerView>(R.id.school_tasksRV)
        tasksRV.layoutManager = LinearLayoutManager(activity)

        val taskRVAdapter = TaskRVAdapter(requireActivity(), this, this, this)
        tasksRV.adapter = taskRVAdapter
        viewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        viewModel.allTasks.observe(requireActivity(), Observer { list ->
            list?.let {
                taskRVAdapter.updateList(it)
            }
        })

        addFAB = requireView().findViewById(R.id.idFABAddTask)
        addFAB.setOnClickListener {
            val intent = Intent(this.requireActivity(), AddTaskActivity::class.java)
            startActivity(intent)
            this.requireActivity().finish()
        }

    }

    override fun onDeleteIconClick(task: Task) {
        viewModel.deleteTask(task)
        Toast.makeText(this.requireActivity(), "${task.taskName} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onUpdateIconClick(task: Task) {
        val textView: TextView = requireView().findViewById(R.id.idTVStatus)

        if(textView.text.toString().equals(("Status: TO DO"))) {
            textView.text = getString(R.string.taskStatus2)
        }
        else if(textView.text.toString().equals(("Status: IN PROGRESS"))) {
            textView.text = getString(R.string.taskStatus3)
        }
        else {
            Toast.makeText(this.requireActivity(), "${task.taskName} is DONE, you should delete it!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onTaskClick(task: Task) {
        val intent = Intent(this.requireActivity(), AddTaskActivity::class.java)
        intent.putExtra("taskType", "Edit")
        intent.putExtra("taskTitle", task.taskName)
        intent.putExtra("taskDescription", task.description)
        intent.putExtra("taskID", task.taskId)
        startActivity(intent)
        this.requireActivity().finish()
    }
}