package net.alexandroid.template2019

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.alexandroid.shpref.ShPref
import net.alexandroid.template2019.di.KoinLogs
import net.alexandroid.template2019.di.appModule
import net.alexandroid.utils.mylogkt.MyLogKt
import net.alexandroid.utils.mylogkt.logE
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.coroutines.CoroutineContext

class App : Application(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun onCreate() {
        super.onCreate()
        MyLogKt.tag = "Template2019"
        MyLogKt.packageName = packageName

        logE("=== Application onCreate()")
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