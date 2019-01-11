package com.apps.elliotgrin.userslist.ui.users

import com.apps.elliotgrin.userslist.data.repository.UsersRepository
import com.apps.elliotgrin.userslist.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModel(private val usersRepository: UsersRepository) : BaseViewModel<UsersState>() {

    override fun onStart() {

    }

    override fun stopLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun loadUsers() = CoroutineScope(Dispatchers.Main).launch {
        // show loading
        val users = withContext(Dispatchers.IO) { usersRepository.fetchUsers() }
        // stop loading
        // update state with users
    }

}