package com.psbapp.uidesign.ui.toastbar.utils

import MessageBarState
import com.psbapp.uidesign.ui.toastbar.controller.ToastBarController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun MessageBarState.showErrorMessage(message: String) = this.addError(Exception(message))
fun MessageBarState.showSuccessMessage(message: String) = this.addSuccess(message)

fun CoroutineScope.showErrorMsgBar(message: String) {
    launch {
        ToastBarController.sendError(message)
    }
}
fun CoroutineScope.showSuccessMsgBar(message: String) {
    launch {
        ToastBarController.sendSuccess(message)
    }
}