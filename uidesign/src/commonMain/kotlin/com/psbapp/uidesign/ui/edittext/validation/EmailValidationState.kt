package com.psbapp.uidesign.ui.edittext.validation

import com.psbapp.appres.CoreStringRes
import com.psbapp.uidesign.ui.edittext.validation.BaseValidationState.Companion.errorMessage
import gesundheitskiosk.core.resources.generated.resources.email_empty_err
import gesundheitskiosk.core.resources.generated.resources.incorrect_email_err
import org.jetbrains.compose.resources.getString



class EmailValidationStateState : BaseValidationState() {
    override suspend fun isValid(): Boolean {
        return text.isEmailValid()
    }
}


private suspend fun String.isEmailValid(): Boolean {
    return when {
        this.isEmpty() -> {
            errorMessage = getString(CoreStringRes.email_empty_err)
            false
        }

        this.isValidEmail().not() -> {
            errorMessage = getString(CoreStringRes.incorrect_email_err)
            false
        }

        else -> {
            errorMessage = ""
            true
        }
    }
}
private fun emailStateSaver() = textFieldStateSaver(EmailValidationStateState())

class EmptyValidationState : BaseValidationState() {
    override suspend fun isValid(): Boolean {
        return text.isStringValid()
    }
}

private suspend fun String.isStringValid():Boolean{
    return when{
        this.isEmpty()->{
            errorMessage = "Please enter password"
            false
        }else->{
            true
        }
    }
}