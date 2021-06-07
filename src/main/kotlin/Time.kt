import kotlinx.datetime.*
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds
import kotlin.time.minutes

@ExperimentalTime val delayMicro = 200.milliseconds
@ExperimentalTime val delayMini = 1.minutes
@ExperimentalTime val delayShort = 5.minutes
@ExperimentalTime val delayLong = 30.minutes

fun isWorkingHours(): Boolean {
    val currentMoment: Instant = Clock.System.now()
    val tzBerlin = TimeZone.of("Europe/Berlin")
    val dateTime = currentMoment.toLocalDateTime(tzBerlin)
    val workingHours = dateTime.hour in (9..19)
    val workingDays = dateTime.dayOfWeek !in listOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
    return workingHours && workingDays
}