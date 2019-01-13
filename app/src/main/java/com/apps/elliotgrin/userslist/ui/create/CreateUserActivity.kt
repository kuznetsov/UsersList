package com.apps.elliotgrin.userslist.ui.create

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.apps.elliotgrin.userslist.R
import com.apps.elliotgrin.userslist.data.model.User
import com.apps.elliotgrin.userslist.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val ARG_USER = "arg:user"

class CreateUserActivity : BaseActivity<CreateUserState, CreateUserViewModel>(CreateUserViewModel::class) {

    override val viewModel: CreateUserViewModel by viewModel { parametersOf(intent?.extras?.getParcelable(ARG_USER)) }

    override val layoutId: Int
        get() = R.layout.activity_create_user

    override fun whenState(state: CreateUserState): Unit? = when(state) {
        is CreateUserState.StateUserIsNull -> Toast.makeText(this, "User is null", Toast.LENGTH_SHORT).show()
        is CreateUserState.StateUserIsNotNull -> Toast.makeText(this, "User is not null: ${state.user}", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newIntent(context: Context, user: User? = null): Intent {
            val intent = Intent(context, CreateUserActivity::class.java)
            intent.putExtra(ARG_USER, user)
            return intent
        }
    }
}
