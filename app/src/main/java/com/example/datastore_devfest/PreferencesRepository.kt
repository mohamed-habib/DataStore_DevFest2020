package com.example.datastore_devfest

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.core.content.edit
import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import androidx.datastore.createDataStore
import androidx.datastore.migrations.SharedPreferencesMigration
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.flow.*
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class PreferencesRepository(context: Context) {
    companion object {
        val PREFERENCE_FILE_NAME = "user_data"
        val USER_NAME_KEY = "user_name_key"
        val USER_AGE_KEY = "user_age_key"
    }

    private val dataStore =
        context.createDataStore(
            fileName = "user.pb", serializer = UserPreferencesSerializer,
            migrations = listOf(
                SharedPreferencesMigration(context, PREFERENCE_FILE_NAME)
                { sharedPreferencesView, userPreferences ->
                    userPreferences.toBuilder()
                        .setUserName(sharedPreferencesView.getString(USER_NAME_KEY, defValue = ""))
                        .setAge(sharedPreferencesView.getInt(USER_AGE_KEY, defValue = -1)).build()
                })
        )
/**/
    val userFlow: Flow<UserPreferences> = dataStore.data.catch { exception ->
        // dataStore.data throws an IOException when an error is encountered when reading data
        if (exception is IOException) {
            exception.printStackTrace()
            emit(UserPreferences.getDefaultInstance())
        } else {
            throw exception
        }
    }

    suspend fun saveUser(userName: String, age: Int) {
        dataStore.updateData { preferences ->
            preferences.toBuilder().setUserName(userName).setAge(age).build()
        }
    }
}


object UserPreferencesSerializer : Serializer<UserPreferences> {
    override fun readFrom(input: InputStream): UserPreferences {
        try {
            return UserPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read user.", exception)
        }
    }

    override fun writeTo(t: UserPreferences, output: OutputStream) = t.writeTo(output)
}