package com.moviedb.presentation.utils

import android.content.Context

object AppUtils {

    /*fun formatDate(context: Context, date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss") //set format of date you receiving from db

        val date: Date = sdf.parse(date.replace("T", " ")) as Date
        val comma = context.getString(R.string.comma)
        val newDate = SimpleDateFormat("E$comma dd MMMM")

        return newDate.format(date)
    }*/

    fun getAppVersionNumber(context: Context): Double {
        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return pInfo.versionName.toDouble()
    }
}