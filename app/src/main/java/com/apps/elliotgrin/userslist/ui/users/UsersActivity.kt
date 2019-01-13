package com.apps.elliotgrin.userslist.ui.users

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.elliotgrin.userslist.R
import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.ui.base.BaseActivity
import com.apps.elliotgrin.userslist.ui.create.CreateUserActivity
import com.apps.elliotgrin.userslist.util.constants.*
import com.apps.elliotgrin.userslist.util.ext.setVisible
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : BaseActivity<UsersState, UsersViewModel>(UsersViewModel::class) {

    override val layoutId: Int
        get() = R.layout.activity_users

    private lateinit var adapter: UsersRecyclerAdapter

    override fun renderState(state: UsersState) = when (state) {
        is UsersState.StateLoading -> showLoading(state.isLoading)
        is UsersState.StateShowUsers -> showUsers(state.users)
        is UsersState.StateShowError -> showError(state.error)
    }

    override fun initViews() {
        supportActionBar?.title = "Users List"

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        fab.setOnClickListener { startCreateUserActivity(null, POSITION_NEW_USER) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CREATE_USER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val position = data?.getIntExtra(ARG_POSITION, POSITION_NEW_USER) ?: POSITION_NEW_USER
            val user = data?.getParcelableExtra<User>(ARG_USER)

            user?.let {
                if (position == POSITION_NEW_USER) {
                    viewModel.addUser(it)
                    adapter.addUser(it)
                } else {
                    viewModel.updateUser(it, position)
                    adapter.updateUser(it, position)
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.setVisible(isLoading)
        recyclerView.setVisible(!isLoading)
    }

    private fun showUsers(users: List<User>) {
        adapter = UsersRecyclerAdapter(ArrayList(users), this) { user, position ->
            startCreateUserActivity(
                user,
                position
            )
        }
        recyclerView.adapter = adapter
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun startCreateUserActivity(user: User?, position: Int) {
        val intent = CreateUserActivity.newIntent(this, user, position)
        startActivityForResult(intent, CREATE_USER_REQUEST_CODE)
    }
}
