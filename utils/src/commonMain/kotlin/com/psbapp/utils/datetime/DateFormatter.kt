package com.psbapp.utils.datetime

import com.psbapp.utils.capitalizeFirstChar
import com.psbapp.utils.value
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun getCurrentDate() =  LocalDate.now()
fun getCurrentDateInString() =  LocalDate.now().toString()


object DateUtils {

    fun String.getInstant(): Instant? = try {
        this.toInstant()
    } catch (e: IllegalArgumentException) {
        // TODO throw incorrect date to firebase
        e.printStackTrace()
        null
    }

    fun getLocalDateTime(utcTimeStr: String): LocalDateTime? = try {
        getLocalDateTime(utcTimeStr.getInstant()?.toEpochMilliseconds() ?: 0)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    fun getTimeMiles(
        year: Int,
        month: Int,
        day: Int,
    ): Long {
        val timeZone: TimeZone = TimeZone.currentSystemDefault()

        // Create a LocalDateTime with the provided year, month, and day
        val localDateTime = LocalDateTime(year, month, day, 0, 0, 0, 0)

        // Convert LocalDateTime to Instant
        val instant: Instant = localDateTime.toInstant(timeZone)

        // Convert Instant to milliseconds
        return instant.toEpochMilliseconds()
    }

    fun getCurrentTime(): Long {
        val currentInstant: Instant = Clock.System.now()
        return currentInstant.toEpochMilliseconds()
    }

    fun getMonthDayCount(timeStamp: Long): Int {
        val timeZone: TimeZone = TimeZone.currentSystemDefault()

        // Convert timestamp to Instant
        val instant = Instant.fromEpochMilliseconds(timeStamp)

        // Convert Instant to LocalDate
        val localDate = instant.toLocalDateTime(timeZone).date

        // Calculate the number of days in the month
        val daysInMonth = when (localDate.month) {
            Month.FEBRUARY -> if (localDate.year % 4 == 0) 29 else 28
            Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
            else -> 31
        }

        return daysInMonth
    }

    fun getDay(timeStamp: Long): Int {
        val timeZone: TimeZone = TimeZone.currentSystemDefault()

        // Convert timestamp to Instant
        val instant = Instant.fromEpochMilliseconds(timeStamp)

        // Convert Instant to LocalDateTime
        val localDateTime = instant.toLocalDateTime(timeZone)

        // Extract the day of the month from LocalDateTime
        return localDateTime.dayOfMonth
    }

    /** String must be a UTC time format **/
    fun String.isToday() : Boolean =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date ==
                this.getInstant()?.toLocalDateTime(TimeZone.currentSystemDefault())?.date


    /** String must be a UTC time format **/
    fun String.getWeekDay(): String {
        val timeZone: TimeZone = TimeZone.currentSystemDefault()
        return getInstant()?.toLocalDateTime(timeZone)?.dayOfWeek?.name.value
    }

    fun getLocalDateTime(timeStamp: Long): LocalDateTime {
        val timeZone: TimeZone = TimeZone.currentSystemDefault()

        // Convert timestamp to Instant
        val instant = Instant.fromEpochMilliseconds(timeStamp)

        // Convert Instant to LocalDateTime
        return instant.toLocalDateTime(timeZone)
    }

    fun getMonth(timeStamp: Long): Int {
        val timeZone: TimeZone = TimeZone.currentSystemDefault()

        // Convert timestamp to Instant
        val instant = Instant.fromEpochMilliseconds(timeStamp)

        // Convert Instant to LocalDateTime
        val localDateTime = instant.toLocalDateTime(timeZone)

        // Extract the month from LocalDateTime
        return localDateTime.monthNumber
    }

    fun getYear(timeStamp: Long): Int {
        val timeZone: TimeZone = TimeZone.currentSystemDefault()

        // Convert timestamp to Instant
        val instant = Instant.fromEpochMilliseconds(timeStamp)

        // Convert Instant to LocalDateTime
        val localDateTime = instant.toLocalDateTime(timeZone)

        // Extract the year from LocalDateTime
        return localDateTime.year
    }

    fun monthNumberToString(monthNumber: Int): String {
        val month = Month.values()[monthNumber - 1] // Adjust monthNumber to be 0-based
        return month.name
    }

    fun Long.formatMilliSecToMMSSss(): String {
        val second = this / 1000
        val minutes = second / 60
        return if (minutes > 0) {
            "${minutes.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}"
        } else {
            "${second.toString().padStart(2, '0')}:${"0".padStart(2, '0')}"
        }
    }

    fun LocalDate.dateString(): String {
        return "$year-$monthNumber-$dayOfMonth"
    }

    fun LocalDate.ddMMM(): String {
        // Extract day and month components
        val dayOfMonth = dayOfMonth
        val month = month
        // Map month to abbreviated form (e.g., Jan, Feb)
        val abbreviatedMonth = month.toString().substring(0, 3).lowercase().capitalizeFirstChar()
        return "$dayOfMonth $abbreviatedMonth"
    }
    fun LocalDate.MMM(): String {
        val month = month
        // Map month to abbreviated form (e.g., Jan, Feb)
        return month.toString().substring(0, 3).lowercase().capitalizeFirstChar()
    }


    fun LocalDate.ddMMYYYY(): String {
        // Extract day and month components
        val dayOfMonth = dayOfMonth
        val month = monthNumber
        val year = year
        // Map month to abbreviated form (e.g., Jan, Feb)
        return "$dayOfMonth-$month-$year"
    }


    /** Int must be in seconds **/
    fun Int.durationString(): String {
        val hours = this / 3600

        val remainingTime = this % 3600

        val minutes = remainingTime / 60

        return "${hours}h ${minutes}m"
    }





    fun getStartOfCurrentWeek(): LocalDate {
        val currentDate = getTodayDate()
        with(currentDate) {
            // Calculate the start of the week (assuming Monday is the first day of the week)
            return minus(
                dayOfWeek.ordinal.toLong() - DayOfWeek.MONDAY.ordinal.toLong(), DateTimeUnit.DAY
            )
        }
    }


    fun getStartOfCurrentMonth(): LocalDate {
        val currentDate = getTodayDate()
        return LocalDate(currentDate.year, currentDate.monthNumber, 1)
    }


    fun getEndOfCurrentMonth(): LocalDate {
        return getStartOfCurrentMonth().plus(DatePeriod(months = 1)).minus(DatePeriod(days = 1))
    }

    fun getMonthEndDate(startDate: LocalDate): LocalDate {
        return startDate.plus(DatePeriod(months = 1)).minus(DatePeriod(days = 1))
    }

    fun getPreviousNthMonthStart(n: Int, month: LocalDate): LocalDate {
        return month.minus(DatePeriod(months = n))
    }

    fun getLast12MonthsStartDate(): List<Pair<LocalDate, LocalDate>> {
        val listOfStartDates = mutableListOf<Pair<LocalDate, LocalDate>>()
        var currentMonthStart = getStartOfCurrentMonth()
        repeat(12) {
            listOfStartDates.add(Pair(currentMonthStart, getMonthEndDate(currentMonthStart)))
            currentMonthStart = currentMonthStart.minus(DatePeriod(months = 1))
        }
        return listOfStartDates
    }

    fun getEndOfCurrentWeek(): LocalDate {
        return getStartOfCurrentWeek().plus(6, DateTimeUnit.DAY)
    }

    fun getTodayDate(): LocalDate {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    fun getCurrentLocalDateTime() : LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    /***
     * start date and end date is inclusive
     */
    fun getLocalDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var temp = startDate

        while (temp <= endDate) {
            dates.add(temp)
            temp = temp.plus(1, DateTimeUnit.DAY)
        }
        return dates
    }


    fun getTimerTimeUpToMinutesOnly(milliseconds: Long): String { // returns mm:ss Ex 00:45)
        val totalSeconds = (milliseconds / 1000)%3600
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60

        val formattedMinutes = minutes.twoDigits()
        val formattedSeconds = seconds.twoDigits()

        return "$formattedMinutes:$formattedSeconds"
    }

    private fun Long.twoDigits(): String {
        return if (this < 10) "0$this" else this.toString()
    }

    fun formatTimeNoSpace(data: Long, showHour: Boolean = false): String {
        val seconds = ((data / 1000.0) % 60)
        val minutes = ((data / (1000 * 60)) % 60)
        val hours = ((data / (1000 * 60 * 60)) % 24)
        val formatSeconds = seconds.toLong().twoDigits()
        val formattedMinutes = minutes.twoDigits()
        val formattedHour = hours.twoDigits()
        return if(hours <= 0)
            if (showHour) "00:$formattedMinutes:$formatSeconds"
            else "$formattedMinutes:$formatSeconds"
        else
            "$formattedHour:$formattedMinutes:$formatSeconds"

    }



}