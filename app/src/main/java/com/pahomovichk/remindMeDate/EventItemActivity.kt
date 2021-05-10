package com.pahomovichk.remindMeDate

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.pahomovichk.remindMeDate.entity.Event
import com.pahomovichk.remindMeDate.presentation.adapter.EventClickListener
import com.pahomovichk.remindMeDate.presentation.viewModel.EventsViewModel

class EventItemActivity: AppCompatActivity() {
    private lateinit var toolBar: Toolbar
    private lateinit var viewModel: EventsViewModel

    private var eventId: Long = 0L
    private lateinit var eventName : String
    private lateinit var eventDate : String
    private lateinit var eventComments : String

    private lateinit var eventCardData : TextView
    private lateinit var eventCommentsData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_activity)
        setActivityContent()
        viewModel = ViewModelProvider(this).get(EventsViewModel::class.java)
        eventCardData = findViewById(R.id.date_card_data)
        eventCommentsData = findViewById(R.id.date_comment_data)

        eventId = intent.getLongExtra(Constants.EVENT_ID, 0L)
        eventName = intent.getStringExtra(Constants.EVENT_NAME) ?: ""
        eventDate = intent.getStringExtra(Constants.EVENT_DATE) ?: ""
        eventComments = intent.getStringExtra(Constants.EVENT_COMMENT) ?: ""

        toolBar = findViewById(R.id.item_activity_toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.apply {
            titleColor = resources.getColor(R.color.white)
            title = eventName
        }

        toolBar.setNavigationOnClickListener {
            val intent = Intent(this.baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        eventCardData.text = eventDate
        eventCommentsData.text = eventComments
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.item_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_bar_delete -> {
                viewModel.onItemSelected(eventId)
                val intent = Intent(this.baseContext, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.item_bar_edit -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActivityContent(){
        findViewById<TextView>(R.id.date_card_label).setText("Event date")
        findViewById<ImageView>(R.id.date_card_icon).setImageResource(R.drawable.ic_notifications)
    }

}