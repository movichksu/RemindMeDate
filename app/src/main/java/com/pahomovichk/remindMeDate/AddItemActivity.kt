package com.pahomovichk.remindMeDate

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import com.pahomovichk.remindMeDate.domain.entity.OnetimeEvent
import com.pahomovichk.remindMeDate.presentation.viewModel.YearlyViewModel
import com.pahomovichk.remindMeDate.presentation.viewModel.OnetimeViewModel
import java.time.LocalDate
import java.util.*

class AddItemActivity : AppCompatActivity() {

    private lateinit var yearlyViewModel: YearlyViewModel
    private lateinit var onetimeViewModel: OnetimeViewModel
    private lateinit var toolBar: Toolbar

    private lateinit var nameInput: EditText
    private lateinit var typeInput: AutoCompleteTextView
    private lateinit var dateInput: EditText
    private lateinit var commentInput: EditText
    private lateinit var createItemBtn: Button

    private var eventDate: LocalDate = LocalDate.of(2000, 5, 31)

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        yearlyViewModel = ViewModelProvider(this).get(YearlyViewModel::class.java)
        onetimeViewModel = ViewModelProvider(this).get(OnetimeViewModel::class.java)
        setContentView(R.layout.add_activity)
        toolBar = findViewById(R.id.add_activity_toolbar)
        setSupportActionBar(toolBar)

        nameInput = findViewById(R.id.name_input)
        dateInput = findViewById(R.id.date_input)
        commentInput = findViewById(R.id.comments_input)
        typeInput = findViewById(R.id.selection_input)
        createItemBtn = findViewById(R.id.create_btn)
        createItemBtn.setText("Create")

        toolBar.setNavigationOnClickListener {
            finish()
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
                    eventDate =
                        LocalDate.parse(dateInput.text.toString(), Constants.gettingLocalFormatter)
                },
                getCalendar.get(Calendar.YEAR),
                getCalendar.get(Calendar.MONTH),
                getCalendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        val selection: Array<String> = resources.getStringArray(R.array.add_selection)
        val arrayAdapter =
            ArrayAdapter(this.baseContext, R.layout.selection_db_dropdown_item, selection)
        typeInput.setAdapter(arrayAdapter)

        createItemBtn.setOnClickListener {
            if (nameInput.text.isEmpty() || dateInput.text.isEmpty()) {
                Toast.makeText(this, "Input fields are empty!", Toast.LENGTH_SHORT).show()
            } else {
                if (resources.getStringArray(R.array.yearly_events)
                        .contains(typeInput.text.toString())
                ) {
                    val event = YearlyEvent(
                        0L,
                        nameInput.text.toString(),
                        typeInput.text.toString(),
                        eventDate,
                        commentInput.text.toString()
                    )
                    yearlyViewModel.addEvent(event)
                    finish()
                } else if (resources.getStringArray(R.array.one_time_events)
                        .contains(typeInput.text.toString())
                ) {
                    val event = OnetimeEvent(
                        0L,
                        nameInput.text.toString(),
                        typeInput.text.toString(),
                        eventDate,
                        commentInput.text.toString()
                    )
                    onetimeViewModel.addEvent(event)
                    finish()
                } else {
                    Toast.makeText(this, "Choose the type!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        return true
    }
}
