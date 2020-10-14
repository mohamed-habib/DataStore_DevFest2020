package com.example.datastore_devfest

import androidx.lifecycle.*
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MainViewModel(val preferencesRepository: PreferencesRepository) : ViewModel() {


    var userLiveData = preferencesRepository.userFlow.asLiveData()

    fun getUserName(): String {
        return userLiveData.value?.userName ?: ""
    }

    fun getUserAge(): Int {
        return userLiveData.value?.age ?: -1
    }

    fun saveUser(userName: String, age: String) {
        viewModelScope.launch {
            preferencesRepository.saveUser(userName, age.toInt())
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