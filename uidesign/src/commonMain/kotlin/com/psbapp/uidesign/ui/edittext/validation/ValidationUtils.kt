package com.psbapp.uidesign.ui.edittext.validation


fun String.isValidEmail(): Boolean = this.matches(
    Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
)


fun String.isValidPassword(): Boolean =
    this.matches(Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"))

fun isValidText(text: String): Boolean {
    // Add your custom validation rules here
    return text.matches(Regex("[a-zA-Z]+"))
}
