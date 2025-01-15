package com.psbapp.uidesign.ui.calendar.monthscroll

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.YearMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.minusMonths
import com.kizitonwose.calendar.core.now
import com.kizitonwose.calendar.core.plusMonths
import com.psbapp.uidesign.theme.colors.BgColor
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import com.psbapp.uidesign.theme.dimension.TextDimension
import com.psbapp.uidesign.theme.typography.secondaryDarkStyle
import com.psbapp.uidesign.ui.calendar.next
import com.psbapp.uidesign.ui.calendar.previous
import com.psbapp.uidesign.ui.calendar.rememberFirstMostVisibleMonth
import com.psbapp.uidesign.ui.textview.PSBText
import com.psbapp.uidesign.ui.textview.TextWeight
import kotlinx.coroutines.launch
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate

@Composable
fun PSBMonthScrollCalendar(
    isCurrentDateSelected: Boolean = true,
    adjacentMonths: Int = 500,
    isSingleSelection: Boolean = true,
    onDateSelected: (date: String) -> Unit
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(adjacentMonths) }
    val endMonth = remember { currentMonth.plusMonths(adjacentMonths) }
    val selections = remember { mutableStateListOf<CalendarDay>() }
    val today = remember { LocalDate.now() }
    val daysOfWeek = remember { daysOfWeek() }
    val currentDay =
        remember { CalendarDay(date = today, position = DayPosition.MonthDate) } // Get today's date

    LaunchedEffect(Unit) {
        if (selections.isEmpty() && isCurrentDateSelected) {
            selections.add(currentDay)
        }
    }
    Column(
        modifier = Modifier
            .background(Color.White).padding(MaterialDimension.dp5),
    ) {
        val state = rememberCalendarState(
            startMonth = startMonth,
            endMonth = endMonth,
            firstVisibleMonth = currentMonth,
            firstDayOfWeek = daysOfWeek.first(),
        )
        val coroutineScope = rememberCoroutineScope()
        val visibleMonth = rememberFirstMostVisibleMonth(state, viewportPercent = 90f)
        SimpleCalendarTitle(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
            currentMonth = visibleMonth.yearMonth,
            goToPrevious = {
                coroutineScope.launch {
                    state.animateScrollToMonth(state.firstVisibleMonth.yearMonth.previous)
                }
            },
            goToNext = {
                coroutineScope.launch {
                    state.animateScrollToMonth(state.firstVisibleMonth.yearMonth.next)
                }
            },
        )
        HorizontalCalendar(
            modifier = Modifier
                .testTag("Calendar"),
            state = state,
            dayContent = { day ->
                Day(day, isSelected = selections.contains(day)) { calendarDay ->
                    if (isSingleSelection) {
                        selections.clear()
                        selections.add(calendarDay)
                    } else {
                        if (selections.contains(calendarDay)) {
                            selections.remove(calendarDay)
                        } else {
                            selections.add(calendarDay)
                        }
                    }
                    onDateSelected(day.date.toString())

                }
            },
            monthHeader = {
                MonthHeader(daysOfWeek = daysOfWeek)
            },
        )
    }
}

@Composable
private fun MonthHeader(daysOfWeek: List<DayOfWeek>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("MonthHeader"),
    ) {
        for (dayOfWeek in daysOfWeek) {
            PSBText(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.displayDaysText(),
                fontWeight = TextWeight.MEDIUM,
                style = secondaryDarkStyle.copy(fontSize = TextDimension.sp10)
            )
        }
    }
}

@Composable
private fun Day(day: CalendarDay, isSelected: Boolean, onClick: (CalendarDay) -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f) // This is important for square-sizing!
            .testTag("MonthDay")
            .padding(MaterialDimension.dp4)
            .clip(CircleShape)
            .background(color = if (isSelected) MaterialThemeColor.primaryDarkColor else BgColor)
            // Disable clicks on inDates/outDates
            .clickable(
                enabled = day.position == DayPosition.MonthDate,
                onClick = { onClick(day) },
            ),
        contentAlignment = Alignment.Center,
    ) {
        val textColor = when (day.position) {
            // Color.Unspecified will use the default text color from the current theme
            DayPosition.MonthDate -> if (isSelected) Color.White else Color.Unspecified
            DayPosition.InDate, DayPosition.OutDate -> MaterialThemeColor.lightGreyColor
        }
        PSBText(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
            fontWeight = TextWeight.MEDIUM,
            style = secondaryDarkStyle.copy(color = textColor, fontSize = TextDimension.sp8)
        )
    }
}
