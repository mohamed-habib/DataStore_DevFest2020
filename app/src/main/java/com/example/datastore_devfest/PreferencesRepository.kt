package com.example.datastore_devfest

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

const val PREFERENCE_FILE_NAME = "user_data"
const val USER_NAME_KEY = "user_name_key"
const val USER_AGE_KEY = "user_age_key"

class PreferencesRepository(context: Context) {

    val sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)
//
//    // Keep the user name as a stream of changes
//    private val _userNameFlow = MutableStateFlow(userName)
//    val userNameFlow: StateFlow<String> = _userNameFlow
//
//    private val userName: String
//        get() {
//            return sharedPreferences.getString(USER_NAME_KEY, "") ?: ""
//        }
//
//    // Keep the user name as a stream of changes
//    private val _userAgeFlow = MutableStateFlow(userAge)
//    val userAgeFlow: StateFlow<Int> = _userAgeFlow
//
//    private val userAge: Int
//        get() {
//            return sharedPreferences.getInt(USER_AGE_KEY, 0)
//        }


    fun getUserName(): String {
        return sharedPreferences.getString(USER_NAME_KEY, "") ?: ""
    }

    public fun saveUserName(userName: String) {
        sharedPreferences.edit {
            putString(USER_NAME_KEY, userName)
        }
    }

    public fun saveAge(age: Int) {
        sharedPreferences.edit {
            putInt(USER_AGE_KEY, age)
        }
    }

    fun getUserAge(): Int {
        return sharedPreferences.getInt(USER_AGE_KEY, 0)
    }

}