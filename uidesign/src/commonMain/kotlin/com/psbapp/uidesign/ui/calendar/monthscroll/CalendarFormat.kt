package com.psbapp.uidesign.ui.calendar.monthscroll

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import com.kizitonwose.calendar.core.Week
import com.kizitonwose.calendar.core.YearMonth
import com.kizitonwose.calendar.core.yearMonth
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month

fun YearMonth.displayYearText(short: Boolean = false): String {
    return "${month.displayMonthText(short = short)} $year"
}

fun Month.displayMonthText(short: Boolean = true): String {
    return getMonthName(short, enLocale)
}

fun DayOfWeek.displayDaysText(uppercase: Boolean = false, narrow: Boolean = false): String {
    return getDaysName(narrow, enLocale).let { value ->
        if (uppercase) value.toUpperCase(enLocale) else value
    }
}

expect fun Month.getMonthName(short: Boolean, locale: Locale): String

expect fun DayOfWeek.getDaysName(narrow: Boolean = false, locale: Locale): String

private val enLocale = Locale("en-US")

fun getWeekPageTitle(week: Week): String {
    val firstDate = week.days.first().date
    val lastDate = week.days.last().date
    return when {
        firstDate.yearMonth == lastDate.yearMonth -> {
            firstDate.yearMonth.displayYearText()
        }

        firstDate.year == lastDate.year -> {
            "${firstDate.month.displayMonthText(short = false)} - ${lastDate.yearMonth.displayYearText()}"
        }

        else -> {
            "${firstDate.yearMonth.displayYearText()} - ${lastDate.yearMonth.displayYearText()}"
        }
    }
}
