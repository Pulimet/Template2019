package net.alexandroid.template2019

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.alexandroid.shpref.ShPref
import net.alexandroid.template2019.di.KoinLogs
import net.alexandroid.template2019.di.appModule
import net.alexandroid.utils.mylog.MyLog
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import kotlin.coroutines.CoroutineContext

class App : Application(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun onCreate() {
        super.onCreate()
        MyLog.init(applicationContext, "Template2019", BuildConfig.DEBUG)
        MyLog.e("=== Application onCreate()")
        ShPref.init(applicationContext, ShPref.APPLY)

        launch { initKoin() }
    }

    private fun initKoin() {
        startKoin {
            logger(KoinLogs())
            androidContext(applicationContext)
            modules(listOf(appModule))
        }
    }
}