package com.example.datastore_devfest

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MainViewModel(val preferencesRepository: PreferencesRepository) : ViewModel() {

    val userNameLiveData = preferencesRepository.userNameFlow.asLiveData()
    val userAgeLiveData = preferencesRepository.userAgeFlow.asLiveData()

    fun getUserName(): String {
        return preferencesRepository.userNameFlow.asLiveData().value ?: ""
    }

    fun getUserAge(): Int {
        return preferencesRepository.userAgeFlow.asLiveData().value ?: 0
    }

    fun saveUserName(userName: String) {
        viewModelScope.launch {
            preferencesRepository.saveUserName(userName)
        }
    }

    fun saveUserAge(userAge: Int) {
        viewModelScope.launch {
            preferencesRepository.saveAge(userAge)
        }
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