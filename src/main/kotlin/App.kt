@file:JvmName("App")

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main(args: Array<String>): Unit =
    runBlocking {

        require(args.size >= 2) { "Pass user and token" }
        val (user, token) = args

        val api = Api.create(user, token)
        val nixie = Nixie()
        var count = 0

        while (true) {
            val current = api.getNotifications().size
            if (count != current) {
                count = current
                nixie.off()
                delay(delayMicro)
            }
            with(nixie) {
                if (count > 0) display(count) else off()
            }
            val delay = if (isWorkingHours()) delayShort else delayLong
            delay(delay)
        }
    }