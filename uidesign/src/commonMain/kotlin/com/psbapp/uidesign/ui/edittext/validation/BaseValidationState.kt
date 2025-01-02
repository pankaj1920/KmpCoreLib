package com.psbapp.uidesign.ui.edittext.validation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

open class BaseValidationState(
    private val validator: (String) -> Boolean = { true },
    private val errorFor: (String) -> String = { "" },
    showError: Boolean = true
) {
    companion object {
        var errorMessage: String by mutableStateOf("")
    }

    var text: String by mutableStateOf("")
    private var displayErrors: Boolean by mutableStateOf(showError)


    open suspend fun isValid(): Boolean = validator(text)


    suspend fun hasError() = !isValid() && displayErrors


    fun enableError() {
        displayErrors = true
    }

    fun disableError() {
        displayErrors = false
    }


    suspend fun getError(): String {
        return if (hasError()) errorMessage else ""
    }

}

fun textFieldStateSaver(state: BaseValidationState) = listSaver<BaseValidationState, Any>(
    save = { listOf(it.text) },
    restore = {
        state.apply {
            text = it[0] as String
        }
    }
)


fun <T : BaseValidationState> baseModelSaver(
    vararg states: T,
    createModel: (List<T>) -> T
): Saver<T, Any> {
    return listSaver(
        save = { states.map { it.text } },
        restore = { savedValues ->
            val restoredStates = savedValues.mapIndexed { index, value ->
                states[index].apply { text = value }
            }
            createModel(restoredStates)
        }
    )
}
