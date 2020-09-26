package com.example.datastore_devfest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(PreferencesRepository(this))
        ).get(MainViewModel::class.java)

        updateui()

    }

    fun onSaveClicked(view: View) {

        val userName = user_name_et.text
        val age = age_et.text

        viewModel.saveUserName(userName.toString())

        viewModel.saveUserAge(age.toString().toInt())

        updateui()

    }

    private fun updateui() {
        user_name_tv.text = viewModel.getUserName()
        user_age_tv.text = viewModel.getUserAge().toString()
    }
}