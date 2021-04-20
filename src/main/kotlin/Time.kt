import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds
import kotlin.time.minutes

@ExperimentalTime val delayMicro = 100.milliseconds
@ExperimentalTime val delayShort = 5.minutes
@ExperimentalTime val delayLong = 30.minutes

fun isWorkingHours(): Boolean {
    val currentMoment: Instant = Clock.System.now()
    val tzBerlin = TimeZone.of("Europe/Berlin")
    val dateTime = currentMoment.toLocalDateTime(tzBerlin)
    return dateTime.hour in (9..19)
}