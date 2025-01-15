package com.psbapp.uidesign.ui.toastbar.controller

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

data class ToastBarEvent(
    val message: String,
    val status: Boolean,
    val action: (() -> Unit?)? = null
)

object ToastBarController {
    private val _event = Channel<ToastBarEvent>()
    val event = _event.receiveAsFlow()

    suspend fun sendSuccess(message: String){
        _event.send(ToastBarEvent(message=message, status=true))
    }
    suspend fun sendError(message: String){
        _event.send(ToastBarEvent(message=message, status=false))
    }
}