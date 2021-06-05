package com.pahomovichk.remindMeDate

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import com.pahomovichk.remindMeDate.domain.entity.OnetimeEvent
import com.pahomovichk.remindMeDate.presentation.ui.ItemActivity
import com.pahomovichk.remindMeDate.presentation.viewModel.YearlyViewModel
import com.pahomovichk.remindMeDate.presentation.viewModel.OnetimeViewModel
import java.time.LocalDate
import java.util.*

class EditItemActivity : AppCompatActivity() {

    private lateinit var yearlyViewModel: YearlyViewModel
    private lateinit var onetimeViewModel: OnetimeViewModel
    private lateinit var toolBar: Toolbar

    private var id = 0L
    private lateinit var name: String
    private lateinit var type: String
    private lateinit var date: String
    private lateinit var comments: String

    private lateinit var nameInput: EditText
    private lateinit var typeInput: AutoCompleteTextView
    private lateinit var dateInput: EditText
    private lateinit var commentInput: EditText
    private lateinit var editItemBtn: Button

    private var eventDate: LocalDate = LocalDate.of(2000, 5, 31)

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        yearlyViewModel = ViewModelProvider(this).get(YearlyViewModel::class.java)
        onetimeViewModel = ViewModelProvider(this).get(OnetimeViewModel::class.java)
        setContentView(R.layout.add_activity)
        toolBar = findViewById(R.id.add_activity_toolbar)
        setSupportActionBar(toolBar)

        id = intent.getLongExtra(Constants.ID, 0L)
        name = intent.getStringExtra(Constants.NAME) ?: ""
        type = intent.getStringExtra(Constants.TYPE) ?: ""
        date = intent.getStringExtra(Constants.DATE) ?: ""
        comments = intent.getStringExtra(Constants.COMMENT) ?: ""

        nameInput = findViewById(R.id.name_input)
        nameInput.setText(name)
        typeInput = findViewById(R.id.selection_input)
        typeInput.setText(type)
        dateInput = findViewById(R.id.date_input)
        dateInput.setText(date)
        commentInput = findViewById(R.id.comments_input)
        commentInput.setText(comments)
        editItemBtn = findViewById(R.id.create_btn)
        editItemBtn.setText("Edit")

        toolBar.setNavigationOnClickListener {
            if (resources.getStringArray(R.array.yearly_events).contains(typeInput.text.toString())) {
                val intent = Intent(this.baseContext, ItemActivity::class.java)
                intent.putExtra(Constants.ID, id)
                intent.putExtra(Constants.NAME, name)
                intent.putExtra(Constants.TYPE, type)
                intent.putExtra(Constants.DATE, date)
                intent.putExtra(Constants.COMMENT, comments)
                startActivity(intent)
                finish()
            } else if (resources.getStringArray(R.array.one_time_events).contains(typeInput.text.toString())) {
                val intent = Intent(this.baseContext, ItemActivity::class.java)
                intent.putExtra(Constants.ID, id)
                intent.putExtra(Constants.NAME, name)
                intent.putExtra(Constants.TYPE, type)
                intent.putExtra(Constants.DATE, date)
                intent.putExtra(Constants.COMMENT, comments)
                startActivity(intent)
                finish()
            } else {
                finish()
            }
        }

        dateInput.setOnClickListener {
            val getCalendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                //android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, month)
                    selectDate.set(Calendar.DAY_OF_MONTH, day)
                    val date = Constants.simpleDateFormatter.format(selectDate.time)
                    dateInput.setText(date)
                    eventDate = LocalDate.parse(dateInput.text.toString(), Constants.gettingLocalFormatter)
                },
                getCalendar.get(Calendar.YEAR),
                getCalendar.get(Calendar.MONTH),
                getCalendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        editItemBtn.setOnClickListener {
            if (nameInput.text.isEmpty() || dateInput.text.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_fields_toast), Toast.LENGTH_SHORT).show()
            } else {
                if (resources.getStringArray(R.array.yearly_events).contains(typeInput.text.toString())) {
                    val event = YearlyEvent(
                        id,
                        nameInput.text.toString(),
                        typeInput.text.toString(),
                        eventDate,
                        commentInput.text.toString()
                    )
                    yearlyViewModel.editEvent(event)
                    val intent = Intent(this.baseContext, ItemActivity::class.java)
                    intent.putExtra(Constants.ID, event.id)
                    intent.putExtra(Constants.NAME, event.name)
                    intent.putExtra(Constants.TYPE, type)
                    intent.putExtra(Constants.DATE, "${event.date.format(Constants.gettingLocalFormatter)}")
                    intent.putExtra(Constants.COMMENT, event.comments)
                    startActivity(intent)
                    finish()
                } else if (resources.getStringArray(R.array.one_time_events).contains(typeInput.text.toString())) {
                    val event = OnetimeEvent(
                        id,
                        nameInput.text.toString(),
                        typeInput.text.toString(),
                        eventDate,
                        commentInput.text.toString()
                    )
                    onetimeViewModel.editEvent(event)
                    val intent = Intent(this.baseContext, ItemActivity::class.java)
                    intent.putExtra(Constants.ID, event.id)
                    intent.putExtra(Constants.NAME, event.name)
                    intent.putExtra(Constants.TYPE, type)
                    intent.putExtra(Constants.DATE, "${event.date.format(Constants.gettingLocalFormatter)}")
                    intent.putExtra(Constants.COMMENT, event.comments)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, getString(R.string.empty_type_toast), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        return true
    }
}
