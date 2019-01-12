package com.apps.elliotgrin.userslist.ui.users

import android.widget.Toast
import com.apps.elliotgrin.userslist.R
import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.ui.base.BaseFragment

class UsersFragment : BaseFragment<UsersState, UsersViewModel>(UsersViewModel::class) {

    override val layoutId: Int
        get() = R.layout.fragment_users

    override fun whenState(state: UsersState) = when(state) {
        is UsersState.StateShowError -> showError(state.error)
        is UsersState.StateShowUsers -> showUsers(state.users)
        is UsersState.StateLoading -> showLoading(state.isLoading)
    }

    private fun showLoading(isLoading: Boolean) {

    }

    private fun showUsers(users: List<User>) {
        Toast.makeText(context, users.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun showError(error: String) {

    }

}
