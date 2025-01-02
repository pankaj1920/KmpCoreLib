package com.psbapp.uidesign.utils.modifier

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp


fun Modifier.backGestureHandler(onBack: () -> Unit): Modifier =
    this.pointerInput(Unit) {
        var dragStartPositionInPx = 0F
        var dragAmount = 0F
        detectHorizontalDragGestures(
            onDragStart = { offset ->
                dragAmount = 0F
                dragStartPositionInPx = offset.x
            },
            onDragEnd = {
                if (dragAmount > 0 && dragStartPositionInPx < 100.dp.toPx()) {
                    onBack()
                }
            },
            onDragCancel = {
            },
            onHorizontalDrag = { change, drag ->
                dragAmount = drag
            }
        )
    }