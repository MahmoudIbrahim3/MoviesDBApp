package com.moviedb.presentation.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {

    fun formatDateForMovieListItem(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")

        val date: Date = sdf.parse(date) as Date
        val newDate = SimpleDateFormat("MMMM dd, yyyy")

        return newDate.format(date)
    }

    fun fetchYear(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")

        val date: Date = sdf.parse(date) as Date
        val newDate = SimpleDateFormat("(yyyy)")

        return newDate.format(date)
    }

    fun formatDateForMovieDetails(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")

        val date: Date = sdf.parse(date) as Date
        val newDate = SimpleDateFormat("dd/MM/yyyy")

        return newDate.format(date)
    }

    fun getAppVersionNumber(context: Context): Double {
        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return pInfo.versionName.toDouble()
    }

    fun getRunTime(runtime: Int): String? {
        val h = runtime / 60
        val m = runtime % 60

        return "${h}h ${m}m"
    }
}