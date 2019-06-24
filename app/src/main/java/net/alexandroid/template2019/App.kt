package net.alexandroid.template2019

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import net.alexandroid.shpref.ShPref
import net.alexandroid.utils.mylog.MyLog
import kotlin.coroutines.CoroutineContext

class App : Application(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun onCreate() {
        super.onCreate()
        MyLog.init(applicationContext, "Template2019", BuildConfig.DEBUG)
        MyLog.e("=== Application onCreate()")
        ShPref.init(applicationContext, ShPref.APPLY)
    }
}