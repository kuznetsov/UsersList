package com.apps.elliotgrin.userslist.ui.users

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.elliotgrin.userslist.R
import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.ui.base.BaseFragment
import com.apps.elliotgrin.userslist.util.ext.setVisible
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : BaseFragment<UsersState, UsersViewModel>(UsersViewModel::class) {

    override val layoutId: Int
        get() = R.layout.fragment_users

    override fun whenState(state: UsersState) = when(state) {
            is UsersState.StateLoading -> showLoading(state.isLoading)
            is UsersState.StateShowUsers -> showUsers(state.users)
            is UsersState.StateShowError -> showError(state.error)
        }

    override fun initViews() {
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
    }

    private fun showLoading(isLoading: Boolean) {
        Log.d("#MainActivity", "showLoading($isLoading)")
        progressBar.setVisible(isLoading)
        recyclerView.setVisible(!isLoading)
    }

    private fun showUsers(users: List<User>) {
        Log.d("#MainActivity", "showUsers(${users.size})")
        context?.let {
            val adapter = UsersRecyclerAdapter(users, it)
            recyclerView.adapter = adapter
        }
    }

    private fun showError(error: String) {
        Log.d("#MainActivity", "showError($error)")
    }

}
