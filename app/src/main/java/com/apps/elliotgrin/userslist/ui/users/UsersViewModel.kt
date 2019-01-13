package com.apps.elliotgrin.userslist.ui.users

import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.data.repository.UsersRepository
import com.apps.elliotgrin.userslist.ui.base.BaseViewModel
import com.apps.elliotgrin.userslist.util.Event
import kotlinx.coroutines.withContext

class UsersViewModel(private val usersRepository: UsersRepository) : BaseViewModel<UsersState>() {

    private var users: ArrayList<User>? = null

    override fun onCreate() {
        if (users == null) loadUsers()
        else stateLiveData.value = Event(UsersState.StateShowUsers(users!!))
    }

    override fun stopLoading() {
        stateLiveData.value = Event(UsersState.StateLoading(false))
    }

    override fun showError(error: String) {
        stateLiveData.value = Event(UsersState.StateShowError(error))
    }

    private fun loadUsers() = safeLaunch {
        stateLiveData.value = Event(UsersState.StateLoading(true))

        val usersList = withContext(bgDispatcher) { usersRepository.fetchUsers() }
        users = ArrayList(usersList)

        stateLiveData.value = Event(UsersState.StateLoading(false))
        stateLiveData.value = Event(UsersState.StateShowUsers(users!!))
    }

    fun addUser(user: User) = users?.add(user)

    fun updateUser(user: User, position: Int) = users?.set(position, user)

}