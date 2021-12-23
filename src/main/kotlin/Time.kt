import kotlinx.datetime.*
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@ExperimentalTime val delayMicro = 200.milliseconds
@ExperimentalTime val delayMini = 1.seconds
@ExperimentalTime val delayShort = 1.minutes
@ExperimentalTime val delayLong = 30.minutes

fun isWorkingHours(): Boolean {
    val currentMoment: Instant = Clock.System.now()
    val tzBerlin = TimeZone.of("Europe/Berlin")
    val dateTime = currentMoment.toLocalDateTime(tzBerlin)
    val workingHours = dateTime.hour in (9..21)
    val workingDays = dateTime.dayOfWeek !in listOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
    return workingHours && workingDays
}
