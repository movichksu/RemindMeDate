package com.pahomovichk.remindMeDate

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pahomovichk.remindMeDate.domain.YearlyEventsUseCase
import com.pahomovichk.remindMeDate.domain.OnetimeEventsUseCase
import com.pahomovichk.remindMeDate.presentation.viewModel.YearlyViewModel
import com.pahomovichk.remindMeDate.presentation.viewModel.OnetimeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        fun newInstance() =
            MainActivity()
    }

    private lateinit var addBtn: FloatingActionButton
    private lateinit var toolBar: Toolbar

    private val birthdaysUseCase: YearlyEventsUseCase by lazy { Dependencies.getYearlyEventUseCase() }
    private val onetimeEventsUseCase: OnetimeEventsUseCase by lazy { Dependencies.getOnetimeEventUseCase() }
    private lateinit var yearlyViewModel: YearlyViewModel
    private lateinit var onetimeViewModel: OnetimeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        yearlyViewModel = ViewModelProvider(this).get(YearlyViewModel::class.java)
        onetimeViewModel = ViewModelProvider(this).get(OnetimeViewModel::class.java)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.background = null
        navView.menu.getItem(2).isEnabled = false
        addBtn = findViewById(R.id.add_btn)
        toolBar = findViewById(R.id.main_activity_toolbar)
        setSupportActionBar(toolBar)

        addBtn.setOnClickListener {
            val intent = Intent(this.baseContext, AddItemActivity::class.java)
            startActivity(intent)
//            val intent = Intent(this.baseContext, ItemActivity::class.java)
//            startActivity(intent)
        }

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.onetime_events_navigation,
                R.id.yearly_events_navigation,
                R.id.calendar_navigation,
                R.id.settings_navigation
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.main_bar_delete_all_birthdays -> {
                yearlyViewModel.viewModelScope.launch(Dispatchers.IO) {
                    birthdaysUseCase.cleanDb()
                }
                true
            }
            R.id.main_bar_delete_all_events -> {
                onetimeViewModel.viewModelScope.launch(Dispatchers.IO) {
                    onetimeEventsUseCase.cleanDb()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}