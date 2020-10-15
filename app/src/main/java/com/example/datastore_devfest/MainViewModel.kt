package com.example.datastore_devfest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(val preferencesRepository: PreferencesRepository) : ViewModel() {


    fun getUserName(): String {
        return preferencesRepository.getUserName()
    }

    fun getUserAge(): String {
        if (preferencesRepository.getUserAge() == -1)
            return ""
        return preferencesRepository.getUserAge().toString()
    }

    fun saveUserName(userName: String) {
        preferencesRepository.saveUserName(userName)
    }

    fun saveUserAge(userAge: String) {
        preferencesRepository.saveAge(userAge.toInt())
    }
}


class ViewModelFactory(
    private val preferencesRepository: PreferencesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(preferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}