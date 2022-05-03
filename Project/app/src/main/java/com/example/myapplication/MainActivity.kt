package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.fragments.CalendarFragment
import com.example.myapplication.fragments.TasksFragment
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var toggle : ActionBarDrawerToggle // for the Navigation Bar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // for the Bottom Navigation Bar
        replaceFragment(tasksFragment)
        val bottomNavigation : NavigationBarView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.show_calendar -> replaceFragment(calendarFragment)
                R.id.show_tasks -> replaceFragment(tasksFragment)
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
                R.id.nav_work -> Toast.makeText(applicationContext, "Work Tasks", Toast.LENGTH_SHORT).show()
                R.id.nav_school -> Toast.makeText(applicationContext, "School Tasks", Toast.LENGTH_SHORT).show()
                R.id.nav_personal -> Toast.makeText(applicationContext, "Personal Tasks", Toast.LENGTH_SHORT).show()
            }
            true
        }
        // for the Navigation Bar
    }

    // for the Bottom Navigation Bar
    private val tasksFragment = TasksFragment()
    private val calendarFragment = CalendarFragment()

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