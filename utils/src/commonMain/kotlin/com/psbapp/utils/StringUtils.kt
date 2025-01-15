package com.psbapp.utils


val String.Companion.EMPTY: String by lazy { "" }
val String.Companion.COMMA: String by lazy { "," }

/** This Extension field of type string returns the value of string or empty */
val String?.value : String get() = this ?: String.EMPTY

// This method is added for comparing contents of strings with ignoring case
fun String?.containsIgnoreCase(other: String?): Boolean {
    if (this==null || other == null) return false
    return this.contains(other, ignoreCase = true)
}

/**
 * Essential for Phone no.
 */
fun String.isDigitsOnly(): Boolean = this.all { it.isDigit() }


/**
 * To capitalize first char in a string.
 */
fun String?.capitalizeFirstChar(): String {
    val s = this
    if (s.isNullOrEmpty()) {
        return String.EMPTY
    }
    val first = s[0]
    return if (first.isUpperCase()) {
        s
    } else {
        first.uppercaseChar().toString() + s.substring(1)
    }
}

fun String.getFirstName() = this.substringBefore(" ")
fun String.getLastName ()= this.substringAfter(
    " ",
    ""
).trim()

fun concatenateStrings(first: String, second: String, third: String): String {
    return StringBuilder().apply {
        append("$first-")
        append("$second-")
        append(third)
    }.toString()
}