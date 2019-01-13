package com.apps.elliotgrin.userslist.ui.users

import com.apps.elliotgrin.userslist.data.repository.UsersRepository
import com.apps.elliotgrin.userslist.ui.base.BaseViewModel
import com.apps.elliotgrin.userslist.util.Event
import kotlinx.coroutines.withContext

class UsersViewModel(private val usersRepository: UsersRepository) : BaseViewModel<UsersState>() {

    override fun onStart() {
        loadUsers()
    }

    override fun stopLoading() {
        stateLiveData.value = Event(UsersState.StateLoading(false))
    }

    override fun showError(error: String) {
        stateLiveData.value = Event(UsersState.StateShowError(error))
    }

    private fun loadUsers() = safeLaunch {
        stateLiveData.value = Event(UsersState.StateLoading(true))

        val users = withContext(bgDispatcher) { usersRepository.fetchUsers() }

        stateLiveData.value = Event(UsersState.StateLoading(false))
        stateLiveData.value = Event(UsersState.StateShowUsers(users))
    }

}