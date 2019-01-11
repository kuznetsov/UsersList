package com.apps.elliotgrin.userslist.di

import com.apps.elliotgrin.userslist.data.repository.UsersRepository
import com.apps.elliotgrin.userslist.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    // users ViewModel
    viewModel { UsersViewModel(get()) }

    // users repository
    single { UsersRepository(get()) }

}

val usersListApp  = listOf(appModule)