package net.alexandroid.template2019.network

import kotlinx.coroutines.delay
import net.alexandroid.utils.mylog.MyLog
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> retryIO(
    times: Int = 4,
    initialDelay: Long = 1000, // 1 second
    maxDelay: Long = 4000,    // 4 seconds
    factor: Double = 2.0,
    desc: String = "",
    block: suspend () -> T?
): T? {
    var currentDelay = initialDelay
    repeat(times) {
        try {
            return block()
        } catch (e: IOException) {
            MyLog.e("Description (IOException): $desc, fail counter: ${it + 1}, Exception: ${e.message} ")
        } catch (e: HttpException) {
            MyLog.e("Description (HttpException): $desc, fail counter: ${it + 1}, Exception: ${e.message} ")
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    return null
}


