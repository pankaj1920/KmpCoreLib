package com.psbapp.uidesign.ui.textview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.psbapp.appres.CoreStringRes
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.typography.normalStyle
import com.psbapp.utils.downTimer
import syncride.core.resources.generated.resources.did_not_receive_otp
import syncride.core.resources.generated.resources.send_again
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

@Composable
fun ResendCode(

    textColor: Color = MaterialThemeColor.blackColor,
    actionTextColor: Color = MaterialThemeColor.actionColor,
    textFontWeight: TextWeight = TextWeight.REGULAR,
    actionTextFontWeight: TextWeight = TextWeight.BOLD,
    resendClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val resend = stringResource(CoreStringRes.send_again)
    var resendText by remember { mutableStateOf(resend) }
    var resendAgain by remember { mutableStateOf(true) }

    val textDecoration = if (resendAgain) TextDecoration.Underline else null
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        PSBText(
            text = stringResource(CoreStringRes.did_not_receive_otp),
            fontWeight = textFontWeight,
            style = normalStyle.copy(fontSize = 13.sp, color = textColor)
        )
        PSBText(
            text = resendText,
            style = normalStyle.copy(
                fontSize = 14.sp,
                color = actionTextColor,
                textDecoration = textDecoration
            ),
            fontWeight = actionTextFontWeight,
            modifier = Modifier.clickable {
                scope.launch {
                    if (resendAgain) {
                        resendClick()
                        downTimer(59).collect {
                            if (it == -1) {
                                resendAgain = true
                                resendText = getString(CoreStringRes.send_again)
                            } else {
                                resendText = "00:$it"
                                resendAgain = false
                            }
                        }
                    }

                }
            })
    }
}