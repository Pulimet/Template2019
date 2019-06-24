@file:Suppress("ConstantConditionIf")

package net.alexandroid.template2019.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import net.alexandroid.template2019.SHOW_VIEW_MODEL_LOGS
import net.alexandroid.utils.mylog.MyLog
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope, KoinComponent {

    init {
        if (SHOW_VIEW_MODEL_LOGS) MyLog.w("${javaClass.simpleName} - init")
    }
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun onCleared() {
        if (SHOW_VIEW_MODEL_LOGS)  MyLog.w("${javaClass.simpleName} - onCleared")
        coroutineContext.cancel()
    }
}

