package com.example.datastore_devfest

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit


class PreferencesRepository(context: Context) {
    companion object {
        val PREFERENCE_FILE_NAME = "user_data"
        val USER_NAME_KEY = "user_name_key"
        val USER_AGE_KEY = "user_age_key"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)

    fun getUserName(): String {
        return sharedPreferences.getString(USER_NAME_KEY, "") ?: ""
    }

    fun getUserAge(): Int {
        return sharedPreferences.getInt(USER_AGE_KEY, -1)
    }

    fun saveUserName(userName: String) {
        sharedPreferences.edit {
            putString(USER_NAME_KEY, userName)
        }
    }

    fun saveAge(age: Int) {
        sharedPreferences.edit {
            putInt(USER_AGE_KEY, age)
        }
    }

}