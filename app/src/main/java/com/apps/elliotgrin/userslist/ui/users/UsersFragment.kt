package com.apps.elliotgrin.userslist.ui.users

import com.apps.elliotgrin.userslist.R
import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.ui.base.BaseFragment

class UsersFragment : BaseFragment<UsersState, UsersViewModel>(UsersViewModel::class) {

    override val layoutId: Int
        get() = R.layout.fragment_users

    override fun whenState(state: UsersState) = when(state) {
        is UsersState.StateStopLoading -> stopLoading()
        is UsersState.StateShowError -> showError(state.error)
        is UsersState.StateShowUsers -> showUsers(state.users)
    }

    private fun showUsers(users: List<User>) {

    }

    private fun stopLoading() {

    }

    private fun showError(error: String) {

    }

}
