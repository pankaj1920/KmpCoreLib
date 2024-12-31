package com.psbapp.uidesign.ui.edittext.validation

import com.psbapp.uidesign.ui.edittext.validation.BaseValidationState.Companion.errorMessage

data class BasicValidationState(
    private val errorMsg: String = "",
    private val showError: Boolean = true,

    ) : BaseValidationState(
    showError = showError,
    validator = { text -> if (showError) text.isValidField(errorMsg) else true })

fun String.isValidField(errMsg: String): Boolean {
    return when {
        this.isEmpty() -> {
            errorMessage = errMsg
            false
        }

        else -> {
            errorMessage = ""
            true
        }
    }
}

fun baseStateSaver() = textFieldStateSaver(BasicValidationState())