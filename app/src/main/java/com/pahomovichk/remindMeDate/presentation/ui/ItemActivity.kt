package com.pahomovichk.remindMeDate.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.pahomovichk.remindMeDate.Constants
import com.pahomovichk.remindMeDate.EditItemActivity
import com.pahomovichk.remindMeDate.R
import com.pahomovichk.remindMeDate.presentation.viewModel.OnetimeViewModel
import com.pahomovichk.remindMeDate.presentation.viewModel.YearlyViewModel

class ItemActivity: AppCompatActivity() {

    private lateinit var toolBar: Toolbar
    private lateinit var onetimeViewModel: OnetimeViewModel
    private lateinit var yearlyViewModel:YearlyViewModel

    private var eventId = 0L
    private lateinit var eventName: String
    private lateinit var eventType: String
    private lateinit var eventDate: String
    private lateinit var eventComments: String

    private lateinit var eventCardData: TextView
    private lateinit var eventCommentsData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_activity)
        onetimeViewModel = ViewModelProvider(this).get(OnetimeViewModel::class.java)
        yearlyViewModel = ViewModelProvider(this).get(YearlyViewModel::class.java)

        eventCardData = findViewById(R.id.date_card_data)
        eventCommentsData = findViewById(R.id.date_comment_data)

        eventId = intent.getLongExtra(Constants.ID, 0L)
        eventName = intent.getStringExtra(Constants.NAME) ?: ""
        eventType = intent.getStringExtra(Constants.TYPE) ?: ""
        eventDate = intent.getStringExtra(Constants.DATE) ?: ""
        eventComments = intent.getStringExtra(Constants.COMMENT) ?: ""

        toolBar = findViewById(R.id.item_activity_toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.apply {
            titleColor = resources.getColor(R.color.white)
            title = eventName
        }

        toolBar.setNavigationOnClickListener {
            finish()
        }

        eventCardData.text = eventDate
        eventCommentsData.text = eventComments

        if (resources.getStringArray(R.array.one_time_events).contains(eventType)) {
            findViewById<TextView>(R.id.date_card_label).setText("$eventType date")
            findViewById<ImageView>(R.id.date_card_icon).setImageResource(R.drawable.ic_notifications)
            findViewById<ImageView>(R.id.appbar_background).setImageResource(R.drawable.book_1)

        } else if (resources.getStringArray(R.array.yearly_events).contains(eventType)) {
            findViewById<TextView>(R.id.date_card_label).setText("$eventType date")
            findViewById<ImageView>(R.id.date_card_icon).setImageResource(R.drawable.ic_cake)
            findViewById<ImageView>(R.id.appbar_background).setImageResource(R.drawable.pink_cacke)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.item_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_bar_delete -> {
                if (resources.getStringArray(R.array.one_time_events).contains(eventType)) {
                    onetimeViewModel.onItemSelected(eventId)
                } else if (resources.getStringArray(R.array.yearly_events).contains(eventType)) {
                    yearlyViewModel.onItemSelected(eventId)
                }
                finish()
                true
            }
            R.id.item_bar_edit -> {
                val intent = Intent(this.baseContext, EditItemActivity::class.java)
                intent.putExtra(Constants.ID, eventId)
                intent.putExtra(Constants.NAME, eventName)
                intent.putExtra(Constants.TYPE, eventType)
                intent.putExtra(Constants.DATE, eventDate)
                intent.putExtra(Constants.COMMENT, eventComments)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}