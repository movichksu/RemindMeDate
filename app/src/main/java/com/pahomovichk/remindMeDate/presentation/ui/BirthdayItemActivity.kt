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
import com.pahomovichk.remindMeDate.MainActivity
import com.pahomovichk.remindMeDate.R
import com.pahomovichk.remindMeDate.presentation.viewModel.BirthdaysViewModel

class BirthdayItemActivity : AppCompatActivity() {

    private lateinit var toolBar: Toolbar
    private lateinit var viewModel: BirthdaysViewModel

    private var birthId = 0L
    private lateinit var birthName : String
    private lateinit var birthDate : String
    private lateinit var birthComments : String

    private lateinit var birthCardData : TextView
    private lateinit var birthCommentsData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_activity)
        setActivityContent()
        viewModel = ViewModelProvider(this).get(BirthdaysViewModel::class.java)
        birthCardData = findViewById(R.id.date_card_data)
        birthCommentsData = findViewById(R.id.date_comment_data)

        birthId = intent.getLongExtra(Constants.ID, 0L)
        birthName = intent.getStringExtra(Constants.NAME) ?: ""
        birthDate = intent.getStringExtra(Constants.DATE) ?: ""
        birthComments = intent.getStringExtra(Constants.COMMENT) ?: ""

        toolBar = findViewById(R.id.item_activity_toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.apply {
            titleColor = resources.getColor(R.color.white)
            title = birthName
        }

        toolBar.setNavigationOnClickListener {
            finish()
        }

        birthCardData.text = birthDate
        birthCommentsData.text = birthComments
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.item_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_bar_delete -> {
                viewModel.onItemSelected(birthId)
                finish()
//                val intent = Intent(this.baseContext, MainActivity::class.java)
//                startActivity(intent)
                true
            }
            R.id.item_bar_edit -> {
                val intent = Intent(this.baseContext, EditItemActivity::class.java)
                intent.putExtra(Constants.ID, birthId)
                intent.putExtra(Constants.NAME, birthName)
                intent.putExtra(Constants.DATE, birthDate)
                intent.putExtra(Constants.COMMENT, birthComments)
                intent.putExtra(Constants.SELECTION_ITEM, resources.getStringArray(R.array.add_selection).get(0))
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActivityContent(){
        findViewById<TextView>(R.id.date_card_label).setText("Birthday date")
        findViewById<ImageView>(R.id.date_card_icon).setImageResource(R.drawable.ic_cake)
        findViewById<ImageView>(R.id.appbar_background).setImageResource(R.drawable.pink_cacke)
    }


}