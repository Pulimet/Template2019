package net.alexandroid.template2019

import android.app.Application
import net.alexandroid.shpref.ShPref
import net.alexandroid.utils.mylog.MyLog

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MyLog.init(applicationContext, "Template2019", BuildConfig.DEBUG)
        MyLog.e("=== Application onCreate()")
        ShPref.init(applicationContext, ShPref.APPLY)


    }
}