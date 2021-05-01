package com.pahomovichk.remindMeDate

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pahomovichk.remindMeDate.entity.Birthday
import com.pahomovichk.remindMeDate.presentation.viewModel.BirthdaysViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class AddActivity : AppCompatActivity() {

    private lateinit var viewModel: BirthdaysViewModel

    private var birthDate: LocalDate = LocalDate.of(2000,5,31)
    private var birthTime: LocalTime = LocalTime.now()

    private lateinit var nameInput: EditText
    private lateinit var dateInput: EditText
    private lateinit var timeInput: EditText
    private lateinit var commentInput: EditText
    private lateinit var createBirthdayBtn: Button

    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)

    @SuppressLint("SimpleDateFormat")
    private val timeFormat = SimpleDateFormat("HH:mm")

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BirthdaysViewModel::class.java)
        setContentView(R.layout.add_activity)
        nameInput = findViewById(R.id.name_input)
        dateInput = findViewById(R.id.date_input)
        timeInput = findViewById(R.id.time_input)
        commentInput = findViewById(R.id.comments_input)
        createBirthdayBtn = findViewById(R.id.create_btn)

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
                    birthDate = LocalDate.of(Calendar.YEAR, Calendar.MONTH,Calendar.DAY_OF_MONTH)
                },
                getCalendar.get(Calendar.YEAR),
                getCalendar.get(Calendar.MONTH),
                getCalendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        timeInput.setOnClickListener {
            val getCalendar = Calendar.getInstance()
            val timePicker = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                getCalendar.set(Calendar.HOUR_OF_DAY, hour)
                getCalendar.set(Calendar.MINUTE, minute)
                timeInput.setText(timeFormat.format(getCalendar.time))
                birthTime = LocalTime.parse(timeFormat.format(getCalendar.time).toString())
            }
            TimePickerDialog(
                this,
                timePicker,
                getCalendar.get(Calendar.HOUR_OF_DAY),
                getCalendar.get(Calendar.MINUTE),
                true
            ).show()
        }

        createBirthdayBtn.setOnClickListener {
            if (nameInput.text.isEmpty() || dateInput.text.isEmpty() || timeInput.text.isEmpty()){
                Toast.makeText( this, "input fields are empty!", Toast.LENGTH_SHORT).show()
            }
            else {
                val birthday = Birthday(0, nameInput.text.toString(), birthDate, birthTime)
                viewModel.addBirthday(birthday)
            }
        }
    }
}
