package com.apps.elliotgrin.userslist.ui.create

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.apps.elliotgrin.userslist.R
import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.ui.base.BaseActivity
import com.apps.elliotgrin.userslist.util.constants.*
import kotlinx.android.synthetic.main.activity_create_user.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CreateUserActivity : BaseActivity<CreateUserState, CreateUserViewModel>(CreateUserViewModel::class) {

    private val intentUser: User? by lazy { intent?.extras?.getParcelable<User>(ARG_USER) }
    private val intentPosition: Int? by lazy { intent?.extras?.getInt(ARG_POSITION) }

    override val viewModel: CreateUserViewModel by viewModel { parametersOf(intentUser) }

    override val layoutId: Int
        get() = R.layout.activity_create_user

    override fun renderState(state: CreateUserState): Unit? = when (state) {
        is CreateUserState.StateUserIsNotNull -> fillUserInputs(state.user)
        is CreateUserState.StateShowError -> showError(state.error)
        is CreateUserState.StateLoading -> showLoading(state.isLoading)
        is CreateUserState.StateUserIsCreated -> returnResult(state.user)
    }

    override fun initViews() {
        val isCreate = intentUser == null

        supportActionBar?.title = if (isCreate) "Create User" else "Update User"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        createUserButton.text = if (isCreate) "Create user" else "Update user"
        createUserButton.setOnClickListener { createUser() }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun fillUserInputs(user: User) {
        firstNameEditText.setText(user.firstName)
        lastNameEditText.setText(user.lastName)
        emailEditText.setText(user.email)
    }

    private fun createUser() {
        val id = if (intentUser == null) NEW_USER_ID else intentUser!!.id
        val user = User(
            id,
            firstNameEditText.text.toString(),
            lastNameEditText.text.toString(),
            emailEditText.text.toString(),
            intentUser?.avatarUrl
        )

        if (id == NEW_USER_ID) viewModel.createUser(user)
        else viewModel.updateUser(user)
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
    }

    private fun returnResult(user: User) {
        val intent = Intent()
        intent.putExtra(ARG_USER, user)
        intent.putExtra(ARG_POSITION, intentPosition)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        fun newIntent(context: Context, user: User? = null, position: Int): Intent {
            val intent = Intent(context, CreateUserActivity::class.java)
            intent.putExtra(ARG_USER, user)
            intent.putExtra(ARG_POSITION, position)
            return intent
        }
    }
}
