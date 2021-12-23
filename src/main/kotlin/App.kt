@file:JvmName("App")

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.ExperimentalTime

private const val ERROR = -1

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
                val data = runCatching { api.getNotifications() }.getOrNull()
                val count = data?.size ?: ERROR
                nixie.show(count)
            }
            val delay = if (isWorkingHours()) delayShort else delayLong
            delay(delay)
        }
    }

@ExperimentalTime
private suspend fun Nixie.show(count: Int) {
    off()
    delay(delayMicro)
    when(count) {
        0 ->        return
        ERROR ->    dash()
        else ->     display(count)
    }
}
