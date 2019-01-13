package com.apps.elliotgrin.userslist.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apps.elliotgrin.userslist.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<StateT> : ViewModel() {

    abstract fun onCreate()

    abstract fun stopLoading()

    abstract fun showError(error: String)

    private val job = SupervisorJob()

    val stateLiveData = MutableLiveData<Event<StateT>>()

    protected val bgDispatcher = Dispatchers.IO

    protected fun safeLaunch(
        context: CoroutineContext = Dispatchers.Main,
        block: suspend () -> Unit = {}
    ) = CoroutineScope(context + job).launch {
        try {
            block()
        } catch (t: Throwable) {
            stopLoading()
            showError(t.localizedMessage)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}