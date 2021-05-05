package com.pahomovichk.remindMeDate

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class ItemActivity : AppCompatActivity() {

    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_activity)
        toolBar = findViewById(R.id.item_activity_toolbar)
        setSupportActionBar(toolBar)

        toolBar.setNavigationOnClickListener {
            Toast.makeText(this, "Back clicked!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.baseContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.item_toolbar, menu)
        return true
    }

}