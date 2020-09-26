package com.example.datastore_devfest

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.combine

class MainViewModel(val preferencesRepository: PreferencesRepository) : ViewModel() {


    fun getUserName():String{
        return preferencesRepository.getUserName()
    }

    fun getUserAge():Int{
        return preferencesRepository.getUserAge()
    }

    fun saveUserName(userName: String) {
        preferencesRepository.saveUserName(userName)
    }

    fun saveUserAge(userAge: Int) {
        preferencesRepository.saveAge(userAge)
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