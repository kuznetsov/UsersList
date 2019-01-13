package com.apps.elliotgrin.userslist.ui.create

import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.ui.base.BaseViewModel
import com.apps.elliotgrin.userslist.util.Event

class CreateUserViewModel(private val user: User?) : BaseViewModel<CreateUserState>() {

    override fun onStart() {
        user?.let { stateLiveData.value = Event(CreateUserState.StateUserIsNotNull(it)) }
    }

    fun createUser(user: User) {
        
    }


    override fun stopLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}