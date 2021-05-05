package com.pahomovichk.remindMeDate

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    companion object {
        fun newInstance() =
            MainActivity()
    }

    private lateinit var addBtn: FloatingActionButton
    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.background = null
        navView.menu.getItem(2).isEnabled = false
        addBtn = findViewById(R.id.add_btn)
        toolBar = findViewById(R.id.main_activity_toolbar)
        setSupportActionBar(toolBar)

        addBtn.setOnClickListener {
            val intent = Intent(this.baseContext, AddActivity::class.java)
            startActivity(intent)
//            val intent = Intent(this.baseContext, ItemActivity::class.java)
//            startActivity(intent)
        }

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.events_navigation,
                R.id.birthdays_navigation,
                R.id.calendar_navigation,
                R.id.settings_navigation
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }
}