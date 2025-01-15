package com.psbapp.uidesign.ui.edittext.validation

import com.psbapp.appres.CoreStringRes
import com.psbapp.uidesign.ui.edittext.validation.BaseValidationState.Companion.errorMessage
import syncride.core.resources.generated.resources.invalid_password_err
import syncride.core.resources.generated.resources.password_and_confirm_password_not_matched_err
import syncride.core.resources.generated.resources.password_empty_err
import org.jetbrains.compose.resources.getString


class PasswordValidationStateState : BaseValidationState() {

    override suspend fun isValid(): Boolean {
        return text.isPasswordValid()
    }
}


data class ConfirmPasswordValidationState(
    private val passwordState: PasswordValidationStateState? = null,
) : BaseValidationState() {

    override suspend fun isValid(): Boolean {
        return passwordState?.text?.let { text.isPasswordAndConfirmationValid(it) } == true
    }
}


private suspend fun String.isPasswordValid(): Boolean {
    return when {
        this.isEmpty() -> {
            errorMessage = getString(CoreStringRes.password_empty_err)
            false
        }

        this.isValidPassword().not() -> {
            errorMessage = getString(CoreStringRes.invalid_password_err)
            false
        }

        else -> {
            errorMessage = ""
            true
        }
    }
}


private suspend fun String.isPasswordAndConfirmationValid(password: String): Boolean {
    return when {
        this.isEmpty() -> {
            errorMessage = getString(CoreStringRes.password_empty_err)
            false
        }

        password != this -> {
            errorMessage = getString(CoreStringRes.password_and_confirm_password_not_matched_err)
            return false
        }

        else -> {
            errorMessage = ""
            return true
        }
    }
}

fun passwordStateSaver() = textFieldStateSaver(PasswordValidationStateState())