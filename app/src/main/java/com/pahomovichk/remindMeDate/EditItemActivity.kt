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
import com.pahomovichk.remindMeDate.presentation.ui.YearlyItemActivity
import com.pahomovichk.remindMeDate.presentation.ui.OnetimeItemActivity
import com.pahomovichk.remindMeDate.presentation.viewModel.YearlyViewModel
import com.pahomovichk.remindMeDate.presentation.viewModel.OnetimeViewModel
import java.time.LocalDate
import java.util.*

class EditItemActivity : AppCompatActivity() {

    private lateinit var yearlyViewModel: YearlyViewModel
    private lateinit var onetimeViewModel: OnetimeViewModel
    private lateinit var toolBar: Toolbar

    private var id = 0L
    private lateinit var name : String
    private lateinit var date : String
    private lateinit var comments : String
    private lateinit var selectionItem : String

    private lateinit var nameInput: EditText
    private lateinit var dateInput: EditText
    private lateinit var commentInput: EditText
    private lateinit var selectionInput: AutoCompleteTextView
    private lateinit var editItemBtn: Button
    private val types = resources.getStringArray(R.array.add_selection)

    private var birthDate: LocalDate = LocalDate.of(2000, 5, 31)

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
        date = intent.getStringExtra(Constants.DATE) ?: ""
        comments = intent.getStringExtra(Constants.COMMENT) ?: ""
        selectionItem = intent.getStringExtra(Constants.SELECTION_ITEM) ?: ""

        nameInput = findViewById(R.id.name_input)
        nameInput.setText(name)
        dateInput = findViewById(R.id.date_input)
        dateInput.setText(date)
        commentInput = findViewById(R.id.comments_input)
        commentInput.setText(comments)
        selectionInput = findViewById(R.id.selection_input)
        selectionInput.setText(selectionItem)
        editItemBtn = findViewById(R.id.create_btn)
        editItemBtn.setText("Edit")

        toolBar.setNavigationOnClickListener {
            if (selectionInput.text.toString() == types.get(0)){
                val intent = Intent(this.baseContext, YearlyItemActivity::class.java)
                intent.putExtra(Constants.ID, id)
                intent.putExtra(Constants.NAME, name)
                intent.putExtra(Constants.DATE, date)
                intent.putExtra(Constants.COMMENT, comments)
                startActivity(intent)
            }
            else if (selectionInput.text.toString() == types.get(1)) {
                val intent = Intent(this.baseContext, OnetimeItemActivity::class.java)
                intent.putExtra(Constants.ID, id)
                intent.putExtra(Constants.NAME, name)
                intent.putExtra(Constants.DATE, date)
                intent.putExtra(Constants.COMMENT, comments)
                startActivity(intent)
            }
            else {
                val intent = Intent(this.baseContext, MainActivity::class.java)
                startActivity(intent)
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
                        birthDate = LocalDate.parse(dateInput.text.toString(),Constants.gettingLocalFormatter)
                    },
                    getCalendar.get(Calendar.YEAR),
                    getCalendar.get(Calendar.MONTH),
                    getCalendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        editItemBtn.setOnClickListener {
            if (nameInput.text.isEmpty() || dateInput.text.isEmpty()) {
                Toast.makeText(this, "input fields are empty!", Toast.LENGTH_SHORT).show()
            } else {
                if (selectionInput.text.toString() == types.get(0)){
                    val birthday = YearlyEvent(id, nameInput.text.toString(), birthDate, commentInput.text.toString())
                    yearlyViewModel.editEvent(birthday)
                    val intent = Intent(this.baseContext, YearlyItemActivity::class.java)
                    intent.putExtra(Constants.ID, birthday.id)
                    intent.putExtra(Constants.NAME, birthday.name)
                    intent.putExtra(Constants.DATE, "${birthday.date.format(Constants.gettingLocalFormatter)}")
                    intent.putExtra(Constants.COMMENT, birthday.comments)
                    startActivity(intent)
                }
                else if (selectionInput.text.toString() == types.get(1)) {
                    val event = OnetimeEvent(id, nameInput.text.toString(), birthDate, commentInput.text.toString())
                    onetimeViewModel.editEvent(event)
                    val intent = Intent(this.baseContext, OnetimeItemActivity::class.java)
                    intent.putExtra(Constants.ID, event.id)
                    intent.putExtra(Constants.NAME, event.name)
                    intent.putExtra(Constants.DATE, "${event.date.format(Constants.gettingLocalFormatter)}")
                    intent.putExtra(Constants.COMMENT, event.comments)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "Choose database!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        return true
    }
}
