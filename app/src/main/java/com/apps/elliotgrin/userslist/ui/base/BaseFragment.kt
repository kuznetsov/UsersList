package com.apps.elliotgrin.userslist.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModelByClass
import kotlin.reflect.KClass

abstract class BaseFragment<StateT, out ViewModelT: BaseViewModel<StateT>>(clazz: KClass<ViewModelT>) : Fragment() {

    protected val viewModel: ViewModelT by viewModelByClass(clazz)

    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onStart() {
        super.onStart()

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