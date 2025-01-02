//package com.psbapp.uidesign.ui.Calendar
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Color.Companion.DarkGray
//import androidx.compose.ui.unit.dp
//import com.google.firebase.vertexai.common.server.Date
//import com.kizitonwose.calendar.compose.CalendarState
//import com.kizitonwose.calendar.compose.HorizontalCalendar
//import com.kizitonwose.calendar.compose.rememberCalendarState
//import com.kizitonwose.calendar.compose.yearcalendar.rememberYearCalendarState
//import com.kizitonwose.calendar.core.CalendarDay
//import com.kizitonwose.calendar.core.ExperimentalCalendarApi
//import com.kizitonwose.calendar.core.Year
//import com.kizitonwose.calendar.core.YearMonth
//import com.kizitonwose.calendar.core.daysOfWeek
//import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
//import com.kizitonwose.calendar.core.minusMonths
//import com.kizitonwose.calendar.core.minusYears
//import com.kizitonwose.calendar.core.now
//import com.kizitonwose.calendar.core.plusMonths
//import com.kizitonwose.calendar.core.plusYears
//import com.psbapp.uidesign.theme.colors.Black
//import com.psbapp.uidesign.theme.colors.White
//import kotlinx.coroutines.launch
//import kotlinx.datetime.Instant
//import kotlinx.datetime.LocalDate
//import kotlinx.datetime.LocalDateTime
//import org.jetbrains.compose.ui.tooling.preview.Preview
//
//@OptIn(ExperimentalCalendarApi::class)
//@Composable
//fun CalendarView(
//) {
//    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
//    val coroutineScope = rememberCoroutineScope()
//    val selectedDate = remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
////    val context = LocalContext.current
//    val earliestMonth = remember {
//        mutableStateOf(YearMonth.now())
//    }
//    val userScrollEnabled = remember {
//        mutableStateOf(currentMonth >= earliestMonth.value)
//    }
//    val currentYear = remember { Year.now() }
//    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
//    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
//    val firstDayOfWeek =
//        remember { firstDayOfWeekFromLocale() } // Available from the library
//    val state = rememberCalendarState(
//        startMonth = startMonth,
//        endMonth = endMonth,
//        firstVisibleMonth = currentMonth,
//        firstDayOfWeek = firstDayOfWeek
//    )
//
//    Column(
//        modifier = Modifier
//            .padding(horizontal = 30.dp)
//    ) {
//        HorizontalCalendar(
//            userScrollEnabled = userScrollEnabled.value,
//            state = state,
//            dayContent = { day ->
//                Day(
//                    day,
//                    selectedDate,
//                    Date()
//                ) { date ->
//
//                }
//            },
//            monthHeader = {
//                DaysOfWeekTitle(it.yearMonth, previusClick = {
//                    coroutineScope.launch {
//                        if (currentMonth > YearMonth.now()) {
//                            currentMonth = currentMonth.minusMonths(1)
//                        }
//                    }
//                }, nextClick = {
//                    coroutineScope.launch {
//                        // Allow scrolling to next year by removing restriction to the current year
//                        if (currentMonth < YearMonth.now().plusMonths(24)) {
//                            currentMonth = currentMonth.plusMonths(1)
//                        }
//                    }
//                })
//            })
//    }
//}
//
//@Composable
//fun Day(
//    day: CalendarDay,
//    selectedDate: MutableState<LocalDate?>,
//    preSelectedDate: LocalDateTime,
//    onClicks: (CalendarDay) -> Unit
//) {
//    val today = LocalDate.now()
//
//    // Determine the text color based on the date
//    val textColor = when {
//        day.date == selectedDate.value -> White
//        else -> DarkGray
//    }
//    val backgroundColor = when (day.date) {
//        selectedDate.value, convertDateToLocalDate(preSelectedDate) -> Color.Red
//        today -> Color.Red
//        else -> Color.Transparent
//    }
//
//    Box(
//        modifier = Modifier
//            .aspectRatio(1f),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = day.date.dayOfMonth.toString(),
//            modifier = Modifier
//                .background(backgroundColor, shape = CircleShape)
//                .padding(horizontal = 9.dp, vertical = 6.dp)
//        )
//    }
//}
//
//fun convertDateToLocalDate(date: Date): LocalDate {
//    // Convert Date to Instant
//    val instant: Instant = date.toInstant()
//
//    // Create ZoneId, defaulting to UTC or any specific timezone you want
//    val zoneId: ZoneId = ZoneId.systemDefault()
//
//    // Convert Instant to LocalDate
//    return instant.atZone(zoneId).toLocalDate()
//}
//
//
//
