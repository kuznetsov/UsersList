package com.apps.elliotgrin.userslist.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.elliotgrin.userslist.ui.users.UsersFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .add(UsersFragment(), null)
            .commit()
    }
}
