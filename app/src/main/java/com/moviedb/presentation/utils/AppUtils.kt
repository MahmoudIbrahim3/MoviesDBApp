package com.moviedb.presentation.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {

    fun formatDate(context: Context, date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")

        val date: Date = sdf.parse(date) as Date
        val newDate = SimpleDateFormat("MMMM dd, yyyy")

        return newDate.format(date)
    }

    fun getAppVersionNumber(context: Context): Double {
        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return pInfo.versionName.toDouble()
    }
}