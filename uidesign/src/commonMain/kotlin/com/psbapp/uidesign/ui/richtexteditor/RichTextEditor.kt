package com.psbapp.uidesign.ui.richtexteditor

import androidx.compose.runtime.Composable
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor

@Composable
fun PSBRichTextEditor(text:String){
    val state = rememberRichTextState()

    RichTextEditor(
        state = state,
    )
}