package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskViewModel
import android.app.Application

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalTasksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonalTasksFragment : Fragment(), TaskClickDeleteInterface, TaskClickUpdateInterface,
    TaskClickInterface {

    private lateinit var tasksRV: RecyclerView
    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_personal_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // What to do here??
        tasksRV = requireView().findViewById<RecyclerView>(R.id.personal_tasksRV)
        tasksRV.layoutManager = LinearLayoutManager(activity)

        val taskRVAdapter = TaskRVAdapter(requireActivity(), this, this, this)
        tasksRV.adapter = taskRVAdapter
        //viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TaskViewModel::class.java)
    }

    override fun onDeleteIconClick(task: Task) {
        TODO("Not yet implemented")
    }

    override fun onUpdateIconClick(task: Task) {
        TODO("Not yet implemented")
    }

    override fun onTaskClick(task: Task) {
        TODO("Not yet implemented")
    }
}