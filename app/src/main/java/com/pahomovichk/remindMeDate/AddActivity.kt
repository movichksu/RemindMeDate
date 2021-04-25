package com.pahomovichk.remindMeDate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pahomovichk.remindMeDate.presentation.ui.birthdays.BirthdaysFragment

class AddActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.add_container, BirthdaysFragment.newInstance())
//                .commitNow()
//        }
    }
}