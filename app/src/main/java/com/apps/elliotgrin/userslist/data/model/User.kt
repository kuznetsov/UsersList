package com.apps.elliotgrin.userslist.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatarUrl: String?,
    val url: String
) : Parcelable