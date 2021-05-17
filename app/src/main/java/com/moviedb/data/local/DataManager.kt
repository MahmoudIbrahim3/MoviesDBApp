package com.moviedb.data.local

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton
private const val IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH"

@Singleton
class DataManager @Inject constructor(private val context: Context,
                                      private val sharedPrefsHelper: SharedPrefsHelper
) {

}