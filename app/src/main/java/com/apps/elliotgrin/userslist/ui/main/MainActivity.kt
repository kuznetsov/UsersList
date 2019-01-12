package com.apps.elliotgrin.userslist.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.elliotgrin.userslist.R
import com.apps.elliotgrin.userslist.ui.users.UsersFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, UsersFragment(), null)
            .commit()
    }
}
