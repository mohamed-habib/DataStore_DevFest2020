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


        viewModel.userNameLiveData.observe(this, Observer {
            user_name_tv.text = it
        })

        viewModel.userAgeLiveData.observe(this, Observer {
            user_age_tv.text = it.toString()
        })

    }

    fun onSaveClicked(view: View) {
        val userName = user_name_et.text
        val age = age_et.text

        viewModel.saveUserName(userName.toString())

        viewModel.saveUserAge(age.toString().toInt())
    }

}