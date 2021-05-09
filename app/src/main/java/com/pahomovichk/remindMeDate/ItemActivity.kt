package com.pahomovichk.remindMeDate

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pahomovichk.remindMeDate.entity.Birthday
import com.pahomovichk.remindMeDate.presentation.adapter.ItemClickListener
import com.pahomovichk.remindMeDate.presentation.viewModel.BirthdaysViewModel
import java.time.LocalDate
import java.time.LocalTime

class ItemActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var toolBar: Toolbar
    private lateinit var viewModel: BirthdaysViewModel

    private lateinit var birth_name : String
    private lateinit var birth_time : String
    private lateinit var birth_date : String

    private lateinit var birthCardData : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_activity)
        viewModel = ViewModelProvider(this).get(BirthdaysViewModel::class.java)
        birthCardData = findViewById(R.id.date_card_data)

        birth_name = intent.getStringExtra(Constants.BIRTHDAY_NAME) ?: ""
        birth_time = intent.getStringExtra(Constants.BIRTHDAY_TIME) ?: ""
        birth_date = intent.getStringExtra(Constants.BIRTHDAY_DATE) ?: ""

        toolBar = findViewById(R.id.item_activity_toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.apply {
            titleColor = resources.getColor(R.color.white)
            title = birth_name
        }

        toolBar.setNavigationOnClickListener {
            Toast.makeText(this, "Back clicked!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        birthCardData.text = birth_date
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.item_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.item_bar_delete -> {
                //onClick()
                Toast.makeText(this, "Delete is clicked!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.item_bar_edit -> {
                Toast.makeText(this, "Edit is clicked!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(birthday: Birthday) {
        viewModel.onItemSelected(birthday)
    }

}