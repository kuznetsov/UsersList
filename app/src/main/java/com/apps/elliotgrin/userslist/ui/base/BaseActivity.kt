package com.apps.elliotgrin.userslist.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModelByClass
import kotlin.reflect.KClass

abstract class BaseActivity<StateT, out ViewModelT: BaseViewModel<StateT>>(clazz: KClass<ViewModelT>) : AppCompatActivity() {

    protected open val viewModel: ViewModelT by viewModelByClass(clazz)

    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId)
        subscribe()
        initViews()

        viewModel.onCreate()
    }

    private fun subscribe() {
        viewModel.stateLiveData.observe(this, Observer { event->
            event.getContentIfNotHandled()?.let { state->
                whenState(state)
            }
        })
    }

    abstract fun whenState(state: StateT) : Unit?

    abstract fun initViews()
}