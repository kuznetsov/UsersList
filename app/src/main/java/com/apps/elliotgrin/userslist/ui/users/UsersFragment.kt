package com.apps.elliotgrin.userslist.ui.users

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.elliotgrin.userslist.R
import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.ui.base.BaseFragment
import com.apps.elliotgrin.userslist.ui.create.CreateUserActivity
import com.apps.elliotgrin.userslist.util.ext.setVisible
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : BaseFragment<UsersState, UsersViewModel>(UsersViewModel::class) {

    override val layoutId: Int
        get() = R.layout.fragment_users

    override fun whenState(state: UsersState) = when (state) {
        is UsersState.StateLoading -> showLoading(state.isLoading)
        is UsersState.StateShowUsers -> showUsers(state.users)
        is UsersState.StateShowError -> showError(state.error)
    }

    override fun initViews() {
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        fab.setOnClickListener { startCreateUserActivity(null) }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.setVisible(isLoading)
        recyclerView.setVisible(!isLoading)
    }

    private fun showUsers(users: List<User>) {
        context?.let { context ->
            val adapter = UsersRecyclerAdapter(users, context) { user -> startCreateUserActivity(user) }
            recyclerView.adapter = adapter
        }
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    private fun startCreateUserActivity(user: User?) {
        context?.let { context ->
            val intent = CreateUserActivity.newIntent(context, user)
            startActivity(intent)
        }
    }

}
