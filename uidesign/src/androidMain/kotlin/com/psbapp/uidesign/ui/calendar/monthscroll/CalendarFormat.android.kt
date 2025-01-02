package com.psbapp.uidesign.ui.calendar.monthscroll

import android.os.Build
import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.DayOfWeek
import java.time.Month

actual fun Month.getMonthName(short: Boolean, locale: Locale): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Use the Java 8+ API for API 26 and above
        val style = if (short) java.time.format.TextStyle.SHORT else java.time.format.TextStyle.FULL
        this.getDisplayName(style, java.util.Locale.forLanguageTag(locale.toLanguageTag()))
    } else {
        // Custom implementation for API levels below 26
        val monthNames = if (short) {
            arrayOf(
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul",
                "Aug",
                "Sep",
                "Oct",
                "Nov",
                "Dec"
            )
        } else {
            arrayOf(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            )
        }
        monthNames[this.ordinal] // `this.ordinal` gets the zero-based index of the month
    }
}

actual fun DayOfWeek.getDaysName(narrow: Boolean, locale: Locale): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Use the Java 8+ API for API 26 and above
        val style =
            if (narrow) java.time.format.TextStyle.NARROW else java.time.format.TextStyle.SHORT
        this.getDisplayName(style, java.util.Locale.forLanguageTag(locale.toLanguageTag()))
    } else {
        // Custom implementation for API levels below 26
        val dayNames = if (narrow) {
            arrayOf("S", "M", "T", "W", "T", "F", "S")
        } else {
            arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        }
        dayNames[this.ordinal] // `this.ordinal` gets the zero-based index of the day
    }
}
