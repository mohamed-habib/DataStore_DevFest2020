package com.example.datastore_devfest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(PreferencesRepository(this))
        ).get(MainViewModel::class.java)

        updateUI()

    }

    fun onSaveClicked(view: View) {

        val userName = user_name_et.text
        val age = age_et.text

        viewModel.saveUserName(userName.toString())

        viewModel.saveUserAge(age.toString())

        updateUI()

    }

    private fun updateUI() {
        user_name_tv.text = viewModel.getUserName()
        user_age_tv.text = viewModel.getUserAge()
    }
}