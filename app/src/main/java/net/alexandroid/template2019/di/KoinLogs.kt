package net.alexandroid.template2019.di

import net.alexandroid.template2019.SHOW_KOIN_LOGS
import net.alexandroid.utils.mylogkt.logD
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE

@Suppress("ConstantConditionIf")
class KoinLogs : Logger(level = Level.DEBUG) {

    override fun log(level: Level, msg: MESSAGE) {
        when (level) {
            Level.DEBUG -> if (SHOW_KOIN_LOGS) logD(msg)
            Level.INFO -> if (SHOW_KOIN_LOGS) logD(msg)
            Level.ERROR -> logD(msg)
        }
    }
}