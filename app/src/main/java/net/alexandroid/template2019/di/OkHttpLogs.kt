package net.alexandroid.template2019.di

import net.alexandroid.template2019.SHOW_OK_HTTP_LOGS
import net.alexandroid.utils.mylog.MyLog.i
import okhttp3.logging.HttpLoggingInterceptor

@Suppress("ConstantConditionIf")
class OkHttpLogs : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        if (SHOW_OK_HTTP_LOGS) i(message)
    }
}