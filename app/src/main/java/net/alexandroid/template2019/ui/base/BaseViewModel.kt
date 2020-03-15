@file:Suppress("ConstantConditionIf")

package net.alexandroid.template2019.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import net.alexandroid.template2019.SHOW_VIEW_MODEL_LOGS
import net.alexandroid.utils.mylogkt.logW

import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), KoinComponent {

    init {
        if (SHOW_VIEW_MODEL_LOGS) logW("${javaClass.simpleName} - init")
    }
    override fun onCleared() {
        if (SHOW_VIEW_MODEL_LOGS)  logW("${javaClass.simpleName} - onCleared")
    }
}

