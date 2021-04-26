package com.pahomovichk.remindMeDate

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class AddActivity: AppCompatActivity() {

    lateinit var nameInput: EditText
    lateinit var dateInput: EditText
    lateinit var timeInput: EditText
    lateinit var commentInput: EditText
    var formatDate = SimpleDateFormat("dd MMMM yyyy", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)
        nameInput = findViewById(R.id.name_input)
        dateInput = findViewById(R.id.date_input)
        timeInput = findViewById(R.id.time_input)
        commentInput = findViewById(R.id.comments_input)

        dateInput.setOnClickListener {
            val getCalendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_MinWidth,DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val selectDate = Calendar.getInstance()
                selectDate.set(Calendar.YEAR,year)
                selectDate.set(Calendar.MONTH,month)
                selectDate.set(Calendar.DAY_OF_MONTH,day)
                val date = formatDate.format(selectDate.time)
                Toast.makeText(this, "Date: $date",Toast.LENGTH_SHORT).show()
                dateInput.setText(date)
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
               getCalendar.set(Calendar.MINUTE,minute)

                //Toast.makeText(this, "Date: $date",Toast.LENGTH_SHORT).show()
                timeInput.setText(SimpleDateFormat("HH,mm").format(getCalendar.time))
            }
            TimePickerDialog(this,
                timePicker,
                getCalendar.get(Calendar.HOUR_OF_DAY),
                getCalendar.get(Calendar.MINUTE),
                true
            ).show()


        }

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.add_container, BirthdaysFragment.newInstance())
//                .commitNow()
//        }
    }
}
