package com.apps.elliotgrin.userslist.ui.create

import com.apps.elliotgrin.userslist.data.model.User

sealed class CreateUserState {
    data class StateUserIsNotNull(val user: User) : CreateUserState()
    data class StateLoading(val isLoading: Boolean) : CreateUserState()
    data class StateShowError(val error: String) : CreateUserState()
    data class StateUserIsCreated(val user: User) : CreateUserState()
}