@file:JvmName("App")

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

@ExperimentalTime
fun main(args: Array<String>): Unit =
    runBlocking {
        val (user, token) = args
        val api = Api.create(user, token)
        val nixie = Nixie()
        while (true) {
            val count = api.getNotifications().size
            println(count)
            nixie.display(digit = count)
            delay(60.seconds)
        }
    }