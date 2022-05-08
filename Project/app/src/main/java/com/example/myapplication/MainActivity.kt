package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.fragments.*
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskViewModel
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private lateinit var toggle : ActionBarDrawerToggle // for the Navigation Bar
    //var viewModel: TaskViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TaskViewModel::class.java) // for the RV

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_MyApplication)

        setContentView(R.layout.activity_main)

        // for the Bottom Navigation Bar
        replaceFragment(personalTasksFragment)
        val bottomNavigation : NavigationBarView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.show_calendar -> replaceFragment(calendarFragment)
                R.id.show_tasks -> replaceFragment(personalTasksFragment)   // the main task that will be shown after pressing the 'Tasks' button
                                                                    // will be 'Personal tasks'
            }
            true
        }
        // for the Bottom Navigation Bar

        // for the Navigation Bar
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.navView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {

                // whatever button we press, be it 'Work', 'Personal' or 'School',
                    // we will be sent to the 'Tasks' fragment

                R.id.nav_work -> {
                    Toast.makeText(applicationContext, "Work Tasks", Toast.LENGTH_SHORT).show()
                    replaceFragment(workTasksFragment)
                }
                R.id.nav_school -> {
                    Toast.makeText(applicationContext, "School Tasks", Toast.LENGTH_SHORT).show()
                    replaceFragment(schoolTasksFragment)
                }
                R.id.nav_personal -> {
                    Toast.makeText(applicationContext, "Personal Tasks", Toast.LENGTH_SHORT).show()
                    replaceFragment(personalTasksFragment)
                }
            }
            true
        }
        // for the Navigation Bars
    }

    // for the buttons that will open the different types of tasks
    private val personalTasksFragment = PersonalTasksFragment()
    private val calendarFragment = CalendarFragment()
    private val workTasksFragment = WorkTasksFragment()
    private val schoolTasksFragment = SchoolTasksFragment()

    private fun replaceFragment(fragment: Fragment) {
        if(fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    // for the Navigation Bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
    // for the Navigation Bar
}