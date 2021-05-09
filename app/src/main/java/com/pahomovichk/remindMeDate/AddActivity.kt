package com.pahomovichk.remindMeDate

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.pahomovichk.remindMeDate.entity.Birthday
import com.pahomovichk.remindMeDate.presentation.viewModel.BirthdaysViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class AddActivity : AppCompatActivity() {

    private lateinit var viewModel: BirthdaysViewModel
    private lateinit var toolBar: Toolbar

    private var birthDate: LocalDate = LocalDate.of(2000, 5, 31)

    private lateinit var nameInput: EditText
    private lateinit var dateInput: EditText
    private lateinit var commentInput: EditText
    private lateinit var selectionInput: AutoCompleteTextView
    private lateinit var createBirthdayBtn: Button

    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)

    @SuppressLint("SimpleDateFormat")
    private val timeFormat = SimpleDateFormat("HH:mm")

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BirthdaysViewModel::class.java)
        setContentView(R.layout.add_activity)
        toolBar = findViewById(R.id.add_activity_toolbar)
        setSupportActionBar(toolBar)
        nameInput = findViewById(R.id.name_input)
        dateInput = findViewById(R.id.date_input)
        commentInput = findViewById(R.id.comments_input)
        selectionInput = findViewById(R.id.selection_input)
        createBirthdayBtn = findViewById(R.id.create_btn)

        toolBar.setNavigationOnClickListener {
            Toast.makeText(this, "Back clicked!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.baseContext, MainActivity::class.java)
            startActivity(intent)
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
                    val date = dateFormat.format(selectDate.time)
                    dateInput.setText(date)
                    birthDate = LocalDate.of(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH)
                },
                getCalendar.get(Calendar.YEAR),
                getCalendar.get(Calendar.MONTH),
                getCalendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        val selection = resources.getStringArray(R.array.add_selection)
        val arrayAdapter = ArrayAdapter(this.baseContext, R.layout.selection_db_dropdown_item, selection)
        selectionInput.setAdapter(arrayAdapter)

        createBirthdayBtn.setOnClickListener {
            if (nameInput.text.isEmpty() || dateInput.text.isEmpty()) {
                Toast.makeText(this, "input fields are empty!", Toast.LENGTH_SHORT).show()
            } else {
                val birthday = Birthday(0L, nameInput.text.toString(), birthDate, commentInput.text.toString())
                viewModel.addBirthday(birthday)
                val intent = Intent(this.baseContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        return true
    }
}
