package com.moviedb.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val MOVIE_DATE_FORMAT = "yyyy-MM-dd"

    fun formatDateForMovieListItem(date: String): String {
        val sdf = SimpleDateFormat(MOVIE_DATE_FORMAT, Locale.ENGLISH)

        val date: Date = sdf.parse(date) as Date
        val newDate = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)

        return newDate.format(date)
    }

    fun fetchYear(date: String): String {
        val sdf = SimpleDateFormat(MOVIE_DATE_FORMAT, Locale.ENGLISH)

        val date: Date = sdf.parse(date) as Date
        val newDate = SimpleDateFormat("(yyyy)", Locale.ENGLISH)

        return newDate.format(date)
    }

    fun formatDateForMovieDetails(date: String): String {
        val sdf = SimpleDateFormat(MOVIE_DATE_FORMAT, Locale.ENGLISH)

        val date: Date = sdf.parse(date) as Date
        val newDate = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

        return newDate.format(date)
    }
}