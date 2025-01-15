package com.psbapp.utils.datetime

import com.psbapp.utils.EMPTY
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateTimeUtils {

    fun formatMillis(millis: Long, format: String): String {
        val totalMilliseconds = millis.coerceAtLeast(0)
        val hours = totalMilliseconds / (1000 * 60 * 60)
        val minutes = (totalMilliseconds / (1000 * 60)) % 60
        val seconds = (totalMilliseconds / 1000) % 60
        val milliseconds = totalMilliseconds % 1000

        return format.replace("hh", hours.toInt().toString().padStart(2, '0'))
            .replace("mm", minutes.toInt().toString().padStart(2, '0'))
            .replace("ss", seconds.toInt().toString().padStart(2, '0'))
            .replace("SSS", milliseconds.toInt().toString().padStart(3, '0'))
    }

    fun formatDateString(dateString: String): String {
        return try {
            // Parse the input date string (ISO 8601 format)
            val instant = Instant.parse(dateString)

            // Convert to a local date-time (adjust to the system's timezone)
            val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

            // Format to the desired output, e.g., "Aug 28 2024"
            localDateTime.formatToDisplayFormat()
        } catch (e: Exception) {
            // Handle any parsing or formatting error by returning an empty string
            String.EMPTY
        }
    }

    // Extension function to format LocalDateTime
    private fun LocalDateTime.formatToDisplayFormat(): String {
        val month = this.month.name.lowercase().replaceFirstChar { it.uppercase() }.substring(0, 3) // e.g., "August" -> "Aug"
        val day = this.dayOfMonth
        val year = this.year
        return "$month $day $year"
    }
}