@file:JvmName("App")

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main(args: Array<String>): Unit =
    runBlocking {

        require(args.size >= 2) { "Pass user and token" }
        val (user, token) = args

        val api = Api.create(user, token)
        val nixie = Nixie()
        var job: Job? = null

        while (true) {
            job?.cancel()
            job = launch {
                val current = api.getNotifications().size
                nixie.show(current)
            }
            val delay = if (isWorkingHours()) delayShort else delayLong
            delay(delay)
        }
    }

@ExperimentalTime
private suspend fun Nixie.show(count: Int) {
    if (count == 0) {
        off()
        return
    }
    // Blinks every delayMini time
    while (coroutineContext.isActive) {
        off()
        delay(delayMicro)
        display(count)
        delay(delayMini)
    }
}