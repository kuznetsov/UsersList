package com.apps.elliotgrin.userslist.ui.users

import com.apps.elliotgrin.userslist.data.model.User

sealed class UsersState {
    data class StateLoading(val isLoading: Boolean) : UsersState()
    data class StateShowError(val error: String) : UsersState()
    data class StateShowUsers(val users: List<User>) : UsersState()
}