package com.apps.elliotgrin.userslist.ui.create

import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.data.repository.UsersRepository
import com.apps.elliotgrin.userslist.ui.base.BaseViewModel
import com.apps.elliotgrin.userslist.util.Event

class CreateUserViewModel(private val user: User?, private val repository: UsersRepository) :
    BaseViewModel<CreateUserState>() {

    override fun onCreate() {
        user?.let { stateLiveData.value = Event(CreateUserState.StateUserIsNotNull(it)) }
    }

    fun createUser(user: User) = safeLaunch {
            stateLiveData.value = Event(CreateUserState.StateLoading(true))
            val newUser = repository.createUser(user)
            stateLiveData.value = Event(CreateUserState.StateUserIsCreated(newUser))
        }

    fun updateUser(user: User) = safeLaunch {
            stateLiveData.value = Event(CreateUserState.StateLoading(true))
            val newUser = repository.updateUser(user)
            stateLiveData.value = Event(CreateUserState.StateUserIsCreated(newUser))
        }

    override fun stopLoading() {
        stateLiveData.value = Event(CreateUserState.StateLoading(false))
    }

    override fun showError(error: String) {
        stateLiveData.value = Event(CreateUserState.StateShowError(error))
    }
}