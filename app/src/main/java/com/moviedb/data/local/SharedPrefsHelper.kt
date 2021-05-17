package com.moviedb.data.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun put(key: String, value: String) =
            sharedPreferences.edit().putString(key, value).apply()

    fun put(key: String, value: Int) =
            sharedPreferences.edit().putInt(key, value).apply()

    fun put(key: String ,value: Float) =
            sharedPreferences.edit().putFloat(key, value).apply()

    fun put(key: String, value: Long) =
            sharedPreferences.edit().putLong(key, value).apply()

    fun put(key: String, value: Boolean) =
            sharedPreferences.edit().putBoolean(key, value).apply()

    fun get(key: String, defaultValue: String = ""): String  =
            sharedPreferences.getString(key, defaultValue)?: ""

    fun get(key: String, defaultValue: Int = 0): Int =
            sharedPreferences.getInt(key, defaultValue)

    fun get(key: String, defaultValue: Float = 0f): Float =
            sharedPreferences.getFloat(key, defaultValue)

    fun get(key: String, defaultValue: Long = 0): Long =
            sharedPreferences.getLong(key, defaultValue)

    fun get(key: String, defaultValue: Boolean = false): Boolean =
            sharedPreferences.getBoolean(key, defaultValue)

    fun deleteSavedData(key: String) =
        sharedPreferences.edit().remove(key).apply()
}