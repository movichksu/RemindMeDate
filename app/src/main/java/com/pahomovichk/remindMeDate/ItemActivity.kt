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
import com.pahomovichk.remindMeDate.entity.Birthday
import com.pahomovichk.remindMeDate.presentation.adapter.BirthdayClickListener
import com.pahomovichk.remindMeDate.presentation.viewModel.BirthdaysViewModel

class ItemActivity : AppCompatActivity(), BirthdayClickListener {

    private lateinit var toolBar: Toolbar
    private lateinit var viewModel: BirthdaysViewModel

    private lateinit var birth_name : String
    private lateinit var birth_date : String
    private lateinit var birth_comments : String

    private lateinit var birthCardData : TextView
    private lateinit var birthCommentsData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_activity)
        viewModel = ViewModelProvider(this).get(BirthdaysViewModel::class.java)
        birthCardData = findViewById(R.id.date_card_data)
        birthCommentsData = findViewById(R.id.date_comment_data)

        birth_name = intent.getStringExtra(Constants.BIRTHDAY_NAME) ?: ""
        birth_date = intent.getStringExtra(Constants.BIRTHDAY_DATE) ?: ""
        birth_comments = intent.getStringExtra(Constants.BIRTHDAY_COMMENT) ?: ""

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
        birthCommentsData.text = birth_comments
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