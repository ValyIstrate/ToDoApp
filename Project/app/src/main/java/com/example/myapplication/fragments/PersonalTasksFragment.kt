package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.TaskClickDeleteInterface
import com.example.myapplication.TaskRVAdapter
import com.example.myapplication.model.TaskViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalTasksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonalTasksFragment : Fragment() {

    lateinit var tasksRV: RecyclerView
    lateinit var viewModel: TaskViewModel

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

        //tasksRV = requireView().findViewById<RecyclerView>(R.id.personal_tasksRV)
        //tasksRV.layoutManager = LinearLayoutManager(this)

        //val taskRVAdapter = TaskRVAdapter(this, this, this, this)
    }
}