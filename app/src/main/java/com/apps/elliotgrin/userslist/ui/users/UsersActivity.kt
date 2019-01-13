package com.apps.elliotgrin.userslist.ui.users

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.elliotgrin.userslist.R
import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.ui.base.BaseActivity
import com.apps.elliotgrin.userslist.ui.create.CreateUserActivity
import com.apps.elliotgrin.userslist.util.ext.setVisible
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : BaseActivity<UsersState, UsersViewModel>(UsersViewModel::class) {

    override val layoutId: Int
        get() = R.layout.activity_users

    override fun renderState(state: UsersState) = when (state) {
        is UsersState.StateLoading -> showLoading(state.isLoading)
        is UsersState.StateShowUsers -> showUsers(state.users)
        is UsersState.StateShowError -> showError(state.error)
    }

    override fun initViews() {
        supportActionBar?.title = "Users List"

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        fab.setOnClickListener { startCreateUserActivity(null) }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.setVisible(isLoading)
        recyclerView.setVisible(!isLoading)
    }

    private fun showUsers(users: List<User>) {
        val adapter = UsersRecyclerAdapter(users, this) { user -> startCreateUserActivity(user) }
        recyclerView.adapter = adapter
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun startCreateUserActivity(user: User?) {
        val intent = CreateUserActivity.newIntent(this, user)
        startActivity(intent)
    }
}
