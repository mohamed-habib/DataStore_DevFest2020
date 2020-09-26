package com.example.datastore_devfest

import android.content.Context
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.*
import java.io.IOException

const val PREFERENCE_FILE_NAME = "user_data"

class PreferencesRepository(context: Context) {
    companion object {
        val USER_NAME_KEY = preferencesKey<String>("user_name_key")
        val USER_AGE_KEY = preferencesKey<Int>("user_age_key")
    }

    private val dataStore = context.createDataStore(
        name = PREFERENCE_FILE_NAME, migrations = listOf(
            SharedPreferencesMigration(context, PREFERENCE_FILE_NAME)
        )
    )

    val userNameFlow: Flow<String> = dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw  it
        }
    }.map { preferences -> preferences[USER_NAME_KEY] ?: "" }


    val userAgeFlow: Flow<Int> = dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw  it
        }
    }.map { preferences -> preferences[USER_AGE_KEY] ?: -1 }


    suspend fun saveUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = userName
        }
    }

    suspend fun saveAge(age: Int) {
        dataStore.edit { preferences ->
            preferences[USER_AGE_KEY] = age
        }
    }

}