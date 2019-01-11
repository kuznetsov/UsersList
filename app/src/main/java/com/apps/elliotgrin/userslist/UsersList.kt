package com.apps.elliotgrin.userslist

import android.app.Application
import com.apps.elliotgrin.userslist.di.apiModule
import com.apps.elliotgrin.userslist.di.usersListApp
import org.koin.android.ext.android.startKoin

class UsersList : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, usersListApp + apiModule)
    }

}